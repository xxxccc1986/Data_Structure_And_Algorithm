package search;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 线性查找(顺序查找)
 * @date 2022/10/6 19:28
 */
public class LinearSearch {
    public static void main(String[] args) {
        int[] array = {1,-9,11,-1,34,89};
        int result = search(array, 34);
        if (result != -1){
            System.out.println("找到了目标数，其下标为：" + result);
        }else {
            System.out.println("目标数在此数组中不存在");
        }
    }

    public static int search(int[] array,int num){
        for (int i = 0; i < array.length; i++) {
            if (num == array[i]){
                return i;
            }
        }
        return -1;
    }

}
