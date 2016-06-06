package com.koolearn.qa.util;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/16
 */
public class FileUtil {
    private final static int FILES_PERPAGE = 1000;// 一个文件夹下的文件数最大限制
    /**
     * 获取系统目录(区分linux和windows)
     *
     * @return
     */
    public static String getPath() {
        return PropUtil.getSystemGlobalsProperties("base_dir");
    }

    /**
     * 获取文件上传的根路径url
     *
     * @return
     */
    public static String getUploadUrl() {
        return PropUtil.getSystemGlobalsProperties("host_dir");
    }

    /**
     * 生成文件名，用户存储用户上传的各种文件
     *
     * @param suffix
     *            文件后缀 eg:.doc, .jpg, .jpeg 一定要带着'点'啊
     * @return
     */
    public static String generateFileName(String suffix) {
        String sep = File.separator;
        // 格式化日期 eg:201202/01--12年二月1日
        String fmt = "yyyyMM" + sep + "dd";
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        String dateStr = sdf.format(new Date());
        String filePath = getPath() + sep + dateStr;// eg:${BASE}/headImg/201202/01
        String filePathFinal = checkFile(filePath);
        String allFileName = filePathFinal + sep + System.currentTimeMillis()
                + suffix;
        while (new File(allFileName).exists()) {
            allFileName = filePathFinal + sep + System.currentTimeMillis()
                    + suffix;
        }
        return allFileName;
    }

    /**
     * 得到相对路径
     *
     * @param fullPath
     *            文件全路径
     * @return
     */
    public static String getRelativePath(String fullPath) {
        if (StringUtils.isBlank(fullPath)) {
            return null;
        }
        String base = getPath();
        if (fullPath.startsWith(base)) {
            return fullPath.substring(base.length() + 1);
        }
        return fullPath;
    }

    public static String fileToURL(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        String base = getPath();
        if (filePath.startsWith(base)) {// 得到相对web应用的路径
            filePath = filePath.substring(base.length() + 1);
        }
        // 转化成url形式
        String fileUrl = filePath.replaceAll("\\\\", "/");

        return fileUrl;
    }

    public static String getFullUrl(String relativeUrl) {
        return getUploadUrl() + relativeUrl;
    }

    public static String getAbsolutePath(String relativeUrl){
        String filePath = getPath() + File.separator + relativeUrl;
        return filePath;
    }

    private static String checkFile(String filePath) {
        int fileCount = 0;
        File dir = new File(filePath);
        if (dir.exists() && dir.isDirectory()) {
            String[] subFiles = dir.list();
            if (subFiles != null) {
                fileCount = subFiles.length;
            }
        } else {
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!dir.isDirectory()) {
                if (dir.delete()) {
                    dir.mkdirs();
                }
            }
        }
        if (fileCount >= FILES_PERPAGE) {// 超过最大限制文件数后，新创建文件夹
            filePath += "0";
            return checkFile(filePath);
        }
        return filePath;
    }

    /**
     * 删除单个文件
     * @param   sPath	被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean deleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
}
