package org.yxs.medusa.reflect;

import org.yxs.medusa.Entity;
import org.yxs.medusa.annotation.NotNull;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 一线生 on 2016/5/21.
 */
public class ReflectUtils {

    public static Set<Entity> getSet(Object object) throws IllegalAccessException {
        Set<Entity> set = new HashSet<Entity>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (!field.isAnnotationPresent(NotNull.class)) continue;
            set.add(new Entity(field.getAnnotation(NotNull.class), field.get(object)));
        }
        return set;
    }

    public static <T> T getAnnotation(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            return (T) field.getAnnotation(NotNull.class);
        }
        return null;
    }

    public static String getString(String fieldName) {
        return null;
    }

}
