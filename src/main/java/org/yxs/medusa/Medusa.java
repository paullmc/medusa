package org.yxs.medusa;

import java.lang.reflect.Field;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class Medusa {
    private boolean flag;
    private Field field;
    private String message;

    public Medusa() {}

    public Medusa(boolean flag) {
        this.flag = flag;
    }

    public Medusa(boolean flag, Field field, String message) {
        this.flag = flag;
        this.field = field;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Medusa{" +
                "flag=" + flag +
                ", field=" + field +
                ", message='" + message + '\'' +
                '}';
    }
}
