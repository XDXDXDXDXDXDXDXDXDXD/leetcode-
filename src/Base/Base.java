package Base;

import stream.StreamOperation;

import java.util.List;

/**
 * @author hanzhi
 * @date 2021/8/8
 */
public class Base {

    public static void main(String[] args) {
        StreamOperation operation = MyFactory.getAStreamOperation();
        int pageSize = 100;
        for (int pageNum = 1; pageNum <= 100; ++pageNum) {
            try {
                System.out.println("当前" + pageNum + "页");
                List<Integer> list = operation.pageSimulation(pageSize, pageNum);
                list.forEach(System.out::println);
            } catch (IllegalArgumentException e) {
                System.out.println("超速了!");
                break;
            }
        }
    }

}
