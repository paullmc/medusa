package org.yxs.medusa.annotation;

import org.yxs.medusa.constant.Message;

/**
 * Created by 一线生 on 2016/5/21.
 */
public @interface Null {
    String value() default Message.NULL;
}