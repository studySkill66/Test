//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hksy.framework.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {
    public ClassUtil() {
    }

    public static void main(String[] args) throws Exception {
        String packageName = "org.yobtc.dao";
        List<String> classNames = getClassName(packageName, true);
        if (classNames != null) {
            Iterator var3 = classNames.iterator();

            while(var3.hasNext()) {
                String className = (String)var3.next();
                System.out.println(className);
            }
        }

    }

    public static List<String> getClassName(String packageName) {
        return getClassName(packageName, true);
    }

    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), (List)null, childPackage);
            } else if (type.equals("jar")) {
                fileNames = getClassNameByJar(url.getPath(), childPackage);
            }
        } else {
            fileNames = getClassNameByJars(((URLClassLoader)loader).getURLs(), packagePath, childPackage);
        }

        return fileNames;
    }

    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        File[] var6 = childFiles;
        int var7 = childFiles.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            File childFile = var6[var8];
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.replaceAll("/", "\\\\");
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }

    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
        List<String> myClassName = new ArrayList();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String packagePath = jarInfo[1].substring(1);

        try {
            JarFile jarFile = new JarFile(jarFilePath);
            Enumeration entrys = jarFile.entries();

            while(entrys.hasMoreElements()) {
                JarEntry jarEntry = (JarEntry)entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }

                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        }

        return myClassName;
    }

    private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList();
        if (urls != null) {
            for(int i = 0; i < urls.length; ++i) {
                URL url = urls[i];
                String urlPath = url.getPath();
                if (!urlPath.endsWith("classes/")) {
                    String jarPath = urlPath + "!/" + packagePath;
                    myClassName.addAll(getClassNameByJar(jarPath, childPackage));
                }
            }
        }

        return myClassName;
    }
}
