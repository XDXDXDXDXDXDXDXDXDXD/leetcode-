import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class MyHash {
    //存在重复元素
    //给定一个整数数组，判断是否存在重复元素。
    //如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false
    public boolean containsDuplicate(int[] nums){
        Set<Integer> Hashset = new HashSet<>();
        for(Integer i:nums){
            if(Hashset.contains(i)) return true;
            Hashset.add(i);
        }
        return false;
    }

    //只出现一次的数字 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
    public int singleNumber(int[] nums){
        //^异或操作，相同数异或为0,0异或任意数为其本身
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            result ^= nums[i];
        }
        return result;
        //第二种方法：使用哈希表，遍历插入，如果已经有记录则删除，最后哈希表中剩下的就是只出现一次的数字
        }
}
