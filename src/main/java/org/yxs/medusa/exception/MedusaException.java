package org.yxs.medusa.exception;

/**
 * Created by 一线生 on 2016/5/23.
 *
 */
public class MedusaException extends RuntimeException {

    public MedusaException() {
    }

    public MedusaException(String message) {
        super(message);
    }

    public MedusaException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedusaException(Throwable cause) {
        super(cause);
    }
}
