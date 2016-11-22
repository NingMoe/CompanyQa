package com.koolearn.qa.service.impl;

import com.koolearn.ldap.dto.LdapUser;
import com.koolearn.ldap.service.LdapService;
import com.koolearn.qa.constant.Constant;
import com.koolearn.qa.dao.platform.MailerMapper;
import com.koolearn.qa.dao.platform.ProductMapper;
import com.koolearn.qa.generic.GenericDao;
import com.koolearn.qa.generic.GenericServiceImpl;
import com.koolearn.qa.model.Mailer;
import com.koolearn.qa.model.Product;
import com.koolearn.qa.model.Project;
import com.koolearn.qa.service.IMailerService;
import com.koolearn.qa.util.CommonUtil;
import com.koolearn.qa.util.PropUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lihuiyan
 * @description
 * @date 2016/7/14
 */
@Service("mailerService")
public class MailerServiceImpl extends GenericServiceImpl<Mailer, Integer> implements IMailerService {

    @Autowired
    private MailerMapper mailerMapper;

    @Autowired
    private LdapService ldapService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public GenericDao<Mailer, Integer> getDao() {
        return mailerMapper;
    }

    @Override
    public Mailer getMailerByProjectId(Integer projectId) {
        return mailerMapper.selectByProjectId(projectId);
    }

    @Override
    public Boolean isExist(Integer projectId) {
        if (mailerMapper.selectByProjectId(projectId) != null)
            return true;
        return false;
    }

    /**
     * 未设置邮件接收人，获取默认接收人邮箱
     * @param project
     * @return
     */
    public Map<String, List<String>> getDefaultMail(Project project) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> toAddress = defaultRecipientsMail(project);
        map.put("recipients", toAddress);
        List<String> ccAddress = defaultCCMail(project);
        ccAddress.removeAll(toAddress);//去除与发送地址相同的数据
        map.put("cc", ccAddress);
        return map;
    }

    /**
     * 未设置邮件接收人，获取默认接收人姓名
     * @param project
     * @return
     */
    public Map<String, String> getDefaultName(Project project) {
        Map<String, String> map = new HashMap<>();
        map.put("recipients", defaultRecipientsName(project));
        map.put("cc", defaultCCName(project));
        return map;
    }

    private String defaultRecipientsName(Project project) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(project.getDeveloper())) {
            sb.append(project.getDeveloper()).append(Constant.COMMA);
        }
        if (StringUtils.isNotBlank(project.getTester())) {
            sb.append(project.getTester()).append(Constant.COMMA);
        }
        if (StringUtils.isNotBlank(project.getProducter())) {
            sb.append(project.getProducter());
        }
        return sb.toString();
    }

    private String defaultCCName(Project project) {
        StringBuffer sb = new StringBuffer();
        List<String> defaultCCMail = defaultCCMail(project);
        if(defaultCCMail == null)
            return null;
        for(String mail:defaultCCMail){
            List<LdapUser> userList = ldapService.queryUser("(mail=" + mail + ")");
            LdapUser userInfo = userList.get(0);
            sb.append(userInfo.getName()).append(Constant.COMMA);
        }
        if(sb.toString().endsWith(Constant.COMMA))
            return sb.substring(0,sb.length()-1);
        return sb.toString();
    }

    private List<String> defaultCCMail(Project project) {
        //抄送人员配置在SystemGloabals文件中
        String cc = PropUtil.getSystemGlobalsProperties("mail.cc");
        List<String> ccAddress = new ArrayList<>(Arrays.asList(cc.split(Constant.COMMA)));
        //负责人添加到抄送人员列表
        Product product = productMapper.selectByPrimaryKey(Integer.valueOf(project.getProductId()));
        ccAddress.addAll(transEmail(product.getLeader()));
        ccAddress = CommonUtil.list_unique(ccAddress);//去重
        return ccAddress;
    }

    private List<String> defaultRecipientsMail(Project project) {
        return transEmail(defaultRecipientsName(project));
    }

    public List<String> transEmail(String str) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isBlank(str)) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        String[] strArray = str.split(Constant.COMMA);
        strArray = CommonUtil.array_unique(strArray);
        for (int i = 0; i < strArray.length; i++) {
            List<LdapUser> userList = ldapService.queryUser("(name=" + strArray[i] + ")");
            if(userList!=null && userList.size()>0){
                LdapUser userInfo = userList.get(0);
                if (userInfo.getEmail().endsWith(Constant.EMAIL_SUFFIX)) {
                    list.add(userInfo.getEmail());
                } else {
                    list.add(userInfo.getEmail() + Constant.EMAIL_SUFFIX);
                }
            }
        }
        return list;
    }
}
