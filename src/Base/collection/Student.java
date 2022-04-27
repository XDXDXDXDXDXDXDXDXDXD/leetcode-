package Base.collection;

import java.util.Objects;

/**
 * @author Yanghz
 * @date 2021/8/25
 * @description
 */

class Student implements Comparable<Student>{

    private String name;
    private int score;
    private int age;

    public Student (String name, int score, int age) {
        this.name = name;
        this.score = score;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        Objects.requireNonNull(o);
        return this.getScore() - o.getScore();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return this.getName() + "\t" + this.getAge() + "岁，" + this.getScore() + "分";
    }
}
