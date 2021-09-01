package Base;

import stream.StreamOperation;

/**
 * @author Yanghz
 * @date 2021/8/31
 * @description
 */
public class MyFactory<T> {

    public static StreamOperation getAStreamOperation() {
        return new StreamOperation();
    }
}
