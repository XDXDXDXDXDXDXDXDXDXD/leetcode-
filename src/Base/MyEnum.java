package Base;

/**
 * @Author Yanghz
 * @Since 2022/4/18
 * @Description 枚举
 */
public enum MyEnum {

    QINGTONG("青铜", 1000),
    BAIYIN("白银", 2000),
    HUANGJIN("黄金", 3000),
    WANGZHE("王者", 10000),
    ;

    private final int score;
    private final String level;

    public int getScore() {
        return score;
    }

    public String getLevel() {
        return level;
    }

    MyEnum(String level, int score) {
        this.level = level;
        this.score = score;
    }

    @Override
    public String toString() {
        return this.level;
    }

    /**
     * 根据分数获取分段
     */
    public static MyEnum rank(int score) {
        if (score < 1000) {
            return QINGTONG;
        } else if (score < 2000) {
            return BAIYIN;
        } else if (score < 3000) {
            return HUANGJIN;
        } else {
            return WANGZHE;
        }
    }
}
