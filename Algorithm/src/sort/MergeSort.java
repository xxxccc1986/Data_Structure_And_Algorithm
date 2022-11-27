package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 归并排序
 * 分治法中所谓的分就是将一个数组不断左右递归拆分，
 * 但仅仅是字面意思上的拆分，并不是真正将一个数组拆分成多个数组，
 * 只是通过多个下标指针限定每次遍历的范围。
 * 而每次的递归都是左右递归拆分，直至不能再拆分后就进行合并。
 * 这里很复杂，因为递归是压栈操作，每次都先执行完栈顶方法才会执行下一层，
 * 但是会在某一层中又会发生递归分解，重复拆分合并的操作。
 * 直至栈内所有方法执行完毕。
 *
 *
 * 所谓的归并算法的分治思想就是在一个数组的基础上，
 * 不断的分割出来的虚拟数组，然后再把这个虚拟数组再次分为左右两个部分，
 * 分别拿左右各自的第一个数进行比较，使用一个临时数组存储左右部分比较之后较小的值，
 * 直至左右某一个部分的数被完全放入了临时数组中，再将剩余部分(有可能是左边剩下的或者是右边剩下的)
 * 放入到临时数组中，最后将临时数组的值全部放入到原来的数组中，这就是治(merge)的过程，只不过
 * 上述的这部分会通过递归不断执行(分的过程)
 * @date 2022/10/4 23:14
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {8,4,5,7,1,3,6,2};
        int[] temp = new int[array.length];
        mergeSoft(array,0, array.length-1,temp);
        System.out.println("\n" + "数组：" + Arrays.toString(array));
        // testTime();

    }

    /**
     * Description : 递归拆分排序数组+合并成有序数组
     * @param array 传入的数组
     * @param left 数组左边的下标
     * @param right 数组右边的下标
     * @param temp 临时存储交换值所用数组
     **/
    public static void mergeSoft(int[] array,int left,int right,int[] temp){
        //该条件成立则传入的数组不需要递归分解
        if (left >= right){
            return;
        }
        int mid = (left + right) / 2;
        //向左递归分解，直至不能再分解
        mergeSoft(array,left,mid,temp);
        //向右递归分解，直至不能再分解
        mergeSoft(array,mid+1,right,temp);
        //每次分解完都会合并
        merge(array,left,right,mid,temp);
    }


    /**
     * Description : 合并数组
     * @date 2022/10/4
     * @param array 排序的原始数组
     * @param left 左边有序序列的初始下标
     * @param right 右边下标
     * @param mid 中间下标
     * @param temp 临时存储交换值所用数组
     **/
    public static void merge(int[] array,int left,int right,int mid,int[] temp){
        // System.out.println("原数组:" + Arrays.toString(array));
        int l = left; //左边数组的下标
        int m = mid + 1; //右边第一个数组的下标
        int t = 0;//当前temp数组所在位置下标

        /* 先将左右两边有序的数据按照规则填充到temp数组中
           直至左右两边的有序序列，有一边处理完毕
           l <= mid 确保了只遍历由原数组分出的左边数组，
           m <= right 确保了只遍历由原数组分出的右边数组，
           实际上这些都是在同一个数组中，通过mid和right限制遍历范围
         */
        while (l <= mid && m <= right){
            //满足if则代表左边的比右边的数小
            //需要将当前左边的数放到temp这个临时数组中
            if (array[l] <= array[m]){
                temp[t] = array[l];
                t++;
                l++;
            }else {
                temp[t] = array[m];
                t++;
                m++;
            }
        }

        /*
        将有剩余数据的有序序列中的数据依次添加到temp数组中
        当由于m不满足条件退出上面while循环时，此时m是比数组长度还是大于1的
        因此m-1 == right是用来判断此时右边数组是否已完全遍历了
         */
        if (m-1 == right){
            for (int i = l; i <= mid ; i++) {
                  temp[t] = array[i];
                  t++;
                  l++;
            }
        }else{
            for (int i = m; i <= right ; i++) {
                temp[t] = array[i];
                t++;
                m++;
            }
        }

        /*
        将temp数组的数据复制到原始的数组中
        insertIndex将插入位置设为分解array数组后传入的第一个元素所在的位置
        t表示临时数组temp中有多少个元素
         */
        int insertIndex = left;
        for (int i = 0; i < t; i++) {
            array[insertIndex] = temp[i];
            //因为不知道传入的分解数组有多少个元素，
            // 因此需要+1，才能保证temp的元素正确插入array数组中
            insertIndex++;
        }
        // System.out.println("排序后的数组:" + Arrays.toString(array));
    }

    public static void testTime() { //大概在一秒内
        int[] test = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            test[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[test.length];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        mergeSoft(test,0,test.length-1,temp);

        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);
    }
}
