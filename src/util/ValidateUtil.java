package util;

/**
 * @author Yanghz
 * @date 2021/9/4
 * @description 校验工具类
 */
public class ValidateUtil {

    private static final String REGEXP_ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}(0[1-9]|1[0-2])([0-2][1-9]|10|20|30|31)\\d{3}[0-9Xx]$)";

    /**
     * 校验是否为身份证
     */
    public static boolean isIdCard(String card) {

        boolean flag = card.matches(REGEXP_ID_CARD);
        if (flag) {
            char[] cardArray = card.toCharArray();
            // 前十七位加权因子
            int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            // 加权除以11后，可能产生的11位余数对应的验证码
            String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
            int sum = 0;
            for (int i = 0; i < idCardWi.length; i++) {
                sum += Integer.parseInt(String.valueOf(cardArray[i])) * idCardWi[i];
            }
            int res = sum % 11;
            // 取余后对应校验码
            return idCardY[res].equals(String.valueOf(cardArray[17]));
        }
        return false;
    }
}
