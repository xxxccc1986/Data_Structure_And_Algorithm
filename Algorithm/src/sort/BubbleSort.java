package sort;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 冒泡排序
 * @date 2022/9/25 21:46
 */
public class BubbleSort {
    //未优化版
    public static void main1(String[] args) {
        int[] bubble = new int[]{3,9,-1,10,-2};
        //临时存储元素值
        int temp;
        /*
        只需要进行n-1次排序即可实现有序数组
        因为在第n次排序时，此时剩下的已经是最小的了，因此不需要再次比较
         */
        for (int i = 0;i<bubble.length-1;i++){
            /*
            每一次排序都是从第一个元素开始，因此下标为0
            例如，当i=0时，bubble.length-1-i 相当于 bubble.length-1
            而此时j最大只能取到 bubble.length-2，
            因此当     j = bubble.length-2
                      j+1 = bubble.length-2+1 ==> bubble.length-1
            保证了每次都能取到最后一个需要比较的元素。也正是因为如此
            在每轮i变化时。j的取值范围相应-i.从而保证每轮都可以避免每次索引都遍历到数组最后一个元素
             */
            for (int j = 0;j<bubble.length-1-i;j++){
                //比较相邻的两个元素
                if (bubble[j] > bubble[j+1]){
                    temp = bubble[j+1];
                    bubble[j+1] = bubble[j];
                    bubble[j] = temp;
                }
            }
        }
    }

    //优化版 即当发现某次排序中没有进行任何交换提前结束排序
    public static void main(String[] args) { //冒泡排序花费时间9s
        int[] bubble = new int[]{3,9,-1,10,20};
//        int[] bubble = new int[80000];
//        for (int i=0;i<80000;i++){
//            bubble[i] = (int)(Math.random()*80000);
//        }
        //临时存储元素值
        int temp;
        //临时标识符，用于辨认是否需要结束排序
        boolean flag = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        String startStr = dateFormat.format(start);
        System.out.println("排序前时间：" + startStr);

        for (int i = 0;i<bubble.length-1;i++){
            for (int j = 0;j<bubble.length-1-i;j++){
                //比较相邻的两个元素
                if (bubble[j] > bubble[j+1]){
                    //表示执行过排序
                    flag = true;
                    temp = bubble[j+1];
                    bubble[j+1] = bubble[j];
                    bubble[j] = temp;
                }
            }
            if (!flag){ //表示没进行过交换，直接结束
                break;
            }else {
                //重置flag进行下一次交换
                flag = false;
            }
//            System.out.println("当前是第" + (i+1) + "次排序");
        }
        Date end = new Date();
        String endStr = dateFormat.format(end);
        System.out.println("排序后时间：" + endStr);

    }

}
