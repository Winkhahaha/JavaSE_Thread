package 面试题;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2020/09/11/ 15:27
 * @Description
 */
public class Test02 {
    public static void main(String[] args) {
        HashMap map = new HashMap();
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 最少移动次数
     *
     * @param nums int整型一维数组
     * @return int整型
     */
    public static int minMoves(int[] nums) {
        // write code here
        Arrays.sort(nums);
        int num = nums[(nums.length) / 2];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += Math.abs(nums[i] - num);
        }

        return count;
    }
}
