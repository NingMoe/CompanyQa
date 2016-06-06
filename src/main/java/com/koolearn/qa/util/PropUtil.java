package com.koolearn.qa.util;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropUtil {

    /**
     * get property from properties file
     *
     * @param name     :property name
     * @param propPath :properties file path without suffix
     * @return
     */
    public static String getProperty(String propPath, String name) {
        Properties p = new Properties();
        try {
            p.load(new FileReader(new File(propPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p.getProperty(name);
    }


    /**
     * @param propPath 配置文件路径
     * @return
     * @function: 获取配置文件里面所有的（属性：值）对
     * @date:
     * @author:
     */
    public static Map<String, String> getProperties(String propPath) {
        Map<String, String> kvMap = new HashMap<String, String>();
        Properties p = new Properties();
        try {
            p.load(new FileReader(new File(propPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String name : p.stringPropertyNames()) {
            kvMap.put(name, p.getProperty(name));
        }

        return kvMap;
    }

    /**
     * 获取systemGlobals文件的属性值
     * @param name
     * @return
     */
    public static String getSystemGlobalsProperties(String name) {
        String path = PropUtil.class.getResource("/").getPath()+"systemGlobals.properties";
        return getProperty(path,name);
    }
}
