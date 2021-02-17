package 面试题;

/**
 * @Author Gaoming
 * @Email mineok@foxmail.com
 * @Date 2020/09/11/ 15:16
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(sum(3, 5));
    }

    /**
     * @param num     int整型 相加的数字
     * @param itemNum int整型 相加项数
     * @return long长整型
     */
    public static long sum(int num, int itemNum) {
        // write code here
        StringBuilder sb = new StringBuilder();
        long sum = 0L;
        for (int i = 0; i < itemNum; i++) {
            for (int j = 0; j <= i; j++) {
                sb.append(num + "");
            }
            sum += Long.parseLong(sb.toString());
            sb = new StringBuilder();
        }
        return sum;
    }
}
