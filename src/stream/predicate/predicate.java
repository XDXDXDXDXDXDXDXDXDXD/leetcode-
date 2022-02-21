package stream.predicate;

import java.util.function.Predicate;

/**
 * @author Yanghz
 * @date 2021/9/1
 * @description
 */
public class predicate {

    // 正整数
    private static final Predicate<Integer> positiveInt = (i) -> i >= 0;
    // 大于100的整数
    private static final Predicate<Integer> maxThanHundred = (i) -> Math.abs(i) >= 100;
    // 偶数
    private static final Predicate<Integer> even = (i) -> (i & 1) == 0;

    public static Boolean isEven(Integer i) {
        return even.test(i);
    }

    public static Boolean isPositiveInt(Integer i) {
        return positiveInt.test(i);
    }

    public static Boolean isMaxThanHundred(Integer i) {
        return maxThanHundred.test(i);
    }
}
