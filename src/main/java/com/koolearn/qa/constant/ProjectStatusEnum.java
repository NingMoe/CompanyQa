package com.koolearn.qa.constant;

import com.koolearn.qa.generic.GenericEnum;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/9
 */
public enum ProjectStatusEnum  implements GenericEnum {
    normal("正常",1), advance("提前",2), delay("延期",3);
    int value;
    String text;

    ProjectStatusEnum(String text, int value) {
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
        for (ProjectStatusEnum status : ProjectStatusEnum.values()) {
            if (status.getValue() == String.valueOf(value)) {
                return status.text;
            }
        }
        return null;
    }
}
