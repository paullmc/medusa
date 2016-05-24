package org.yxs.medusa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by 一线生 on 2016/5/22.
 *
 */
public class Entity {
    private Class<? extends Annotation> annotation;
    private Field field;
    private Object value;

    public Entity(Class<? extends Annotation> annotation, Field field, Object value) {
        this.annotation = annotation;
        this.field = field;
        this.value = value;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

