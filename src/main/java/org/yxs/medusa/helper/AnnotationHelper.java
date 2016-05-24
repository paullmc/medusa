package org.yxs.medusa.helper;

import org.yxs.medusa.reflect.ClassUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by medusa on 2016/5/23.
 *
 */
public class AnnotationHelper {

    private static Set<Class<?>> ANNOTATION_SET;
    private final static String BASE_PACKAGE = "org.yxs.medusa.annotation";
    private static Map<Annotation, Class<? extends Annotation>> ANNOTATION_CLASS_SET = new HashMap<Annotation, Class<? extends Annotation>>();

    static {
        loadAnnotation(BASE_PACKAGE);
    }

    private static void loadAnnotation(String packageName) {
        ANNOTATION_SET = ClassUtil.getClassSet(packageName);
    }

    public static Set<Class<?>> getAnnotationSet() {
        return ANNOTATION_SET;
    }

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

    public static Class<? extends Annotation> choice(Annotation annotation) {
        return ANNOTATION_CLASS_SET.get(annotation);
    }
}
