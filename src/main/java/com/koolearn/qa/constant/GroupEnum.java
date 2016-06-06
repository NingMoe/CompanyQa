package com.koolearn.qa.constant;

import com.koolearn.qa.generic.GenericEnum;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/7
 */
public enum GroupEnum implements GenericEnum {
    B("B线开发组", 1), php("php开发组", 2), sharkFront("鲨鱼前端组", 3), service("服务开发组", 4), media("媒体服务开发组", 5), course("课堂开发组", 6), sharkBack("鲨鱼开发组", 7), mobi("移动创新实验室", 8), framework("架构组", 9), qa("测试组", 10), crm("数据分析与CRM开发组", 11), dba("运维组", 12);
    int value;
    String text;

    GroupEnum(String text, int value) {
        this.value = value;
        this.text = text;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public String getText() {
        return text;
    }

    public static String getText(int value) {
        for (GroupEnum group : GroupEnum.values()) {
            if (group.getValue() == String.valueOf(value)) {
                return group.text;
            }
        }
        return null;
    }

}
