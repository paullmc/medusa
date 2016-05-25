package org.yxs.medusa.reflect;

import org.yxs.medusa.Entity;
import org.yxs.medusa.exception.MedusaException;
import org.yxs.medusa.helper.AnnotationHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class ReflectUtils {

    /**
     * author: 一线生
     * explain: 获取校验对象内属性的所有校验注解放入
     * @param object 待校验的对象
     * @throws  IllegalAccessException 当获取field值获取不到时，抛出该异常
     * date 2016/5/25 - 11:39
     **/
    public static Set<Entity> getSet(Object object) throws IllegalAccessException {
        Set<Entity> set = new HashSet<Entity>();
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation annotation = AnnotationHelper.choice(field);
            if (null == annotation) continue;
            set.add(new Entity(annotation, field, field.get(object)));
        }
        return set;
    }

    /**
     * author: 一线生
     * explain: 获取Object[]内的参数类型
     * @param args invokeMethod 方法的参数值
     * date 2016/5/25 - 11:41
     **/
    private static Class<?>[] getType(Object ...args) {
        if (null == args) {
            return new Class<?>[]{};
        }
        Class<?>[] classType = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            classType[i] = args[i].getClass();
        }
        return classType;
    }

    /**
     * author: 一线生
     * explain: 反射执行方法
     * @param clazz 待执行的class
     * @param args 方法参数
     * @param methodName 方法名
     * @throws  MedusaException
     * date 2016/5/25 - 11:42
     **/
    public static Object invokeMethod(Class<?> clazz, String methodName, Object ...args) {
        try {
            Method method = clazz.getMethod(methodName, getType(args));
            return method.invoke(clazz.newInstance(), args);

        } catch (Exception e) {
            throw new MedusaException(e);
        }
    }
}
