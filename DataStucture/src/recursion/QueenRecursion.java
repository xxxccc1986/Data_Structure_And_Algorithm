package recursion;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 八皇后问题
 * @date 2022/6/14 17:15
 */
public class QueenRecursion {

    //定义皇后的最大数量
    int max = 8;

    //定义一个数组保存八个皇后都能正确放置的位置
    int[] queenArray = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        QueenRecursion queenRecursion = new QueenRecursion();
        queenRecursion.check(0);
        System.out.println("result：" + count);

    }

    /**
     * Description : 添加皇后
     * 需要注意的是check方法每一次递归时，进入到check中都会有一个for循环,
     * 等获取到一个完整解就会返回上一个栈，也就是上一个for循环继续在同一行
     * 尝试将皇后摆在下一列，判断是否为另一个完整解释
     * i --> 控制第几列，queenArray[i]的值控制第几行
     * @date 2022/6/14
     * @param n 第n+1个皇后
     * @return void
     **/
    private  void check(int n){
        if (n == max){//当n等于8时，实际上是在添加第9个皇后
            //代表已经得到一条八皇后摆放的正确解，进行打印
            showQueenArray();
            return;
        }
        for (int i = 0; i < max; i++) {
            //先放第一个皇后，先放在该行的第一列
            queenArray[n] = i;
            //判断放到第n个皇后到i列时，是否冲突
            if (judge(n)){
                //进入此结构代表不冲突，可以接着往下放n+1个皇后，开始递归
                check(n+1);
            }
            //如果发生冲突，则会放到本行的下一列进行重新比较，
            //因为这里的下一列是由i控制的，因此会自动往下一列放
        }
    }

    /**
     * Description : 判断已经放置的皇后是否和之前放置的有冲突
     * @date 2022/6/14
     * @param n 表示第n+1个皇后
     * @return boolean
     **/
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            /* 皇后摆放位置要求：
            1.不能在同一列
            2.当行差和列差相等时则表示两个皇后在同一个斜线上，因此行差和列差不能相等
            3.不能在同一行，但因为这里使用了一维数组代替二维数组，本身n是不断变化的，
              摆完一个皇后自动换下一行，因此这里不可能会在同一行，也就不需要判断
             */
            if (queenArray[i] == queenArray[n] || Math.abs(n-i) == Math.abs(queenArray[n] - queenArray[i])){
                return false;
            }
        }
        return true;
    }

    private  void showQueenArray(){
        for (int i = 0; i < queenArray.length; i++) {
            System.out.print(queenArray[i] + "\t");
        }
        count += 1;
        System.out.println();
    }

}

