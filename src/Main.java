import algorithm.thread.LeetCodeTest1;
import algorithm.thread.LeetCodeTest2;
import algorithm.thread.LeetCodeTest3;
import algorithm.thread.LeetCode_1114;
import base.Enum.MyEnum;
import base.stream.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private Integer aac026;

    public static void main(String[] args) throws Exception {

        LeetCodeTest1 test1 = new LeetCodeTest1();
        LeetCodeTest2 test2 = new LeetCodeTest2();
        LeetCodeTest3 test3 = new LeetCodeTest3();

        LeetCode_1114 leetCode_1114 = new LeetCode_1114();
        leetCode_1114.third(test3);
        leetCode_1114.first(test1);
        leetCode_1114.second(test2);
    }

    public Integer getAac026() {
        return aac026;
    }

    public void setAac026(Integer aac026) {
        this.aac026 = aac026;
    }

    /**
     * 根据实体类的某个属性去重
     *
     * @param temp 需去重的list
     * @return 已去重list
     */
    public List<User> unRepeated(List<User> temp) {
        // 根据user的name去重
        return temp.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(User::getName))), ArrayList::new)
        );
    }

    /**
     * 分组后取最大
     */
    public void groupMax() {
        List<Map<String, String>> ab07s = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("110", "202108");
        map.put("210", "202108");
        ab07s.add(map);

        Map<Object, Map<String, String>> aae140MaxIssNum = ab07s.stream().filter(i -> "10".equals(i.get("aaa115"))).collect(Collectors.toMap(i -> i.get("aae140"), Function.identity(), (c1, c2) -> (
                (c1.get("aae003") != null && c2.get("aae003") != null)) && Integer.parseInt(c1.get("aae003")) > Integer.parseInt(c2.get("aae003")) ? c1 : c2));
        for (Object i : aae140MaxIssNum.keySet()) {
            String s = (String) i;
            System.out.println(s + aae140MaxIssNum.get(s));
        }
    }
}
