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

/*
sql. 地址：navicat localhost_5432.test.public.providentFundAccount
 */

/*

-- 表结构
create table ProvidentFundAccount(
issue INTEGER, --期号
money DECIMAL, --缴存金额
months INTEGER, --缴存月份数
total DECIMAL, --账户总金额
maxLoan DECIMAL --当前可公积金贷款金额
);

comment on column providentFundAccount.issue is '期号';
comment on column providentFundAccount.money is '缴存金额';
comment on column providentFundAccount.months is '缴存月份数（自开始缴存起逐月递增）';
comment on column providentFundAccount.total is '账户总金额';
comment on column providentFundAccount.maxLoan is '当前可公积金贷款金额';

-- 序列、触发器
create sequence SEQ_MONTHS
minvalue 1
maxValue 9999
increment 1
start with 1;

create sequence SEQ_ACCOUNTID
minvalue 1
maxValue 9999
increment 1
start with 1;

CREATE OR REPLACE TRIGGER TRG_MONTHS
BEFORE INSERT ON p
FOR EACH ROW
BEGIN
SELECT seq_user_id.nextval INTO :new.id FROM dual;
END;

create trigger tri_calculateMaxLoan after INSERT
on providentFundAccount
for each row
begin
UPDATE providentfundaccount a
SET maxloan = ( SELECT SUM ( money * months * 0.9 ) FROM providentfundaccount b WHERE b.months <= a.months )
where

--⭐⭐⭐⭐⭐计算可公积金贷款金额⭐⭐⭐⭐⭐
UPDATE providentfundaccount a
SET maxloan = ( SELECT SUM ( money * months * 0.9 ) FROM providentfundaccount b WHERE b.months <= a.months )

-- 触发器（待定）
create or replace trigger tri_calculateMaxLoan
after insert
on providentFundAccount
for each row -- 行级触发器
-- 每次新增公积金缴存数据都计算下可贷款金额
begin
  UPDATE providentfundaccount a
  SET maxloan = ( SELECT SUM ( money * months * 0.9 ) FROM providentfundaccount b WHERE b.months <= a.months );
end;

 */


/*
--数据
insert into providentFundAccount values(202007, 780, 1, 780, null);
insert into providentFundAccount values(202008, 780, 2, 1560, null);
insert into providentFundAccount values(202009, 780, 3, 2340, null);
insert into providentFundAccount values(202010, 780, 4, 3120, null);
insert into providentFundAccount values(202011, 780, 5, 3900, null);
insert into providentFundAccount values(202012, 780, 6, 4680, null);
insert into providentFundAccount values(202101, 780, 7, 5460, null);
insert into providentFundAccount values(202102, 780, 8, 6240, null);
insert into providentFundAccount values(202103, 780, 9, 7020, null);
insert into providentFundAccount values(202104, 780, 10, 7800, null);
insert into providentFundAccount values(202105, 960, 11, 8760, null);
insert into providentFundAccount values(202106, 960, 12, 9720, null);
insert into providentFundAccount values(202107, 960, 13, 10680, null);
insert into providentFundAccount values(202108, 960, 14, 11640, null);
insert into providentFundAccount values(202109, 960, 15, 12600, null);
insert into providentFundAccount values(202110, 960, 16, 13560, null);
insert into providentFundAccount values(202111, 960, 17, 14520, null);
insert into providentFundAccount values(202112, 960, 18, 15480, null);
insert into providentFundAccount values(202201, 960, 19, 16440, null);
insert into providentFundAccount values(202202, 960, 20, 17400, null);
insert into providentFundAccount values(202203, 960, 21, 18360, null);
insert into providentFundAccount values(202204, 960, 22, 19320, null);
insert into providentFundAccount values(202205, 1140, 23, 20527.35, null);
 */