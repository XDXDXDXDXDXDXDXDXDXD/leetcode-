package util;

import java.util.Objects;

/**
 * @Author Yanghz
 * @Since 2022/4/20
 * @Description 处理空值工具类
 */
public class NPEUtil {

    public static String isEmptyReturnString(Object obj) {
        return Objects.isNull(obj) ? "" : obj.toString();
    }
}
