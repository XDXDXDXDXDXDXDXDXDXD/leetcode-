import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Sort {
    private int [] a = {3,14,4,1,13,9,535};
    public static void main(String[] args) {
        //选择排序
        /*long start = System.nanoTime();
        int [] a = {3,14,4,1,13,9,535};
        for(int i = 0;i<a.length;i++){
            for(int j = i+1;j<a.length;j++){
                if (a[i]>a[j]){
                    int temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
        for (int temp:a
             ) {
            System.out.print(temp+"<");
        }
        System.out.println();
        long end = System.nanoTime();
        System.out.println(end-start); // 选择排序  时间：338301*/


        //冒泡排序
        /*long start = System.nanoTime();
        int [] a = {3,14,4,1,13,9,535};
        for(int i = 0;i<a.length;i++){
            for(int j = 0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
            }
        }
        for (int temp:a
        ) {
            System.out.print(temp+"<");
        }
        System.out.println();
        long end = System.nanoTime();
        System.out.println(end-start); // 冒泡排序时间：503306*/




    }
    //快速排序
    /*public int[] quickSort(){
        long start = System.nanoTime();
        return a;
    }*/


}