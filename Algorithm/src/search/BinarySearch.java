package search;

import java.util.Arrays;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 二分查找(折半查找)
 * @date 2022/10/6 20:35
 */
public class BinarySearch {
    public static void main(String[] args) {
        // int[] array = {1,8,10,89,1000,1234};
        // int result = search(array, 8, 0, array.length - 1);
        // if (result != -1){
        //     System.out.println("找到了目标数，其下标为：" + result);
        // }else {
        //     System.out.println("目标数在此数组中不存在");
        // }
        searchAnyNum();
    }


    public static int search(int[] array,int num,int left,int right) {
        //先找中间数
        int mid = (left + right) / 2;
        /*
        当遍历到最右边时，left是等于right+1的
        当遍历到最左边时，right是等于-1的，
        因此left > right能保证遍历的范围一定在数组的下标范围内
         */
        if (left > right){
            return -1;
        }
        if (array[mid] == num) { //说明要找的数刚刚好是中间数
            return mid;
        } else if (array[mid] > num) { //说明要找的数还在mid左边
            right = mid-1;
            return search(array, num, left, right); //把查找到的下标一层层返回
        } else if (array[mid] < num) { //说明要找的数还在mid右边
            left = mid+1;
            return search(array, num, left, right); //把查找到的下标一层层返回
        }
         return -1;
    }


    /**
     * Description : 查找所有符合目标数的下标
     * @date 2022/10/10
     **/
    public static void searchAnyNum(){
        int[] array = {1,10,89,89,89,1000,1000,1234};
        int[] temp = new int[array.length];
        int index = 0;
        temp = searchUpdate(array, 1000, 0, array.length - 1,temp,index);
        if (temp.length != 0){
            System.out.println(Arrays.toString(temp));
        }else {
            System.out.println("目标数在此数组中不存在");
        }
    }

    /**
     * Description : 查找所有符合目标数的下标
     * 由于数组是有序的，因此只需要在找到的第一个数字的左右两边进行加1或者减1的遍历判断即可
     * @date 2022/10/10
     * @param array 原始传入数组
     * @param num 目标数
     * @param left 左边下标
     * @param right 右边下标
     * @param temp 临时数组
     * @param index 临时数组下标
     **/
    public static int[] searchUpdate(int[] array,int num,int left,int right,int[] temp,int index) {
        //先找中间数
        int mid = (left + right) / 2;
        /*
        当遍历到最右边时，left是等于right+1的
        当遍历到最左边时，right是等于-1的，
        因此left > right能保证遍历的范围一定在数组的下标范围内
         */
        if (left > right){
            return temp;
        }
        if (array[mid] == num) { //说明要找的数刚刚好是中间数
            temp[index] = mid;
            index++;
        }
        else if (array[mid] > num) { //说明要找的数还在mid左边
            right = mid-1;
            temp = searchUpdate(array, num, left, right,temp,index); //把查找到的下标一层层返回
        } else if (array[mid] < num) { //说明要找的数还在mid右边
            left = mid+1;
            temp =  searchUpdate(array, num, left, right,temp,index); //把查找到的下标一层层返回
        }
        int tempRight = mid - 1;
        //从此循环出去即可得到左边所有相同数字位置
        while (true){
            if (tempRight < 0 || array[tempRight] != num){
                break;
            }
            temp[index] = tempRight--;
            index++;
        }

        int tempLeft = mid + 1;
        //从此循环出去即可得到右边所有相同数字位置
        while (true){
            if (tempLeft >= array.length || array[tempLeft] != num){
                break;
            }
            temp[index] = tempLeft++;
            index++;
        }
        return temp;
    }
}
