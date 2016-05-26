package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Email;

import java.util.regex.Pattern;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class EmailValidate extends AbstractValidate<Email> {

    private final static Pattern EMAIL_PATTERN = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    public boolean validate(Object object) {
        if (null == object) return false;
        return EMAIL_PATTERN.matcher(String.valueOf(object)).matches();
    }

    public void init() {
        this.setMsg(annotation.value());
    }
}
