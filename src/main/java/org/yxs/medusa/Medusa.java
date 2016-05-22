package org.yxs.medusa;

/**
 * Created by 一线生 on 2016/5/21.
 */
public class Medusa {
    private boolean flag;
    private String message;

    public Medusa() {}

    public Medusa(boolean flag) {
        this.flag = flag;
    }

    public Medusa(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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
                ", message='" + message + '\'' +
                '}';
    }
}
