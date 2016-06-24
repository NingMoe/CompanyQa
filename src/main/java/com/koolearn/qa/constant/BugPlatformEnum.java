package com.koolearn.qa.constant;

import com.koolearn.qa.generic.GenericEnum;

/**
 * @author lihuiyan
 * @description
 * @date 2016/6/22
 */
public enum BugPlatformEnum implements GenericEnum {
    mantis("mantis", 1), jira("jira", 2);
    int value;
    String text;

    BugPlatformEnum(String text, int value) {
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
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getValue() == String.valueOf(value)) {
                return status.text;
            }
        }
        return null;
    }
}
