package Base;

/**
 * @author Yanghz
 * @date 2021/8/31
 * @description 泛型
 */
public class Generic<T> {

    private T name;

    public Generic(T name) {
        assert name != null : "姓名不可为空";
       this.name = name;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        assert false;
        assert name == null : "姓名不可为空";
        this.name = name;
    }
}
