package divide_and_conquer;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 汉诺塔移动(分治算法解决)
 * @date 2022/11/19 21:12
 */
public class DACDemo{
    public static void main(String[] args) {
        HanoiTower.hanoiTower(2,'a','b','c');
    }
}

class HanoiTower {

    /**
     * Description : 将盘移动到C塔
     * @date 2022/11/19
     * @param num 当前盘的数量
     * @param a a塔 起始地点
     * @param b b塔 中转使用
     * @param c c塔 目标地点
     **/
    public static void hanoiTower(int num,char a,char b,char c){
        //表示只有一个盘
        if(num == 1){
            System.out.println("第1个盘，从 " + a + " => " + c );
        }else { //表示有两个盘以上
            // 如果我们有 n >= 2 情况，我们总是可以看做是两个盘 1.最下边的盘 2. 上面的盘
            // 先把 最上面的盘 A->B，移动过程中会使用到C塔
            hanoiTower(num - 1,a,c,b);
            // 把最下边的盘 A->C
            System.out.println("第" + num + "个盘，从 " + a + " => " + c);
            // 把B塔的所有盘 从 B->C，移动过程中会使用到A塔
            hanoiTower(num - 1,b,a,c);
        }
    }
}
