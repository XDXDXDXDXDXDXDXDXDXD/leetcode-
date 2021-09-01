import stream.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        Supplier<String> supplier = () -> "hello world";
    }

    /**
     * 根据实体类的某个属性去重
     * @param temp 需去重的list
     * @return 已去重list
     */
    public List<User> unRepeated(List<User> temp) {
        // 根据user的name去重
        return temp.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(User::getName))), ArrayList::new)
        );
    }
}
