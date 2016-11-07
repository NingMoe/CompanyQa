package com.koolearn.qa.util;

import java.io.*;
import java.util.HashMap;
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
        Map<String, String> map = getProperties(propPath);
        return map.get(name);
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
            String projectPath = PropUtil.class.getClassLoader().getResource("").getPath();
            String configPath = java.net.URLDecoder.decode(projectPath,"utf-8");
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(new File(configPath+propPath)),"UTF-8"));
            p.load(br);
            br.close();
        }
        catch (Exception e) {
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
        return getProperty("systemGlobals.properties",name);
    }
}
