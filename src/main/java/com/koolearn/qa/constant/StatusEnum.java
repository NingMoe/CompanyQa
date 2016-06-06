package com.koolearn.qa.constant;

import com.koolearn.qa.generic.GenericEnum;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/8
 */
public enum StatusEnum implements GenericEnum {
    enabled("有效", 1), disabled("无效", 2), all("全部", 0);
    int value;
    String text;

    StatusEnum(String text, int value) {
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
