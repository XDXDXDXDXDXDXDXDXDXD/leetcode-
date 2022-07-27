package base.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Yanghz
 * @Since 2022/7/21
 * @Description 处理文件工具类
 */
public class FileUtil {

    public static String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
//             01.FileInputStream（字节流） 实现了InputStream接口，用来读取文件中的字节流，参数是文件或者文件路径+文件名称
            FileInputStream fileInputStream = new FileInputStream(file);

            // 02.将 fileInputStream（字节流） 流作为参数，转为InputStreamReader（字符流）
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");

            // 03.将 字符流（参数）转为字符串流，带缓冲的流读取，默认缓冲区8k
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String tempString;

            while ((tempString = bufferedReader.readLine()) != null) {// 直接返回读取的字符串
                // 将字符串 添加到 stringBuilder中
                stringBuilder.append(tempString);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /*
        查询xml中 <select id="xx"格式的重复id数量
     */
    public void countDupIdInXml() {
        File file = new File("G:\\company_project\\treatmentApproval.xml");
        String s = readFile(file);

        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        StringBuilder builderTemp = new StringBuilder();
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '"' && chars[i - 1] == '=' && chars[i - 2] == 'd' && chars[i - 3] == 'i') {
                i++;
                while (chars[i] != '"') {
                    builderTemp.append(chars[i++]);
                }
                list.add(builderTemp.toString());
                builderTemp = new StringBuilder();
            }
        }
//        System.out.println(list);
        List<Integer> list2 = new ArrayList<>();
        for (String i : list) {
            list2.add(Collections.frequency(list, i));
        }

        long count = list2.stream().filter(i -> i != 1).count();
        System.out.println(count);
    }
}
