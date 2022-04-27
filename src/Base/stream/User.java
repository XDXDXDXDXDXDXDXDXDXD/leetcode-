package Base.stream;

/**
 * @author hanzhi
 * @date 2021/3/31
 */
public class User implements Comparable<User> {

    private String name;

    private Integer age;

    private String region;

    private Integer balance;

    public User(String name, Integer age, String region, Integer balance) {
        this.name = name;
        this.age = age;
        this.region = region;
        this.balance = balance;
    }

    public User() {

    }

    public String toString() {
        return this.name + " " + this.age + " " + this.region + " " + this.balance;
    }

    @Override
    public int compareTo(User user) {
        if (user == null) {
            throw new NullPointerException();
        }
        if (this.balance == user.getBalance()) {
            return this.age - this.age;
        }
        return this.balance - user.getBalance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
