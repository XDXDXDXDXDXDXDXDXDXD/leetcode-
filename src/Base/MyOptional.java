package Base;

import java.util.*;

/**
 * @Author Yanghz
 * @Since 2022/4/13
 * @Description Optional
 */
public class MyOptional {

    public static Integer getMapMin(List<Map<String, Integer>> list, String key) {
        Optional<Map<String, Integer>> minOp = list.stream().min(Comparator.comparing(i -> i.get(key)));
        return minOp.isPresent() ? minOp.get().get(key) : 0;
    }
}
