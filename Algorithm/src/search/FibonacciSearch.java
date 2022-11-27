package search;

import java.util.Arrays;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 斐波那契查找算法
 * @date 2022/10/11 15:24
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] array = {1,8,10,89,1000,1234};
        System.out.println(search(array, 1234));
    }

    /**
     * Description : 查找传入的数组的指定数
     * @date 2022/10/11
     * @param array 传入的原数组
     * @param num 目标数
     **/
    public static int search(int[] array,int num){
        int low = 0;
        int high = array.length-1;
        int k = 0;//斐波那契分割数值的下标
        int mid = 0;//用于判断的mid值
        int[] f = fib(); //斐波那契数列

        //先判断当k等于何数时大于数组元素个数的最近一个斐波那契数值
        while(high >f[k]-1){
            k++;
        }

        int[] copy = new int[0];
        /*
        当有序表的元素个数不是斐波那契数列中的某个数字时，须要把有序表的元素个数长度补齐，
        让它成为斐波那契数列中的一个数值，固然把原有序表截断确定是不可能的，否则还怎么查找。
        而后图中标识每次取斐波那契数列中的某个值时(F[k])，都会进行-1操作，这是由于有序表数组位序从0开始的，
        纯粹是为了迎合位序从0开始。
         */
        if (high <= f[k]-1){
            //将数组扩容到斐波那契数列当前k下标所对应的值
            copy = Arrays.copyOf(array, f[k]);
            int highVal = array[high];
            int tempHigh = high;
            int index = ++tempHigh;
            while (index < copy.length){
                copy[index++] = highVal;
            }
        }
        //当low大于high时代表以及遍历过数组最后一位了
        while (low <= high) {
            //再求出mid的值，根据斐波那契数列进行黄金分割
            mid = low + f[k - 1] - 1;

            //用mid的下标对应的值和目标数进行比较
            if (copy[mid] == num) {
                /* 因为有可能原数组经过扩容，mid有可能找到扩容后下标去
                因此必须判断要找的值是mid下标对应的值时，mid是否已经超过原数组的high
                 */
                if (mid <= high){ //表示当前mid值尚未超过原数组high
                    return mid;
                }else { //表示当前mid值已超过原数组high
                    return high;
                }
            } else if (copy[mid] > num) { //说明要找的数还在mid左边
                high = mid - 1;
                /*
                k--的原因：
                1.全部元素 = 前面元素 + 后面元素
                2.f[k] = f[k-1] + f[k-2]
                因为前面有f[k-1]个元素所以，可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                即在f[k-1]的前面继续查找k--
                即下次循环 mid = f[k-1-1] - 1
                */
                k--;
            } else if (copy[mid] < num) { //说明要找的数还在mid右边
                low = mid + 1;
                /*
                k-=2的原因：
                1.全部元素 = 前面元素 + 后面元素
                2.f[k] = f[k-1] + f[k-2]
                因为后面有f[k-2]个元素所以，可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                即在f[k-2]的后面继续查找k-=2
                即下次循环 mid = f[k-1-2] - 1
                */
                k-=2;;
            }
        }
        return -1;
    }

    //mid = low + F[k-1]-1 需要使用斐波那契数列，因此先生成一个这种数列
    public static int[] fib(){
        int[] temp = new int[maxSize];
        temp[0] = 1;
        temp[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            temp[i] = temp[i-1] + temp[i -2];
        }
        return temp;
    }
}
