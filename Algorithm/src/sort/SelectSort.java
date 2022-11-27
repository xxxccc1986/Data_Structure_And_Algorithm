package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 选择排序
 * @date 2022/9/28 16:31
 */
public class SelectSort {
    public static void main(String[] args) {
       int[] array = {101, 34, 119, 1};
       // int[] array = {76, 5, -1, 60};
       sort(array);
//         testTime();
    }


    public static void sort(int[] array) {
        int minIndex = 0; //记录最小值的下标
        int min = 0; //假定第一个元素为最小的
        System.out.println(Arrays.toString(array));
        /*
        与冒泡排序相似，只需要执行array.length -1 轮排序即可确定数组的有序性
        例如总共7个数字，执行6轮后剩下最后的这个数就不需要再排序了
        ①确定总共需要进行几轮排序
        ②假定当前的数字为最小值，逐个遍历比较
        当下一个数字比当前数字大时，则将两个数值进行交换
         */
        for (int i = 0; i < array.length - 1; i++) {
            minIndex = i;
            min = array[i];
            for (int j = i+1; j < array.length; j++) {
                if (min > array[j]) {
                    minIndex = j;//重新设置最小值下标
                    min = array[j]; //重新设置最小
                }
            }
            //当minIndex == i时说明最小值下标没变化过，不需要做交换
            if (minIndex != i){
                //将数组最小值和数组首个元素进行交换
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void testTime() { //选择排序花费时间2s
        int[] test = new int[80000];
        for (int i = 0; i < 80000; i++) {
            test[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        sort(test);

        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);
    }
}
