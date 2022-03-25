import Base.MyFactory;
import stream.User;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        int[][] i = new int[][]{{100,200,100},{200,50,200},{100,200,100}};
        Simple question = (Simple) QuestionFactory.getQuestion(Simple.class);
        int[][] ints = question.imageSmoother(i);
        for (int i1 = 0; i1 < ints.length; i1++) {
            for (int j = 0 ; j < ints[0].length; ++j) {
                System.out.print(ints[i1][j] + " ");
            }
            System.out.println();
        }
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

    /**
     * 分组后取最大
     */
    public void groupMax() {
        List<Map> ab07s = new ArrayList();
        Map map = new HashMap();
        map.put("110", "202108");
        map.put("210", "202108");
        map.put("110", "202109");
        map.put("210", "202109");
        map.put("110", "202110");
        map.put("210", "202110");

        Map<Object, Map> aae140MaxIssNum = ab07s.stream().filter(i -> "10".equals(i.get("aaa115"))).collect(Collectors.groupingBy(
                i -> i.get("aae140"), Collectors.collectingAndThen(
                        Collectors.reducing((c1, c2) ->  (
                                (c1.get("aae003") != null && c2.get("aae003") != null)) && Integer.parseInt(c1.get("aae003").toString()) >  Integer.parseInt(c2.get("aae003").toString()) ? c1 : c2
                        ), Optional::get
                )));
        for (Object i : aae140MaxIssNum.keySet()) {
            String s = (String) i;
            System.out.println(s + aae140MaxIssNum.get(s));
        }
    }

    public void s () {
        System.out.println("哈哈哈哈");
    }
}
