package search;

import java.util.Arrays;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 插值查找
 * @date 2022/10/10 21:40
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        int result = search(array, 0, array.length - 1, 2);
        if (result != -1){
            System.out.println("找到了目标数，其下标为：" + result);
        }else {
            System.out.println("目标数在此数组中不存在");
        }

    }

    /**
     * Description : 插值查找算法
     * @date 2022/10/10
     **/
    public static int search(int[] array,int left,int right,int num){
        //num < array[left] || num > array[right]这两个条件很有必要，
        //否则当传入的num值远远大于数组最大值时，会出现角标越界异常
        if (left >= right || num < array[left] || num > array[right]){
            return -1;
        }

        int mid = left + (right - left) * (num - array[left]) / (array[right] - array[left]);
        int numValue = array[mid];

        if (numValue == num){
            return mid;
        }else if (numValue > num){ //说明要找的数还在mid的左边
            right = mid - 1;
            return search(array,left,right,num);
        }else if (numValue < num){ //说明要找的数还在mid右边
            left = mid + 1;
            return search(array,left,right,num);
        }
        return -1;
    }
}
