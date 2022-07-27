package base.functionalInterface;

import base.stream.User;

import java.util.function.Supplier;

/**
 * @Author Yanghz
 * @Since 2022/6/10
 * @Description 简单的supplier工厂
*/
public class MySupplier {

    public User simpleFactory() {

        Supplier<User> uesrSupplier = User::new;
        return uesrSupplier.get();
    }
}
