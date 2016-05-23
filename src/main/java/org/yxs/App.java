package org.yxs;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;
import org.yxs.medusa.validate.Validator;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static class User {

        @NotNull
        private String uname;

        @NotNull
        private String passwd;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
    }
    public static void main( String[] args ) {
        User user = new User();
        user.setUname("asda");
        user.setPasswd("asdasd");
        System.out.println();
        Validator validator = new Validator();
        long start = new Date().getTime();
        Medusa medusa = validator.popDeny(user);
        System.out.println(medusa);
        System.out.println(new Date().getTime() - start);
    }
}
