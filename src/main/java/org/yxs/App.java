package org.yxs;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;
import org.yxs.medusa.validate.Validate;

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
        System.out.println();
        Validate validate = new Validate();
        Medusa medusa = null;
        long start = new Date().getTime();
        for (int i = 0; i < 30000; i++) {
             medusa = validate.popFail(user);
        }
        System.out.println(medusa);
        System.out.println(new Date().getTime() - start);
    }
}
