package recursion;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 迷宫回溯问题
 * @date 2022/6/13 20:44
 */
public class MiGongTest {
    public static void main(String[] args) {
        //创建一个二维数组用于代表地图
        int[][] map = new int[8][7];

        //使用1表示墙
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                //表示i只要是第一行和最后一行的都是1，j只要是第一列和最后一列的都是1
                if (i == 0 || i == map.length-1 || j == 0 ||  j == 6){
                    map[i][j] = 1;
                }else if (i == 3 && j == 1 || i == 3 && j == 2){
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        //遍历地图
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        //使用递归找路
//        boolean result = MiGongTest.isFindWay(map, 1, 1);
        boolean result = MiGongTest.isFindWay(map, 2, 2);
        System.out.println("结果是：" + result + "\n");

        //新地图
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }


    /**
     * Description : 判断是否可以走通
     * 必须要考虑清楚小球是从哪个位置开始，又是到哪个位置就表示结束
     * 0表示没有走过，1表示墙，2表示走过的，3表示已经走过，但走不通，map[6][5]代表已经达到地图出口
     * 按照小球的移动策略是从下-->右-->上-->左-->
     * @date 2022/6/13
     * @param map 表示地图
     * @param i 表示从地图哪一行开始找
     * @param j 表示从地图哪一列开始找
     * @return boolean
     **/
    public static boolean isFindWay(int[][] map,int i,int j){
        if (map[6][5] == 2){//表示已达到出口
            return true;
        }else{
            if (map[i][j] == 0){//代表这个点还没走过
                map[i][j] = 2;//假设这个点是可以走过的
                /*
                能够进入递归方法代表此点可走，返回了true，否则就是返回false，
                然后就去不断的进入别的else if语句中的递归方法，周而复始。
                一旦发现某个点四个方向都走不通，就会回溯到上一个递归方法，
                继续执行这些步骤，直至回到第一个递归方法，如果发现也是全部
                走不通，返回最终结果false
                 */
                if (isFindWay(map,i+1,j)){//先尝试往下左
                    return true;
                }else if (isFindWay(map,i,j+1)){//尝试往右走
                    return true;
                }else if (isFindWay(map,i-1,j)){//尝试往上走
                    return true;
                }else if (isFindWay(map,i,j-1)){//尝试往左走
                    return true;
                }else{
                    /*
                    当进入这个方法代表上面的所有方向全部走不通，
                    要将之前假定此点能走通设置的2重新置为3并返回false
                     */
                    map[i][j] = 3;
                    return false;
                }
            }else{//当map[i][j] != 0时，可能是1，2，3
                return false;
            }
          }
        }
}

