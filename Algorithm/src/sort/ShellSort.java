package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 希尔排序
 * @date 2022/9/29 22:12
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {8,9,1,7,2,3,5,4,6,0};
        // testTime();
        // testSoft(array);
        softShell(array);
    }

    /*
    希尔排序交换式实现 所需时间5s 正序遍历比较
    实际上这种方法就是分组加上每个元素循环遍历比较，效率较低
     */
    public static void soft(int[] array) {
        int insertVal = 0;
        int gap = array.length /2;
        System.out.println("原数组：" + Arrays.toString(array));
        while (gap != 0) {//gap是步长(增量)，当gap=0时，数组已经是有序了
            for (int i = 0; i < array.length; i++) {
                //下面的循环是为了分组后找到需要比较的组和遍历比较组内的所有元素，
                for (int j = i + gap; j < array.length; j += gap) {
                    //保存当前的元素值
                    insertVal = array[j];
                    //进行比较，当此时元素大于加上步长的元素则进行交换
                    //相当于发现一个就立即交换
                    if (array[i] > array[j]) {
                        array[j] = array[i];
                        array[i] = insertVal;
                    }
                }
            }
            gap = gap / 2; //减少增量
        }
        System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    /*
    希尔排序移位法实现，从第gap个元素开始遍历，并不断比较在gap元素前的同组元素
     */
    public static void softShell(int[] array) { //所需时间为1s
        int insertIndex = 0;
        int insertVal = 0;
        // System.out.println("原数组：" + Arrays.toString(array));
        for (int gap = array.length/2; gap > 0 ; gap /= 2) {
            //直接从第gap个元素开始，对其所在的组进行直接插入排序，相当于假设当前i所在的位置就是插入的位置
            for (int i = gap; i < array.length; i++) {
                insertIndex = i;
                insertVal = array[insertIndex];
                if (insertVal < array[insertIndex - gap]){
                    //insertIndex - gap >= 0是为了判断前面是否还有数需要比较，
                    // 不成立则说明上一次比较后的insertIndex就是需要插入的位置
                    while (insertIndex - gap >= 0 && insertVal < array[insertIndex - gap]){
                        array[insertIndex] = array[insertIndex - gap];//将较大的元素后移
                        insertIndex -= gap;//重置元素插入位置
                    }
                    array[insertIndex] = insertVal;
                }
            }
        }
        // System.out.println("排序后的数组：" + Arrays.toString(array));
    }

    //希尔排序交换式实现 所需时间5s
    public static void testSoft(int[] array){
        // System.out.println("原数组" + Arrays.toString(array));
        /* int gap = array.length /2 定义了数组最初的分组
           gap >0 确保在gap步长等于0时，不再排序，此时数组已经有序
           gap /= 2 保证每次排序完都减少步长
         */
        for (int gap = array.length /2; gap > 0 ; gap /= 2) {
            /* int i=gap是按照每次增量减少后的步长
               i < array.length 保证遍历不越界
               i++是逐个遍历某个元素
             */
            for (int i = gap;i < array.length;i++){
                /* i-gap是为了从每次重新分组后数组第一个元素开始遍历
                   j >= 0是为了保证不越界
                   j -=gap是为了保证把前面所有是当前组的元素进行比较排序
                 */
                for (int j = i-gap; j >= 0; j -=gap) {
                    //当当前元素值比加上步长的元素大时则进行交换
                    if (array[j] > array[j+gap]){
                        int temp = array[j];
                        array[j] = array[j+gap];
                        array[j+gap] = temp;
                    }
                }
            }
            // System.out.println("排序后的数组" + Arrays.toString(array));
        }
    }

    public static void testTime() {
        int[] test = new int[80000];
        for (int i = 0; i < 80000; i++) {
            test[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        softShell(test);

        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);
    }



    /*
    已废弃
    希尔排序移位法实现 正序遍历比较未能完成 可惜
     */
    public static void soft2(int[] array) {
        boolean flag = false;
        int insertIndex = 0;
        int insertVal = 0;
        int gap = array.length / 2; //步长(增量)
        System.out.println("原数组：" + Arrays.toString(array));
        while (gap != 0) { //当gap=0时，数组已经是有序了
            for (int i = 0; i < array.length/2; i++) {
                insertIndex = i;//0
                insertVal = array[i + gap]; //3
                if (insertVal < array[insertIndex]){
                    // if (insertIndex + gap >= array.length){
                    //     break;
                    // }
                    while (insertIndex <= gap && insertVal < array[insertIndex]){
                        flag = true;
                        array[insertIndex + gap] = array[insertIndex]; //3 ==> 8
                        insertIndex += gap;
                        // if (insertIndex + gap >= array.length){
                        //     break;
                        // }
                    }

                }
                if (flag){
                    array[insertIndex - gap] = insertVal;
                    flag = false;
                }
            }
            gap = gap / 2; //减少增量
            System.out.println("排序后的数组：" + Arrays.toString(array));
            System.out.println("=============");
        }

    }
}
