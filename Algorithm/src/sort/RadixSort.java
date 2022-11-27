package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 基数排序
 * 基数排序算法只需执行数组中最大值的位数的次数之后就会成为有序数组
 *
 * 步骤：
 * 1.第一轮先从每个元素的个位数开始，将每个元素的进行拆分，按照各元素的个位数值放置在对应的桶里，
 * 其中使用了一个二维数组表示10个桶，外层代表几个桶，内层代表每个桶代表一个一维数组，
 * 存储原待排序数组中对应角标的值
 * 2.按照桶的顺序取出元素，从二维数组的每一个一维数组依次取出数据，覆盖原数组中的数据。
 * 3.然后进行下一轮，从元素十位数开始进行上述步骤，每一轮都往元素的左边位数取
 * 4.反复执行直到排序次数超过最大值的位数的次数
 * @date 2022/10/5 21:47
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {53,3,542,748,14,214};
        System.out.println("排序前的数组：" + Arrays.toString(array));
        radixSoft(array);
        System.out.println("排序后的数组：" + Arrays.toString(array));
        // testTime();
    }

    public static void radixSoft(int[] array){
        //创建一个二维数组表示10个桶
        int[][] bucket = new int[10][array.length];

        /* 创建一个一维数组，该数组下标对应的值表示10个桶各自当前下标所在的位置
           该数组的值不需要重置，仅仅是临时记录下标位置，会被反复覆盖
         */
        int[] bucketIndex = new int[10];

        //先找到最大的数
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]){
                max = array[i];
            }
        }

        int chuNum = 1; //定义每轮需要先除的数字
        int loop = String.valueOf(max).length(); //总共循环比较次数
        int loopNum = 0; //初始化循环次数
        while (loopNum < loop) {
            //遍历比较
            for (int i = 0; i < array.length; i++) {
                int num = array[i] / chuNum % 10 ; //代表应该放在那个桶中
                /* 因为bucketIndex的每个下标都代表对应的桶元素的下标位置
                   因此取余后两个数的num相同，即都会指向bucketIndex数组中同一个下标
                   bucketIndex[num]的值是维护了每一个桶内元素的索引
                   bucketIndex[num]++才能保证桶内前一个元素不会被当前的元素覆盖
                   PS:假设数组中有负数，取余后num为负数，则下方这一步会出现角标越界
                 */
                bucket[num][bucketIndex[num]] = array[i];
                bucketIndex[num]++;
            }

            int index = 0;
            for (int i = 0; i < bucket.length; i++) {
                for (int j = 0; j < bucket[i].length; j++) {
                    if (bucket[i][j] != 0){ //不为0代表有数据
                        array[index] = bucket[i][j]; //将当前位置数赋到原数组位置
                        index++;
                        /* 必须重置当前位置数为0，为下一轮排序做准备，
                       例如下一轮这个位置没有数字，而本轮没重置导致此处位置不为0，
                       本不该进入此结构却进来了，就会破坏原数组的排序数。
                         */
                        bucket[i][j] = 0;
                    }
                }
            }
            chuNum *= 10; //为下一轮取模前除数做准备
            loopNum++;
            System.out.println("第" + (loopNum+1) + "轮的数组：" + Arrays.toString(array));
        }
    }

    public static void testTime() { //大概在一秒内 80000000个数据直接栈溢出
        int[] test = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            test[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[test.length];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        radixSoft(test);

        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);
    }

    //逐步推算的过程
    public static void soft(int[] array){
        //创建一个二维数组表示10个桶
        int[][] bucket = new int[10][array.length];

        //创建一个一维数组表示10个桶各自对应的指针
        int[] bucketIndex = new int[10];

        //遍历比较
        for (int i = 0; i < array.length; i++) {
            int num = array[i] /1 % 10 ; //代表应该放在那个桶中
            /* 因为bucketIndex的每个下标都代表对应的桶元素的下标位置
               因此取余后两个数的num相同，即都会执行bucketIndex数组中同一个下标
               bucketIndex[num]++才能保证前一个元素不会被覆盖
             */
            bucket[num][bucketIndex[num]] = array[i];
            bucketIndex[num]++;
        }


        int geIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i].length; j++) {
                if (bucket[i][j] != 0){ //不为0代表有数据
                    array[geIndex] = bucket[i][j];
                    geIndex++;
                    bucket[i][j] = 0;
                }
            }
            bucketIndex[i] = 0;
        }
        System.out.println("第一轮排序后的数组：" + Arrays.toString(array));


        //遍历比较
        for (int i = 0; i < array.length; i++) {
            int num = array[i] /10 % 10 ; //代表应该放在那个桶中
            /* 因为bucketIndex的每个下标都代表对应的桶元素的下标位置
               因此取余后两个数的num相同，即都会执行bucketIndex数组中同一个下标
               bucketIndex[num]++才能保证前一个元素不会被覆盖
             */
            bucket[num][bucketIndex[num]] = array[i];
            bucketIndex[num]++;
        }


        int shiIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i].length; j++) {
                if (bucket[i][j] != 0){ //不为0代表有数据
                    array[shiIndex] = bucket[i][j];
                    shiIndex++;
                    bucket[i][j] = 0;
                }
            }
            bucketIndex[i] = 0;
        }
        System.out.println("第二轮排序后的数组：" + Arrays.toString(array));

        //遍历比较
        for (int i = 0; i < array.length; i++) {
            int num = array[i] / 100  % 10 ; //代表应该放在那个桶中
            /* 因为bucketIndex的每个下标都代表对应的桶元素的下标位置
               因此取余后两个数的num相同，即都会执行bucketIndex数组中同一个下标
               bucketIndex[num]++才能保证前一个元素不会被覆盖
             */
            bucket[num][bucketIndex[num]] = array[i];
            bucketIndex[num]++;
        }


        int baiIndex = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i].length; j++) {
                if (bucket[i][j] != 0){ //不为0代表有数据
                    array[baiIndex] = bucket[i][j];
                    baiIndex++;
                    bucket[i][j] = 0;
                }
            }
            bucketIndex[i] = 0;
        }
        System.out.println("第三轮排序后的数组：" + Arrays.toString(array));

    }
}
