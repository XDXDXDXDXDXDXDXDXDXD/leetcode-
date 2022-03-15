package Base.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Yanghz
 * @Since 2022/3/11
 * @Description 使用原生态类型时(如List)常见的编译器警告unchecked call to xxx;应该使用参数化类型(List<Object>)
 */
@SuppressWarnings("unused")
public class UncheckedCall {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        addUncheckedEle(list, 4);
        String s = list.get(0);     // 可以编译通过但是运行时将会报错:ClassCastException

//        addEle(list, Integer.valueOf(2));     // 编译将不会不通过并报错List<String>无法转换为List<Object>
    }

    // 可以使用suppressWarning注解来消除警告,但是这是不安全的.例如在此消除警告看起来安全了,但实际
    // 在main方法中调用时会报错
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void addUncheckedEle(List list, Integer i) {
        list.add(i);
    }
    private static void addEle(List<Object> list, Integer i) {
        list.add(i);
    }

    /**
     * <i>无限制的通配符类型</i>,当不关心集合中类型时使用,<i>不可项Collection<?>中加入元素(除null)</?></></i>
     */
    private static int unBoundedWildcardType(List<?> list1, List<?> list2) {

        int res = 0;
        for (Object i : list1) {
            if (list2.contains(i)) {
                res++;
            }
        }
        return res;
    }
}
