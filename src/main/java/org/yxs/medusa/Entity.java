package org.yxs.medusa;

import java.lang.annotation.Annotation;

/**
 * Created by 一线生 on 2016/5/22.
 *
 */
public class Entity {
    private Class<? extends Annotation> annotation;
    private Object fieldName;
    private Object value;

    public Entity(Class<? extends Annotation> annotation, Object fieldName, Object value) {
        this.annotation = annotation;
        this.fieldName = fieldName;
        this.value = value;
    }

    public Class<? extends Annotation> getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public Object getFieldName() {
        return fieldName;
    }

    public void setFieldName(Object fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

