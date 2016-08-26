package com.koolearn.qa.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;

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

    /**
     * java过滤html标签函数
     *
     * @param htmlStr
     * @return
     */
    public static String html2Text(String htmlStr) {
        htmlStr = htmlStr.replaceAll("</div><div>","\n");
        htmlStr = htmlStr.replaceAll("<div>","\n");
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;
        Pattern p_style;
        java.util.regex.Matcher m_style;
        Pattern p_html;
        java.util.regex.Matcher m_html;

        Pattern p_n;
        java.util.regex.Matcher m_n;

        Pattern p_d;
        java.util.regex.Matcher m_d;
        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

            String regEx_n = "</div><div.*?>|</li><li.*?>";
            String regEx_d = "<div.*?>|<li.*?>|<br.*?>";
            p_n = Pattern.compile(regEx_n, Pattern.CASE_INSENSITIVE);
            m_n = p_n.matcher(htmlStr);
            htmlStr = m_n.replaceAll("\n"); //过滤</div><div>标签
            p_d = Pattern.compile(regEx_d, Pattern.CASE_INSENSITIVE);
            m_d = p_d.matcher(htmlStr);
            htmlStr = m_d.replaceAll("\n"); //过滤<div>标签

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); //过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;//返回文本字符串
    }

}
