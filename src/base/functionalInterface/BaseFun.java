package base.functionalInterface;

/**
 * @Author Yanghz
 * @Since 2022/3/12
 * @Description
 */
public class BaseFun {

    @FunctionalInterface
    interface Process {
        int getStringLength(String s);
    }

    public static void main(String[] args) {
        Process p = (String s) -> s.length() * 2;
        System.out.println(p.getStringLength("srffwe"));
    }
}
