package org.yxs.medusa.helper;

import org.yxs.medusa.reflect.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by medusa on 2016/5/23.
 * 注解助手类
 */
public class AnnotationHelper {
    //BASE_PACKAGE 包下的所有class集合
    private static Set<Class<?>> ANNOTATION_SET;
    //初始化要加载的注解包路径
    private final static String BASE_PACKAGE = "org.yxs.medusa.annotation";
    //注解Class 对应 Annotation的map集合
    private static Map<Annotation, Class<? extends Annotation>> ANNOTATION_CLASS_SET = new HashMap<Annotation, Class<? extends Annotation>>();

    static {
        loadAnnotation(BASE_PACKAGE);
    }

    /**
     * author: 一线生
     * explain: 初始化加载所有基础校验注解class
     * @param packageName 要加载的包路径
     * date 2016/5/25 - 11:45
     **/
    private static void loadAnnotation(String packageName) {
        ANNOTATION_SET = ClassUtil.getClassSet(packageName);
    }

    /**
     * author: 一线生
     * explain: 选择属性的校验类型（在基础包下的所有注解）注解
     * @param field 对象属性
     * date 2016/5/25 - 11:46
     **/
    public static Annotation choice(Field field) {
        for (Class<?> clazz : ANNOTATION_SET) {
            Class<? extends Annotation> castClazz = (Class<? extends Annotation>) clazz;
            if (field.isAnnotationPresent(castClazz)) {
                Annotation annotation = field.getAnnotation(castClazz);
                ANNOTATION_CLASS_SET.put(annotation, castClazz);
                return annotation;
            }
        }
        return null;
    }

    /**
     * author: 一线生
     * explain: 根据注解Annotation获取该注解的class 用于反射执行方法
     * @param annotation 注解
     * date 2016/5/25 - 11:47
     **/
    public static Class<? extends Annotation> choice(Annotation annotation) {
        return ANNOTATION_CLASS_SET.get(annotation);
    }
}
