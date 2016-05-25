package org.yxs.medusa.annotation;

import org.yxs.medusa.constant.Message;
import org.yxs.medusa.validate.EmailValidate;

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
public @interface Email {
    Class<?> clazz() default EmailValidate.class;
    String value() default Message.EMAIL;
}
