package org.yxs.medusa;

/**
 * Created by 一线生 on 2016/5/22.
 */
public class Entity {
    private Object annotation;
    private Object value;

    public Entity(Object annotation, Object value) {
        this.annotation = annotation;
        this.value = value;
    }

    public Object getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Object annotation) {
        this.annotation = annotation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

