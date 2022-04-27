package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array_String {

    //递归反转字符串
    public static void StringReverse(int index,char[] str){
        if(str==null||index>str.length-1||index<0){
            return;
        }
        StringReverse(index+1,str);
        System.out.print(str[index]);
    }

    //返回数组的中心索引（数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和）
    public int pivotIndex(int[] nums) {
        int left = 0,right = 0;         //中心索引左之和，右之和
        for(int x : nums){
            right += x;                 //数组所有元素总和
        }
        for(int j =0;j<nums.length;j++){
            if(j==0){
                right -= nums[j];
                if(left == right) return 0;
            }
            else{
                left += nums[j-1];      //每右移一次左和加一个元素
                right -= nums[j];       //每右移一次右和减一个元素
                if(left == right) return j;
            }

        }
        return -1;
        /*  官方解答
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
        if (leftsum == sum - leftsum - nums[i]) return i;
        leftsum += nums[i];
    }
        return -1;*/
    }

    //至少是其他数字两倍的最大值
    public int dominantIndex(int[] nums) {
        int max=nums[0];int secondmax=0;int res=0;
        if(nums.length == 1) return 0;
        for(int i = 1;i < nums.length;i++){       //找到最大值与第二大值
            if(nums[i] >= max){
                secondmax = max;
                max = nums[i];
            }
            else if(nums[i] > secondmax){
                secondmax = nums[i];
            }
        }
        if(max >= 2 * secondmax){       //如果最大值比第二大值的二倍还大则返回最大值的索引
            for(int i = 0;i<nums.length;i++){
                if(max == nums[i]) {res = i;break;}
            }
            return res;
        }
        return -1;
    }

    /*给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
    最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
    你可以假设除了整数 0 之外，这个整数不会以零开头。如输入[1,2,3]输出[1,2,4]*/
    public int[] plusOne(int [] digits){
        int i = digits.length-1;
        int j = (digits[digits.length-1]+1) / 10;
        while(j==1 && i>0) {                  //当加1后比10大则进位
            digits[i] = 0;                  //进位后当前位为0
            j = (digits[i-1]+1)/10;         //j=进位后的更高一位加1后除以10，
            i--;
        }
        if(j == 1){                         //如果是因为i=0跳出循环，且j=1则说明最高位是9
            int [] res = new int[digits.length+1];      //生成一个比输入数组多一位的数组返回结果
            res[0] = 1;
            for(int k = 1;k<res.length;k++){
                res[k] = 0;
            }
            return res;
        }
        else{
            digits[i] += 1;
            return digits;
        }       //ps:第一次执行用时（0ms）超过100%的用户
    }

    //二维数组的对角线遍历
    public int [] diagonalOrder(int [][] matrix){
        if(matrix.length == 0 || matrix == null) return new int[0];

        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];

        int row = 0, col = 0, d = 0;
        int[][] dirs = {{-1,1},{1,-1}};

        for(int i = 0; i < m * n; i++)
        {
            res[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];

            if(row >= m) { row = m - 1; col += 2; d = 1 - d;}
            if(col >= n) { col = n - 1; row += 2; d = 1 - d;}
            if(row < 0)  { row = 0; d = 1 - d;}
            if(col < 0)  { col = 0; d = 1 - d;}
        }
        return res;
    }

    //螺旋矩阵（顺时针旋转遍历二维数组）
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) return res;
        int rowBegin = 0,colBegin = 0,rowEnd = matrix.length-1,colEnd = matrix[0].length-1;
        while(rowBegin<=rowEnd&&colBegin<=colEnd){
            for(int i = colBegin;i<=colEnd;i++){            //从左往右遍历
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            for(int i = rowBegin;i<=rowEnd;i++){     //从上往下遍历
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            if(rowBegin<=rowEnd) {
                for (int i = colEnd; i>=colBegin; i--) {     //从右往左遍历
                    res.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }
            if(colBegin<=colEnd) {
                for (int i = rowEnd; i>=rowBegin; i--) {     //从下往上遍历
                    res.add(matrix[i][colBegin]);
                }
                colBegin++;
            }
        }
        return res;
    }

    //字符串二进制求和，给定两个二进制字符串，返回他们的和（用二进制表示）
    public String addBinary(String a,String b){
        char [] ac = a.toCharArray();
        char [] bc = b.toCharArray();
        int [] ai = new int[ac.length];
        int [] bi = new int[bc.length];
        int[] high={},low={},temp={};
        int j = 0;
        for(int k=0;k<ac.length;k++){
            int tem = Integer.parseInt(String.valueOf(ac[k]));
            ai[k] = tem;
        }
        for(int k=0;k<bc.length;k++){
            int tem = Integer.parseInt(String.valueOf(bc[k]));
            bi[k] = tem;
        }                                           //以上将输入值转为两个int类型的数组
        if(ai.length>bi.length){
            high = ai;
            low = bi;
        }
        else if(ai.length<bi.length){
            high = bi;
            low = ai;
        }
        else{
            high = ai;
            temp = bi;
        }
        if(ai.length!=bi.length) {
            int x = 0;
            temp = new int[high.length];
            for (int k = 0; k < high.length; k++) {
                if (k < high.length - low.length) {
                    temp[k] = 0;
                    continue;
                } else {
                    temp[k] = low[x];
                    x++;
                    continue;
                }
            }
        }                       //低位数组高位补0
        int i = high.length-1;
        while(i>=0){
            if((high[i]+temp[i]+j)==0){
                high[i] = 0;
                j = 0;i--;continue;
            }
            if((high[i]+temp[i]+j)==1){
                high[i] =1;
                j = 0;i--;continue;
            }
            if((high[i]+temp[i]+j)==2){
                high[i] = 0;
                j = 1;i--;continue;
            }
            if((high[i]+temp[i]+j)==3){
                high[i] = 1;
                j = 1;i--;continue;
            }
        }                   //两个数组相加
        if(j==1){
            String res = "1";
            for(int k = 0;k<high.length;k++){
                res += high[k];
            }
            return res;
        }                   //最高位还需进1
        else{
            String res ="";
            for(int k = 0;k<high.length;k++){
                res += high[k];
            }
            return res;
        }
        /*
        //执行用时1ms范例
        char[]acs=a.toCharArray();
        char[]bcs=b.toCharArray();
        int maxlength=Math.max(acs.length,bcs.length);
        char[]rcs=new char[maxlength];
        boolean flag=false;
        for(int i=1;i<=maxlength;i++){
            char achar=acs.length-i>=0?acs[acs.length-i]:'0';
            char bchar=bcs.length-i>=0?bcs[bcs.length-i]:'0';
            char benchar=' ';
            //计算逻辑
            if(achar=='0'&&bchar=='0'){
                benchar=flag?'1':'0';
                flag=false;
            }
            else if(achar=='0'&&bchar=='1'){
                benchar=flag?'0':'1';
            }
            else if(achar=='1'&&bchar=='0'){
                benchar=flag?'0':'1';
            }
            else if(achar=='1'&&bchar=='1'){
                benchar=flag?'1':'0';
                flag=true;
            }
            rcs[maxlength-i]=benchar;
        }
        return flag?"1"+new String(rcs):new String(rcs);*/
        /*
        //执行用时为2ms范例
        int i = a.length() - 1;
        int j = b.length() - 1;
        int sum = 0;
        StringBuilder result = new StringBuilder();
        while (i >= 0 || j >= 0 || sum == 1) {
            sum += i >= 0 ? a.charAt(i--) - '0' : 0;
            sum += j >= 0 ? b.charAt(j--) - '0' : 0;
            result.append(sum & 1);
            sum >>= 1;
        }
        return result.reverse().toString();*/
    }

    //实现strStr()给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回-1
    public int strStr(String haystack,String needle){
        //2ms
        if(needle.equals("")){
            return 0;
        }
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int i = 0,j = 0;
        while(i<h.length&&j<n.length){

            if(h[i] == n[j]){
                i++;j++;

            }
            else{
                i = i-j+1;
                j = 0;
            }
        }
        if(j==n.length)
        return i-j;
        else return -1;
        //0ms
        /*if(needle == null || needle.length() == 0){
            return 0;
        }
        return haystack.indexOf(needle);*/
    }

    //最长公共前缀.eg输入: ["flower","flow","flight"] 输出: "fl" ps用时3ms  19%
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length ==0) return "";
        boolean flag = true;int s = 1,loop = 1,floop = 0;String temp ="";int minlength = 0;
        //找到最短字符串
        for(int i =0;i<strs.length;i++){
            if(strs[i].length()<strs[minlength].length()){
                minlength = i;
            }
        }
        if(strs[minlength].length()==0) return "";
        //截取子字符串进行匹配
        while(flag){
            floop = 0;
            if(s>strs[minlength].length()) break;
            temp = strs[minlength].substring(0,s);
            for(int i = 0;i<strs.length;i++){
                if(!temp.equals(strs[i].substring(0,s))){
                    flag = false;
                    break;
                }
                floop++;
            }
            if(flag){
                s++;
                loop++;
            }
        }
        //只while一次且匹配未完成时
        if(loop == 1){
            return "";
        }
        //匹配未完成时
        else if(floop!=strs.length) return strs[minlength].substring(0,s-1);
        else return temp;
        /*
        //执行用时0ms范例
        if (strs == null || strs.length == 0) {
            return "";
        }
        String pre = strs[0];
        for (String str : strs) {
            while (str.indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length() - 1);
            }
            if (pre.isEmpty()) {
                return "";
            }
        }

        return pre;*/
    }

    //双指针反转字符串 ps用时1ms 100%
    public void reverseString(char[] s){
        if(s == null||s.length==0||s.length==1) return;
        int i = 0,j = s.length-1;
        while(i<j){
            char temp = s[j];
            s[j] = s[i];
            s[i] = temp;
            i++;j--;
        }
    }

    //数组拆分，给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大
    //输入: [1,4,3,2]，输出: 4，解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4)
    //其中n 是正整数,范围在 [1, 10000].数组中的元素范围在 [-10000, 10000]
    public int arrayPairSum(int[] nums){
        /*//30ms 19%
        if(nums == null||nums.length == 0) return 0;
        int res = 0;
        Arrays.sort(nums);
        for(int i = 0;i<nums.length;i+=2){
                res += nums[i];

        }
        return res;*/
        //执行用时3ms范例  没注释看不懂啊
        boolean[] flag = new boolean[20001];
        int sum = 0;
        for (int v: nums) {
            flag[v + 10000] = !flag[v + 10000];     //将存有v的flag全置为1 相当于递增顺序的哈希表（先有记录的对应nums中的较小数）
            sum += v;       //数组之和
        }

        int loss = 0;
        boolean searchFirst = true;
        int first = 0;
        for (int i = 0; i <= 20000; i++) {
            if (flag[i]) {      //如果有记录
                if (searchFirst)    //跳一个数
                    first = i;
                else
                    loss += i - first;

                searchFirst = !searchFirst;
            }
        }

        return (sum - loss) / 2;    //???
    }

    //两数之和--输入有序数组。给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
    //函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。（返回的下标值从1开始）
    //可以假设每个输入只对应一个答案
    public int[] twoSum(int[] numbers, int target){
        /*//82ms 16%
        int [] res = new int[2];int p = 0,index1 = -1,index2 = -1;
        for(int i = 0;i<numbers.length;i++){
            if(numbers[i]>target){
                p = i+1;
                break;
            }
            else p = numbers.length;
        }
        for(int i = 0;i<p;i++){
            for(int j = i+1;j<p;j++){
                if(numbers[i]+numbers[j]==target){
                    index1 = i+1;
                    index2 = j+1;
                }
            }
        }
        res[0] = index1;res[1] = index2;
        return res;*/
        //执行用时0ms范例
        int start=0;
        int end=numbers.length-1;
        int[] result=new int[2];
        while(start<end){
            int temp=numbers[start]+numbers[end];
            if(temp==target){
                result[0]=start+1;
                result[1]=end+1;
                return result;
            }else if(temp<target){
                int t=numbers[start];
                start++;
                while(numbers[start]==t){
                    start++;
                }


            }else{
                int t=numbers[end];
                end--;
                while(numbers[end]==t){
                    end--;
                }
            }
        }
        return result;
        /*//执行用时为1ms范例
        int l = numbers.length - 1;
        int i = 0;
        while (i <= l) {
            int count = numbers[i] + numbers[l];
            if (count == target) {
                return new int[]{i+1, l+1};
            } else if (count > target) {
                l--;
            } else {
                i++;
            }
        }
        return null;*/
    }

    //移除元素 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度
    public int removeElement(int[] nums, int val){
        //执行用时0m
        int i = 0,n = nums.length;
        while(i<n){
            if(nums[i]==val){
                nums[i] = nums[n-1];
                n--;
            }
            else i++;
        }
        return n;
        /*
        int j = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!=val){
                nums[j++] = nums[i];
            }
        }
        return j;*/
    }

    //最大连续1的个数 给定一个二进制数组， 计算其中最大连续1的个数
    public int findMaxConsecutiveOnes(int[] nums){
        //2ms 100%
        if(nums.length==0) return 0;
        int max = 0,currentMax = 0,i = 0,j = 0;
        while(i<nums.length){
            j = i;
            //判断是否连续为1
            if(nums[i]==1){
                while(nums[j]==1) {
                    currentMax++;
                    if(j==nums.length-1) break;     //j指针到达末尾跳出
                    j++;
                }
            }
            max = Math.max(currentMax,max);     //记录连续为1的最大长度
            currentMax = 0;     //置0记录下一个连续长度
            i = j+1;       //i指针到断点的下一位
        }
        return max;
    }

    /*长度最小的子数组 给定一个含有 n 个正整数的数组和一个正整数 s ，
    找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0
    eg输入: s = 7, nums = [2,3,1,2,4,3] 输出: 2*/
    public int minSubArrayLen(int s, int[] nums){
        /*//84ms 13.4%
        int i = 0,j = 0,sum = 0,minL = nums.length;
        //计算数组总和
        for (int x:nums) {
            sum += x;
        }
        //总和都还小于s则没有这样的子数组
        if(sum<s) return 0;
        sum = 0;
        //暴力遍历所有可能的组合
        while(i<nums.length){
            sum += nums[i];
            if(sum >= s){
                int l = i-j+1;
                j++;
                i = j;
                sum = 0;
                if(l<minL) minL = l;
                continue;
            }
            i++;
        }
        return minL;*/
        //执行用时1ms范例
        if (nums.length == 0) {
            return 0;
        }
        int res = Integer.MAX_VALUE;    //int类型的最大值2^31-1
        int left = 0;
        int right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (left <= right && sum >= s) {
                if (res > right - left + 1) {
                    res = right - left + 1;
                }
                sum -= nums[left];
                left++;
            }
            right++;
        }
        if (res == Integer.MAX_VALUE) {
            return 0;
        }
        return res;
        /*
        //执行用时2ms范例
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = Math.min(ans, i + 1 - left);
                sum -= nums[left++];
            }
        }
        return (ans != Integer.MAX_VALUE) ? ans : 0;*/
    }

    //旋转数组 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
    //输入: [1,2,3,4,5,6,7] 和 k = 3.输出: [5,6,7,1,2,3,4]
    public void rotate(int[] nums, int k){
        //1ms 82%
        if(nums.length == 0||nums.length == 1) return;
        int realMove = k % nums.length;       //realMove为实际向右移动的大小
        if(realMove == 0) return;
        //i为移动元素的指针，temp1为起始值，temp2为移动后此位置的值，count为移动次数（为数组的长度，可以避免死循环）
        int i = 0,temp1 = 0,temp2,count = 0,length = nums.length;
        for (int j = 0; j < length; j++) {
            i = j;
            temp1 = nums[i];
            if (count == length) break;      //如果移动次数为length说明所有元素移动完成
            //移动元素
            do{
                temp2 = nums[(i+realMove)%length];
                nums[(i+realMove)%length] = temp1;
                temp1 = temp2;
                i = (i+realMove)%length;        //指针右移
                count++;
            }while(j != i);     //从起始位置一直右移直到回到起始位置开始下一轮for循环
        }
    }

    /*字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
    比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"
    限制：1 <= n < s.length <= 10000*/
    public String rotateString(String s, int n) {

        return s.substring(n, s.length()) + s.substring(0, n);
    }

    /*翻转字符串里的单词
    说明：无空格字符构成一个单词。输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
    如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
    输入: "a good   example" 输出: "example good a"*/
    public String reverseWords(String s){
        char[] c = s.toCharArray();
        char[] cCopy = new char[c.length];
        for(int i = 0;i<c.length;i++){

        }

        return"";
    }

    //杨辉三角 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
    //在杨辉三角中，每个数是它左上方和右上方的数的和,每一行的端点为1
    public List<List<Integer>> yangHuiTriangle(int numRows){
        //1ms 98%
        List<List<Integer>> res = new ArrayList<>();
        int[][] temp = new int[numRows][numRows];                   //二维数组便于找左上方与右上方
        for(int i = 0;i<numRows;i++){
            List<Integer> currentRow = new ArrayList<>();           //每一行生成一个ArrayList接收
            for(int j = 0;j<=i;j++){
                if(j==0||j==i){
                    temp[i][j] = 1;                                 //是端点的元素为1
                }
                else{
                    temp[i][j] = temp[i-1][j-1]+temp[i-1][j];       //不是端点的元素为左上方和右上方数之和
                }
                currentRow.add(temp[i][j]);                         //同一行的元素加入到此行的ArrayList
            }
            res.add(currentRow);
        }
        return res;
        /*//官方题解
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // First base case; if user requests zero rows, they get zero rows.
        if (numRows == 0) {
            return triangle;
        }

        // Second base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }

        return triangle;*/
    }

    //杨辉三角② 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行
    public List<Integer> yangHuiTriangle_2(int rowIndex){
        //1ms 93%
        List<Integer> res = new ArrayList<>();
        int[][] temp = new int[rowIndex+1][rowIndex+1];
        for(int i = 0;i<=rowIndex;i++){
            for(int j = 0;j<=i;j++){
                if(j==i||j==0){
                    temp[i][j] = 1;
                }
                else{
                    temp[i][j] = temp[i-1][j-1]+temp[i-1][j];
                }
                if(i==rowIndex){
                    res.add(temp[i][j]);
                }
            }
        }
        return res;
        /*//执行用时0ms范例
        List<Integer> res = new ArrayList<Integer>(rowIndex+1);
        long nk = 1;        //nk的计算过程中可能越int界所以用long
        for(int i = 0; i <= rowIndex; i++){
            res.add((int)nk);
            nk = nk * (rowIndex - i) / (i + 1);     //杨辉三角由组合数构成，C n k =n!/(k!(n−k)!)=(n∗(n−1)∗(n−2)∗...(n−k+1))/k!
        }
        return res;*/
    }

    //给定一个整数 n，计算并返回该整数「各位数字之积」与「各位数字之和」的差
    public int subtractProductAndSum(int n) {

        int sum = 0, product = 1, temp;
        while (n > 1) {
            temp = n % 10;
            n /= 10;
            sum += temp;
            product *= temp;
        }
        return product-sum;
    }

    /* 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，
     你想知道你拥有的石头中有多少是宝石。
    J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头*/
    public int numJewelsInStones(String J, String S) {

        int count = 0;
        char[] cj = J.toCharArray();
        char[] cs = S.toCharArray();
        for (char j : cj) {
            for (char s : cs) {
                if (j == s) {
                    count++;
                }
            }
        }
        return count;

        /*int ret = 0;
        for(int i=0; i<S.length(); i++) {
            if(J.indexOf(S.charAt(i))>=0) ret++;
        }
        return ret;*/
    }
}