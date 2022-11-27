package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 快速排序
 * @date 2022/10/3 23:49
 */
public class QuickSort {
    public static void main(String[] args) {
        // int[] array = {-9,78,0,23,-567,70};
        int[] array = {49,38,65,97,76,13,27,49};
        soft(array,0, array.length-1);
        // testTime();
    }

    public static void soft(int[] array,int left,int right){
        //当left >= right代表需要排序的数组已经排序过了，不需要再次排序，直接结束
        //这一步很重要，这一步是递归结束的条件，没有这一步会陷入死循环中
        if (left >= right){
            return;
        }
        // System.out.println("排序前的数组" + Arrays.toString(array));
        int l = left; //左边第一个元素，用于遍历比较
        int r = right;//右下标值
        int pivot = array[left]; //以第一个元素为基准值，用于比较
        /*
        这个循环是为了保证把左边所有比pivot大的值放到右边，
        把右边所有比pivot小的值放到左边，而当l == r也就说明所有的数都遍历比较过了
         */
        while (l != r){
            /* 找到右边比基准值小的下标就退出循环
            l < r是为了保证只遍历比较当前要求的数组范围，
            当array[r] >= pivot一直满足时，r会一直-1，会导致
            下面的array[l] = array[r]被错误赋值
             */
            while (l < r && array[r] >= pivot){
                r -= 1;
            }
            if(l < r){
                //上面循环是因为l<r不满足退出时，此时l和r是在同个位置
                array[l] = array[r];
            }

            //找到左边比基准值大的下标就退出循环，此处l<r和上面的原因一样
            while (l < r && array[l] <= pivot){
                l += 1;
            }
            if(l < r){
                array[r] = array[l];
            }
        }
        /* 出了上面的循环，代表此时l==r了，
        同时所有l左边的数都比pivot小，l右边的数都比pivot大，
        此时只需要把pivot的值赋于l或者r所在位置即可
         */
        array[l] = pivot;
        System.out.println("排序后的数组" + Arrays.toString(array));
        //因为无法保证在经过上面的排序后pivot左右两边的数已然有序，因此必须再对pivot左右两边进行递归快排
        //左边递归
        soft(array,left,l-1);
        //右边递归
        soft(array,l+1,right);

    }

    public static void testTime() { //大概一秒内
        int[] test = new int[80000];
        for (int i = 0; i < 80000; i++) {
            test[i] = (int) (Math.random() * 80000);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        soft(test,0,test.length-1);

        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);
    }



}
