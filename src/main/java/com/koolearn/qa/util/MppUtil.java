package com.koolearn.qa.util;

import com.google.gson.Gson;
import com.koolearn.qa.constant.Constant;
import net.sf.mpxj.*;
import net.sf.mpxj.mpp.MPPReader;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/18
 */
public class MppUtil {

    private List<TreeGridModel> treeGridList = new ArrayList<>();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static String dumpJson(String mppFilePath) {
        MppUtil mappUtil = new MppUtil();
        ProjectFile projectFile = mappUtil.readProject(mppFilePath);
        if (projectFile == null) {
            return null;
        }
        List<TreeGridModel> list = mappUtil.dumpTreeList(projectFile);
        String json = null;
        if (list != null) {
            Gson gson = new Gson();
            json = gson.toJson(list);
        }
        return json;
    }


    private ProjectFile readProject(String mppFilePath) {
        File file = new File(mppFilePath);
        MPPReader mppRead = new MPPReader();
        ProjectFile pf = null;
        try {
            pf = mppRead.read(file);
        } catch (MPXJException e) {
            e.printStackTrace();
            return pf;
        }
        return pf;
    }


    /**
     * 获取资源
     *
     * @param task
     * @return 资源名称
     */
    public String getResource(Task task) {
        StringBuffer buf = new StringBuffer();
        List<ResourceAssignment> assignments = task.getResourceAssignments();
        for (ResourceAssignment assignment : assignments) {
            Resource resource = assignment.getResource();
            if (resource != null) {
                buf.append(resource.getName()).append(Constant.COMMA);
            }
        }
        if (buf.toString().endsWith(Constant.COMMA)) {
            return buf.substring(0, buf.length() - 1);
        }
        return buf.toString();
    }

    /**
     * 获取前置任务
     *
     * @param task
     * @return
     */
    public String getPredecessors(Task task) {
        StringBuffer buf = new StringBuffer();
        List<Relation> list = task.getPredecessors();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Relation relation = list.get(i);
                buf.append(relation.getTargetTask().getID()).append(Constant.COMMA);
            }
        }
        if (buf.toString().endsWith(Constant.COMMA)) {
            return buf.substring(0, buf.length() - 1);
        }
        return buf.toString();
    }

    public List<TreeGridModel> dumpTreeList(ProjectFile file) {
        List childTasks = file.getChildTasks();
        if (childTasks != null) {
            for (int i = 0; i < childTasks.size(); i++) {
                Task task = (Task) childTasks.get(i);
                TreeGridModel treeGridModel = new TreeGridModel();
                if (task.getID() != null && task.getID() != 0) {
                    treeGridModel.id = task.getID();
                    treeGridModel.pId = null;
                    if (StringUtils.isBlank(task.getName())) {
                        continue;
                    }
                    treeGridModel.name = task.getName();
                    if (task.getManualDuration() != null) {
                        treeGridModel.duration = task.getManualDuration().getDuration();
                    } else {
                        if (task.getDuration() != null) {
                            treeGridModel.duration = task.getDuration().getDuration();
                        } else {
                            treeGridModel.duration = null;
                        }
                    }
                    treeGridModel.start = formatter.format(task.getStart());
                    treeGridModel.finish = formatter.format(task.getFinish());
                    treeGridModel.beforeTask = getPredecessors(task);
                    treeGridModel.resource = getResource(task);
                    treeGridModel.children = hierarchy(task);
                    treeGridList.add(treeGridModel);
                } else {
                    treeGridList = hierarchy(task);
                }
            }
        }
        return treeGridList;
    }

    private List<TreeGridModel> hierarchy(Task task) {
        List<TreeGridModel> list = new ArrayList<>();
        List childTasks = task.getChildTasks();
        for (int i = 0; i < childTasks.size(); i++) {
            Task child = (Task) childTasks.get(i);
            TreeGridModel treeGridModel = new TreeGridModel();
            treeGridModel.id = child.getID();
            treeGridModel.pId = task.getID();
            treeGridModel.name = child.getName();
            if (StringUtils.isBlank(task.getName())) {
                continue;
            }
            if (child.getManualDuration() != null) {
                treeGridModel.duration = child.getManualDuration().getDuration();
            } else {
                treeGridModel.duration = child.getDuration().getDuration();
            }
            treeGridModel.start = formatter.format(child.getStart());
            treeGridModel.finish = formatter.format(child.getFinish());
            treeGridModel.beforeTask = getPredecessors(child);
            treeGridModel.resource = getResource(child);
            treeGridModel.children = hierarchy(child);
            list.add(treeGridModel);
        }
        return list;
    }

    public class TreeGridModel {
        public Integer id;
        public Integer pId;
        public String name;
        public Double duration;
        public String start;
        public String finish;
        public String beforeTask;
        public String resource;
        public List<TreeGridModel> children;
    }

}
