package org.yxs.medusa.annotation;

import org.yxs.medusa.constant.Message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
    String clazz() default "org.yxs.medusa.validate.NotEmptyValidate";
    String value() default Message.NOT_EMPTY;
}
