/**
 * @Author Yanghz
 * @Since 2022/2/25
 * @Description 题型工厂类
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class QuestionFactory {

    public static Object getQuestion(Class question) {

        try {
            if (Question.class.isAssignableFrom(question)) {
                return question.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
