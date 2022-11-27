package binarysort;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 二分查找算法(非递归的实现)
 * @date 2022/11/15 23:52
 */
public class BinarySortNoRecursion {
    public static void main(String[] args) {
        int[] array = {1,3,8,10,11,67,100};
        int index = binarySearch(array,10);
        if (index == -1){
            System.out.println("查找的数在数组中不存在");
        }else {
            System.out.println("找到了，在数组中的下标为：" +  index);
        }
    }


    /**
     * Description : 二分查找(非递归)
     * 关键是不断判断middle的值是否为目标值
     * 因此middle的值必须放在循环中，根据start和end的变化而变化
     * @date 2022/11/16
     * @param array 待查找的数组
     * @param num 目标值
     **/
    public static int binarySearch(int[] array,int num){
        //先获取数组的头索引和尾索引值
        int start = 0;
        int end = array.length-1;
        while (start <= end){
            int middle = (start+end)/2;
            //代表刚刚好是中间数
            if (num == array[middle]) {
                return middle;
            }else if (num > array[middle]){ //表示要找的数还在中间数的右边
                start = middle + 1;
            } else if (num < array[middle]) { //表示要找的数还在中间数的左边
                end = middle - 1;
            }
        }
        return -1;
    }
}
