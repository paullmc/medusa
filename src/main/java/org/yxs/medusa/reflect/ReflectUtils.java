package org.yxs.medusa.reflect;

import org.yxs.medusa.Entity;
import org.yxs.medusa.exception.MedusaException;
import org.yxs.medusa.helper.AnnotationHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class ReflectUtils {

    public static Set<Entity> getSet(Object object) throws IllegalAccessException {
        Set<Entity> set = new HashSet<Entity>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<? extends Annotation> annotationClazz;
            annotationClazz = AnnotationHelper.choice(field);
            if (null == annotationClazz) continue;
            set.add(new Entity(annotationClazz, field.getName(), field.get(object)));
        }
        return set;
    }

    public static Object invokeMethod(String className, String methodName, Object ...args) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?>[] classType = {Object.class, Object.class};
            Method method = clazz.getMethod(methodName, classType);
            return method.invoke(clazz.newInstance(), args);

        } catch (ClassNotFoundException e) {
            throw new MedusaException(e);
        } catch (NoSuchMethodException e) {
            throw new MedusaException(e);
        } catch (InvocationTargetException e) {
            throw new MedusaException(e);
        } catch (IllegalAccessException e) {
            throw new MedusaException(e);
        } catch (InstantiationException e) {
            throw new MedusaException(e);
        }
    }
}
