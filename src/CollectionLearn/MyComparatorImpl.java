package CollectionLearn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yanghz
 * @date 2021/8/25
 * @description
 */
public class MyComparatorImpl implements Comparator<Student> {

    public static void main(String[] args) {
        Student[] students = {
                new Student("小杨", 90, 18),
                new Student("小李", 99, 18),
                new Student("小陈", 78, 18),
                new Student("小张", 78, 20),
        };

//        Arrays.sort(students, new MyComparatorImpl());
//        int count = 1;
//        for (Student i : students) {
//            System.out.println(i.toString() + "排名第" + count++);
//        }

//        List<Student> collect1 = Arrays.stream(students).sorted(new MyComparatorImpl()).collect(Collectors.toList());
//        List<Student> collect = Arrays.stream(students).sorted(Comparator.comparing(Student::getScore)).collect(Collectors.toList());
//        for (Student student : collect) {
//            System.out.println(student.toString());
//        }
//        System.out.println();
//        for (Student student : collect1) {
//            System.out.println(student.toString());
//        }

        Arrays.sort(students);
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    public int compare(Student s1, Student s2) {
        int score1 = s1.getScore();
        int score2 = s2.getScore();
        int age1 = s1.getAge();
        int age2 = s2.getAge();
        if (score1 > score2) {
            return -1;
        } else if (score1 < score2) {
            return 1;
        } else if (age1 >= age2) {
            return 1;
        } else {
            return -1;
        }
    }
}


