package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 插入排序
 * @date 2022/9/28 21:57
 */
public class InsertSort {
    public static void main(String[] args) {
        // int[] array = {101, 34, 119, 1};
        int[] array = {1, 5, 0, 6, 3, 8, 9, 4, 7, 2};
        soft(array);
        // testTime();
    }

    /*
     基本思路：
     ①将第一个需要插入的元素数值保存起来，用一个临时变量存储待插入元素的下标值
     ②再将其与前一个元素进行比较
     ③判断是否需要进行交换并找到插入位置
     ④将需要插入的元素值插入到指定位置
     */
    public static void soft(int[] array){
        System.out.println(Arrays.toString(array));
        for (int i=0;i<array.length-1;i++){
            int insertVal = array[i+1];
            int insertIndex = i;
            /*
            insertIndex >= 0 保证了数组角标不会越界
            insertVal < array[insertIndex] 保证了插入元素数值一定比前一个元素小
             */
            while (insertIndex >= 0 && insertVal < array[insertIndex]){
                //将大于插入位置元素值的元素后移
                array[insertIndex + 1] = array[insertIndex];
                //考虑到插入位置前有可能有多个元素，因此需要--多次判断
                insertIndex--;
            }
            /* 当退出循环代表已经找到需要插入的位置了
            insertIndex + 1是考虑到两种情况：
            1.最小值在最右边，需要放到第一个元素的位置，而当退出循环找到插入位置时，
            此时insertIndex = -1，因此必须+1才能保证元素的正常插入到第一位
            2.其插入的位置就这原来的位置上的情况，例如[34，35]的情况，期望插入位置为0，
            实际插入位置为1，35又大于34，因此不可能能进入循环中，也必须+1保证元素的正常插入
             */
            array[insertIndex + 1] = insertVal;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void testTime() { //插入排序花费时间1s
        int[] test = new int[80000];
        for (int i = 0; i < 80000; i++) {
            test[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        soft(test);

        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);
    }
}
