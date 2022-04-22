package Base;

import util.ValidateUtil;

import java.io.Serializable;

/**
 * @Author Yanghz
 * @Since 2022/2/14
 * @Description deepClone
 */
public class MyClone implements Cloneable, Serializable {

    private String name;
    private MyCloneSub myCloneSub;

    @Override
    public Object clone() {

        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "name: " + name +
                "\nmyCloneSub:" +(ValidateUtil.isEmpty(myCloneSub) ? "" : myCloneSub.getName() + myCloneSub.getSerial());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyCloneSub getMyCloneSub() {
        return myCloneSub;
    }

    public void setMyCloneSub(MyCloneSub myCloneSub) {
        this.myCloneSub = myCloneSub;
    }



    public static class MyCloneSub implements Cloneable {

        private int serial;
        private String name;

        public MyCloneSub(int serial, String name) {
            this.serial = serial;
            this.name = name;
        }

        public int getSerial() {
            return serial;
        }

        public void setSerial(int serial) {
            this.serial = serial;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
