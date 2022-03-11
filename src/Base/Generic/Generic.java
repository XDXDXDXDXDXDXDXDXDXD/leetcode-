package Base.Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/*
    类的参数化(泛型化)
    (形式)类型参数T,E(任何字母都可甚至小写)中文编译器会警告
    例如此类使用形式类型参数构建的类,随机获取给定集合中的值
 */
@SuppressWarnings("unused")
public class Generic<T> {

    private final List<T> choiceArray;

    public Generic(Collection<T> choices) {
        choiceArray = new ArrayList<>(choices);
    }

    public T getChoice() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return choiceArray.get(random.nextInt(choiceArray.size()));
    }
}
