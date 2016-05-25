package org.yxs.medusa.reflect;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by 一线生 on 2015/11/24.
 * 说明：
 */
public class ClassUtil {
    /**
     * 获取当前线程中的ClassLoader类加载器
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("load class failure", e);
        }
        return clazz;
    }

    /**
     * 获取基础包下的所有类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();

        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while(urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if(null != url) {
                    String protocol = url.getProtocol();
                    if("file".equals(protocol)) {
                        String packagePath = url.getPath().replaceAll("20%", "");
                        addClass(classSet, packagePath, packageName);
                    }
                    else if("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(null != jarURLConnection) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(null != jarFile) {
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while(jarEntryEnumeration.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/",".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("get class set failure", e);
        }
        return classSet;
    }

    /**
     * 将基础包下的所有class放入classSet
     * @param classSet
     * @param packagePath
     * @param packageName
     */
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for(File file : files) {
            String fileName = file.getName();
            if(file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if(!isBlank(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if(!isBlank(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if(!isBlank(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 加载类并放入classSet
     * @param classSet
     * @param className
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> clazz = loadClass(className, false);
        classSet.add(clazz);
    }

    /**
     * author: 一线生
     * explain: 判断字符串是否为null、""、" "
     * @param arg 待判断的字符串
     * date 2016/5/13 - 18:36
     **/
    public static boolean isBlank(String arg) {
        return null == arg || ("".equals(arg.replace(" ", "")));
    }

}
