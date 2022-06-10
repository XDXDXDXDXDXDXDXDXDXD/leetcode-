import base.stream.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        Pattern reg = Pattern.compile("( ac01 )|( ac02 )|( ab01 )|( ab02 )|( ae01 )");
        BufferedReader reader = new BufferedReader(new FileReader("./temp.txt"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            Matcher matcher = reg.matcher(line);
            int count = matcher.groupCount();
            while (matcher.find()) {
                for (int i = 1; i <= count; ++i) {

                }
            }
        }
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
