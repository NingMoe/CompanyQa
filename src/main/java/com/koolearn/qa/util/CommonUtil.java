package com.koolearn.qa.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/**
 * @author lihuiyan
 * @description
 * @date 2016/5/29
 */
public class CommonUtil {
    /**
     * 数组去重
     *
     * @param a
     * @return
     */
    public static String[] array_unique(String[] a) {
        TreeSet<String> set = new TreeSet<>();
        set.addAll(Arrays.asList(a));
        return set.toArray(new String[0]);
    }

    public static List list_unique(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }


}
