package com.tuniu.vla.base.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by chengyao on 2015/11/16.
 */
public class ClassUtils {

    /**
     * 判断给定type是否是Collection的子类，如果是，找出Collection的泛型类返回
     * @param type 入参
     * @return 返回泛型类
     */
    public static Class<?> findCollectionSubClass(Type type) {
        if (type instanceof Class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (rawType instanceof Class && Collection.class.isAssignableFrom((Class<?>) rawType)) {
                Type t = parameterizedType.getActualTypeArguments()[0];
                if (t instanceof Class) {
                    return (Class) t;
                }
            }
        }
        return null;
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> findClasses(String packageName, boolean recursive) {
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    JarFile jar = ((JarURLConnection) url.openConnection())
                            .getJarFile();
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        if (name.charAt(0) == '/') {
                            name = name.substring(1);
                        }
                        if (name.startsWith(packageDirName)) {
                            int idx = name.lastIndexOf('/');
                            if (idx != -1) {
                                packageName = name.substring(0, idx).replace('/', '.');
                            }
                            if ((idx != -1) || recursive) {
                                if (name.endsWith(".class")
                                        && !entry.isDirectory()) {
                                    String className = name.substring(
                                            packageName.length() + 1, name
                                                    .length() - 6);
                                    try {
                                        classes.add(Class
                                                .forName(packageName + '.'
                                                        + className));
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e.getMessage(), e);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return classes;
    }


    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName 搜索包名
     * @param packagePath 路径
     * @param recursive 是否遍历
     * @param classes 保存类
     */
    private static void findAndAddClassesInPackageByFile(String packageName,
                                                         String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        if (dirfiles == null) {
            return;
        }
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }
}
