package org.yxs.medusa.helper;

import org.yxs.medusa.reflect.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by medusa on 2016/5/23.
 *
 */
public class AnnotationHelper {

    private static Set<Class<?>> ANNOTATION_SET;
    private final static String BASE_PACKAGE = "org.yxs.medusa.annotation";

    static {
        loadAnnotation(BASE_PACKAGE);
    }

    private static void loadAnnotation(String packageName) {
        ANNOTATION_SET = ClassUtil.getClassSet(packageName);
    }

    public static Set<Class<?>> getAnnotationSet() {
        return ANNOTATION_SET;
    }

    public static Class<? extends Annotation> choice(Field field) {
        for (Class<?> clazz : ANNOTATION_SET) {
            Class<? extends Annotation> castClazz = (Class<? extends Annotation>) clazz;
            if (field.isAnnotationPresent(castClazz)) {
                return castClazz;
            }
        }
        return null;
    }
}
