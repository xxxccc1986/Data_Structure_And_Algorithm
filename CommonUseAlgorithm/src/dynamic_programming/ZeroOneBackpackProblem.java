package dynamic_programming;

import java.util.Arrays;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 01背包问题(动态规划算法解决)
 * @date 2022/11/23 16:38
 */
public class ZeroOneBackpackProblem {

    public static void main(String[] args) {
        int[] weight = {1,4,3};//维护物物品重量的数组
        int[] value = {1500,3000,2000};//维护物物品价值的数组
        int m = 4;//背包容量
        int n = value.length;//物品的个数
        int[][] dp = new int[n+1][m+1];//表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] path = new int[n+1][m+1];//表示商品放入的情况

        //初始化第一行和第一列的值为0(非必要的步骤，因为默认为0，只是为了表示当背景容量为0时，背包最大价值也为0的情况)
        for (int i = 0; i <= dp.length; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }
        
        //动态规划算法解决01背包问题
        for (int i = 1; i < dp.length; i++) {//不处理第一行为0的数据
            for (int j = 1; j < dp[i].length; j++) { //不处理第一列为0的数据
                //表示当前物品的重量大于背包容量，因为i = 1,如果不-1则会直接取到第二个物品重量
                if (weight[i-1] > j){
                    // 当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
                    dp[i][j] = dp[i-1][j];
                }else { //包括了weight[i-1] < j 和 weight[i-1] = j 的情况
                    //因为i和j都是从1开始的，因此必须-1，否则都会取到数组内第二个数
                    // dp[i][j]=Math.max(dp[i-1][j], value[i-1]+dp[i-1][j-weight[i-1]]);
                    //使用上面的Math的api无法记录物品放入的情况，因此改成使用下面的判断
                    if (dp[i-1][j] < value[i-1]+dp[i-1][j-weight[i-1]]){
                        dp[i][j] = value[i-1]+dp[i-1][j-weight[i-1]];
                        path[i][j] = 1;//将当前放入情况记录到path数组中
                    }else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }

        //输出商品放入的情况
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标

        //因为如果从最开始的第一个path[0][0]遍历的话 会打印出其他非必要的物品
        while (i > 0 && j > 0){ //从path的最后开始找
            if (path[i][j] == 1){
                System.out.printf("第%d个物品放入背包中\n",i);
                //上面输出了当前放入的包后需要减去当前放入商品的容量，因为这是个01背包，
                j -= weight[i-1];
            }
            i--; //每个商品只会放一次，找下一个存放的商品
        }

        //输出二维数组
        for (int[] d : dp) {
            for (int ii : d) {
                System.out.print(ii + "\t");
            }
            System.out.println();
        }
    }
}
