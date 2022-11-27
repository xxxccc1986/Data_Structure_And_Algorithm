package sort;

import java.util.Arrays;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 堆排序
 * 堆排序实现思路：
 * 1.先获取到最后一个非叶子节点(从上到下的角度)，
 * 2.让当前非叶子节点所在的树成为局部大顶堆(至于此树的左右节点大小排序则没有要求)，
 * 3.从下往上找到上一个非叶子节点，且从当前的非叶子节点从上到下进行调整为局部大顶堆
 * 4.最后形成一个完整的大堆顶
 * 5.再交换数组的第一个元素(堆顶)和最后一个元素(堆末)，并重新构建堆结构(从新的堆顶元素开始从上往下调整)
 * 6.最后再反复执行第5步，直至成为了一个小堆顶，此时序列已然有序
 * @date 2022/10/17 23:12
 */
public class HeadSort {
    public static void main(String[] args) {
        int[] array = {4,6,8,5,9};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * Description : 堆排序的实现
     * @date 2022/10/18
     * @param array 传入的数组
     **/
    public static void sort(int[] array){
        //1.将无序序列构建成一个完整的大顶堆
        // adjustHead(array,1,array.length);
        // adjustHead(array,0,array.length);
        for (int i = array.length/2-1; i >= 0 ; i--) {
            adjustHead(array,i,array.length);
        }

        //交换元素并重新构建大堆顶
        int temp = 0;
        for (int i = array.length-1; i >= 0 ; i--) {
            //交换数组的第一个元素(堆顶)和最后一个元素(堆末)
            temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            /* 因为每次堆顶和堆末的元素交换都会破环大顶堆的结构，且每次构建必须
               排除掉已经放到堆末的那个元素，从当前的栈顶重新构建堆结构
             */
            adjustHead(array,0, i);
        }
    }

    /**
     * Description : 构建一个完整的大顶堆
     * @param array 传入的数组
     * @param nodeIndex 当前最后一个非叶子节点
     * @param length 数组长度
     **/
    public static void adjustHead(int[] array,int nodeIndex,int length){
        //保存当前父节点的值
        int temp = array[nodeIndex];
        /* 这里解释一下为什么需要i = i*2+1，和在if后面添加else的逻辑
        看起来完全不需要是吧，因为首先传入的是最后一个非叶子节点所以会让
        这些步骤看起来像是多余的，但是一旦执行到最后一个非叶子节点的上一个
        非叶子节点就非常需要，因为无法确认当前的非叶子节点下的节点是否都满足
        大顶堆的要求，即当前节点比其下的所有左右节点都要大，因此必须去判断它
        下面所有左右子节点是否都小于(而左节点是否小于右节点也做了判断)，
        说白就是为被替换下来的节点再次找到合适的位置
         */
        for (int i = (nodeIndex*2+1); i < length; i = i*2+1) {
            /*判断左子节点是否小于右子节点，经过这步，无论是左节点大于还是小于右节点，
            此时i指针都是指向两个节点中的最大值那个
            */
            if (i+1 < length && array[i] < array[i+1]){
                i++;
            }
            //判断子节点最大值是否大于父节点
            if (array[i] > temp){
                //将父节点的值赋为子节点最大值
                array[nodeIndex] = array[i];
                //将i指针指向子节点中最大值所在位置
                nodeIndex = i;
            }else{
                break;
            }
            //将子节点中最大值修改为原先的父节点值，完成两数交换
            array[nodeIndex] = temp;
        }
    }
}
