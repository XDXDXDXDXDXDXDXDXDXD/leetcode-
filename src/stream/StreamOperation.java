package stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 练习流操作
 *
 * @author hanzhi
 * @date 2021/5/22
 */
public class StreamOperation {

    private List<User> users = new ArrayList<>();

    private static final List<Integer> pageSimulation = new ArrayList<>(5000);

    static {
        Random random = new Random(5000);
        for (int i = 0; i < 5000; i++) {
            pageSimulation.add(random.nextInt());
        }
    }

    public StreamOperation() {
        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            User user;
            user = new User((i & 1) == 1 ? "小杨" : "小李", random.nextInt(100), "北京", random.nextInt(20000));
            this.users.add(user);
        }
    }

    public static void main(String[] args) {
        StreamOperation eg = new StreamOperation();
        System.out.println(eg);
        Map<Boolean, List<User>> map = eg.partitioning();
        for (Map.Entry<Boolean, List<User>> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().forEach(System.out::print);
            System.out.println();
        }
    }

    /**
     * 平均值
     * @return
     */
    public Double getAverage() {
        return users.stream().collect(Collectors.averagingInt(User::getAge));
    }

    /**
     * 最大值 *
     * @return
     */
    public Optional<User> getMax() {
        return users.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
    }

    /**
     * 最小值
     * @return
     */
    public Optional<User> getMin() {
        return users.stream().collect(Collectors.minBy(Comparator.naturalOrder()));
    }

    public Map<String, List<User>> group() {
        return this.users.stream().collect(Collectors.groupingBy(User::getName));
    }

    /**
     * 过滤
     * @return
     */
    public Map<Integer,List<User>> filter() {
        // 将30岁以上的用户按年龄分组
        return this.users.stream().filter(i -> i.getAge() > 30).collect(Collectors.groupingBy(User::getAge));
    }

    /**
     * 映射
     * @return
     */
    public List<User> map() {
        return this.users.stream().map(i -> {i.setRegion("chengdu"); return i;}).collect(Collectors.toList());
    }

    /**
     * 自然排序 （流中元素实现Comparable接口）
     * @return
     */
    public void sort() {
        this.users = this.users.stream().sorted(User::compareTo).collect(Collectors.toList());
    }

    /**
     * 定制排序（Comparator）
     * @return
     */
    public void CusSort() {
        this.users = this.users.stream().sorted((o1, o2) -> {
            if (Objects.equals(o1.getBalance(), o2.getBalance())) {
                return o1.getAge() - o2.getAge();
            } else {
                return o1.getBalance() - o2.getBalance();
            }
        }).collect(Collectors.toList());
    }

    /**
     * 匹配
     */
    public Boolean allMatch() {
        return this.users.stream().allMatch(i -> i.getAge() > 20);
    }
    public Boolean nonMatch() {
        return this.users.stream().noneMatch(i -> i.getAge() < 20);
    }
    public Boolean anyMatch() {
        return this.users.stream().anyMatch(i -> i.getAge() == 20);
    }
    public User findAny() {
        return this.users.stream().findAny().get();
    }
    /** 重要 **/
    public User max() {
        return this.users.stream().max(User::compareTo).get();
//        return this.users.stream().max(Comparator.comparingInt(User::getBalance)).get();
    }

    /**
     * 规约
     * @return
     */
    // 累加器; identity: 初始值
    public Integer reduce() {
        // 获取所有用户余额总和
        return this.users.stream().map(User::getBalance).reduce(Integer::sum).get();
    }

    // 去重
    public void deDuplication() {
        this.users = this.users.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(User::getName))), ArrayList::new)
        );
    }

    /**
     * 收集
     * @return
     */
    public Map<String, Integer> toMap() {
        return this.users.stream().collect(Collectors.toMap(User::getName, User::getBalance));
    }

    // String
    public String nameToString() {
        return this.users.stream().map(User::getName).collect(Collectors.joining(", "));
    }

    // 分组
    public Map<String, List<User>> groupByName() {
        return this.users.stream().collect(Collectors.groupingBy(User::getName));
    }

    /* 应用 */

    // 获取余额大于8000的用户
    public List<User> getUserWithBalanceOver8000() {
        return this.users.stream().filter(i -> i.getBalance() > 8000).collect(Collectors.toList());
    }
    // 获取余额最大的用户
    public User getUserWithMaxBalance() {
        return this.users.stream().max(User::compareTo).get();
    }
    // 获取用户的平均余额
    public Double getUserAverageBalance() {
        return this.users.stream().collect(Collectors.averagingDouble(User::getBalance));
    }
    // 获取用户薪资之和
    public Integer getUserSumBalance() {
        return this.users.stream().mapToInt(User::getBalance).sum();
    }
    // 将用户余额按5000分区
    public Map<Boolean, List<User>> partitioning() {
        return this.users.stream().collect(Collectors.partitioningBy(i -> i.getBalance() > 5000));
    }
    // 按姓氏分组
    public Map<String, List<String>> grougByFirstName(List<String> names) {
        return names.stream().collect(Collectors.groupingBy(i -> i.substring(0, 1)));
    }


    /**
     * page simulation
     * @param pageSize 一页多少条
     * @param pageNum 当前第几页
     * @return
     */
    public List<Integer> pageSimulation (Integer pageSize, Integer pageNum) {
        if (pageSize * pageNum > pageSimulation.size()) {
            throw new IllegalArgumentException();
        }
        return pageSimulation.stream().skip(pageNum - 1).limit(pageSize).collect(Collectors.toList());
    }
    // 取给定数组中绝对值输出
    public void getAbs(List<Integer> list) {
        list.stream().map(Math::abs).forEach(System.out::print);
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for (User user : this.users) {
            b.append(user.getName() + "\t" + user.getAge() + "\t" + user.getRegion() + "\t" + user.getBalance());
            b.append("\n");
        }
        return b.toString();
    }
}
