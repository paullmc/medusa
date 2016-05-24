package org.yxs;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.*;
import org.yxs.medusa.annotation.Number;
import org.yxs.medusa.validate.Validator;

import java.util.Date;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static class User {

        @NotNull
        private String uname;

        @Null
        private String passwd;

        @Email
        private String email;

        @NotEmpty
        private String empty;

        @NotBlank
        private String blank;

        @Number
        private String number;

        @Pattern(pattern = "^1")
        private String pattern;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmpty() {
            return empty;
        }

        public void setEmpty(String empty) {
            this.empty = empty;
        }

        public String getBlank() {
            return blank;
        }

        public void setBlank(String blank) {
            this.blank = blank;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }
    public static void main( String[] args ) {
        User user = new User();
        user.setPasswd("asdasd");
        user.setUname("asd");
        user.setEmail("97545465@163.com");
        user.setEmpty("");
        user.setBlank("");
        user.setNumber("1");
        user.setPattern("12");
        System.out.println();
        Validator validator = Validator.newInstance(user);
        long start = new Date().getTime();
        Set<Medusa> set = validator.result();
        for (Medusa medusa : set) {
            System.out.println(medusa.toString());
        }
        System.out.println(new Date().getTime() - start);
    }
}
