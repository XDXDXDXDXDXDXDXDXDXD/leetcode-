package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * @Author Yanghz
 * @Since 2022/5/25
 * @Description 买房计算
 */
public class BuyHouse {

    /*
    开始缴存月份
     */
    public static final String startTime = "2020-07-01";

    /*
    工资分段
     */
    public static final int[] segmentSalary = {780, 960, 1140};
    public static final int[] segment = {10, 11};

    /**
     * 获取成都可贷公积金
     * @param balance 当前余额
     * @param curTime  当前月份
     * @return 可贷款额
     */
    public static double getProvidentFund(double balance, String curTime) {

        long betweenMonth = getBetweenMonth(curTime);

        double sum;
        for (int i = 0; i <= betweenMonth; ++i) {
            if (betweenMonth - i + 1 < 10) {

            }
            if (betweenMonth - i + 1 < 11) {

            }

//            sum +=
        }

        return 0d;
    }

    public static long getBetweenMonth(String curTime) {
        Temporal startDate = LocalDate.parse(startTime);
        Temporal endDate = LocalDate.parse(curTime);

        return ChronoUnit.MONTHS.between(startDate, endDate);
    }

    public static void main(String[] args) {
        long betweenMonth = getBetweenMonth("2022-05-25");
        System.out.println(betweenMonth);
    }
}
