package sparsearray;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 解决棋盘存盘问题
 * 实现从  二维数组 -->  稀疏数组 -->  存盘(且可以逆转的过程)
 * @date 2022/5/25 23:58
 */
public class SparseArray {
    /**
     * Description : 将二维数组转换为稀疏数组
     * @date 2022/5/26
     * @param args 参数
     * @return void
     **/
    public static void main(String[] args) {
        //原始数组
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }

        //统计数组中元素不为0的元素
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] != 0){
                    sum += 1;
                }
            }
        }
        System.out.println("========================");
        //创建稀疏数组
        int[][] sparseArray = new int[sum+1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        //将二维数组中的非0元素放到稀疏数组中
        int count = 0;//用于记录是第几个非0的元素
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] != 0 ){
                        count++;
                        sparseArray[count][0] = i;
                        sparseArray[count][1] = j;
                        sparseArray[count][2] = array[i][j];
                }
            }
        }

        //遍历稀疏数组
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                System.out.print(sparseArray[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("========================");
        /**
         * Description : 将稀疏数组恢复为二维数组
         */
         //创建一个新的二维数组

        int[][] theNewArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        //方式一
        for (int i = 1; i < sparseArray.length; i++) {
             theNewArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        //方式二：
//        int newCount = 1;
//        for (int i = 0; i < theNewArray.length; i++) {
//            for (int j = 0; j < theNewArray[i].length; j++) {
//                if (i == sparseArray[newCount][0] && j == sparseArray[newCount][1]) {
//                    theNewArray[i][j] = sparseArray[newCount][2];
//                    if (newCount < sparseArray[0][2]){
//                        newCount++;
//                    }
//                }
//                }
//            }

        //输出新的二维数组
        for (int i = 0; i < theNewArray.length; i++) {
            for (int j = 0; j < theNewArray[i].length; j++) {
                System.out.print(theNewArray[i][j] + "\t");
            }
            System.out.println();
        }
    }



}
