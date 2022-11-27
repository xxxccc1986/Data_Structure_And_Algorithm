package queue;

import java.util.Scanner;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 使用数组模拟环形队列，解决数组模拟队列只能使用单次的问题
 * @date 2022/5/26 22:56
 */
public class QueueArray2 {
    private int front;  //队列头部指针
    private int rear; //队列尾部指针
    private int[] queueArray; //队列数组，用于存放数据
    private int maxSize; //表示数组的最大容量

    public QueueArray2() {
    }

    //创建环形队列的构造器
    public QueueArray2(int maxSize) {
        this.front = 0;//队列头部指针,指向队列头的第一个位置
        this.rear = 0;//队列尾部指针，指向队列中最后一个元素的后一个索引位置
        this.queueArray = new int[maxSize];
        this.maxSize = maxSize;
    }

    //判断队列是否已满
    public boolean queueIsFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean queueIsEmpty(){
        return front == rear;
    }

    public int getFront() {
        return front;
    }

    //添加数据到队列中
    public void addDataInQueue(int num){
        //判断队列是否已满
        if (queueIsFull()){
            throw new RuntimeException("队列已满,无法添加数据");
        }else{
            queueArray[rear] = num;
            rear = (rear + 1) % maxSize ;
        }

    }

    //从队列中获取数据
    public int getDataFromQueue(){
        //判断队列数组是否为空
        if (queueIsEmpty()){
            //由于数组中有可能存在-1的值，故采取抛出一个异常进行提示
            throw new RuntimeException("队列没有任何数据，获取失败");
        }
        /*由于此时front指向的第一个元素
          使用一个临时变量存储取出当前指向的元素
          再将front的指针进行后移
          最后返回这个临时变量
         */
        int value = queueArray[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        if (queueIsEmpty()){
            throw new RuntimeException("队列没有任何数据");
        }else {

            for (int i = front; i < front + queueSize() ; i++) {
                /*至于为什么i要取模，例如front的数组索引为3，rear的索引为2，数组容量为5
                那么queueSize()=(2+5-3) % 5 = 4
                则i = 3 ，i < 3 + 4 (即 i < 7)，这样i的值可以取 3，4，5，6
                如果i不取模，在数组容量为5的情况下，数组就会越界
                 */
                System.out.print("queue:" + queueArray[i % maxSize] + "\n");
            }
        }
    }

    //求出队列的有效数据
    public int queueSize(){
        /*
        rear代表队列尾部的位置
        front代表队列头部的位置
        通过rear - front 就能得出有效元素，但为什么还要加上maxSize再取余，这不显得多此一举吗？
        因为这是环形队列，rear的索引值可能比front的索引值小，
        不加上maxSize再取余是有可能出现两者相减为负数的情况
         */
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据(不是取出数据)
    public int showTheFirst(){
        if (queueIsEmpty()){
            throw new RuntimeException("队列没有任何数据");
        }else{
            return queueArray[front];
        }
    }

}

class TestQueue2{
    public static void main(String[] args) {
        //创建一个队列
        QueueArray2 queue = new QueueArray2(3);

        //创建一个选择菜单
        while(true){
            boolean isFlag = false;
            System.out.println("1.show:显示队列");
            System.out.println("2.add:添加数据");
            System.out.println("3.get:获取数据");
            System.out.println("4.head:获取队列头的数据");
            System.out.println("5.exit:退出程序");
            System.out.println("6.查看队列状况");
            System.out.print("请选择你的选项:");

            Scanner in = new Scanner(System.in);
            int queueNum = in.nextInt();
            switch (queueNum){
                case 1:
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("请输入你要添加的数据：");
                        int num = in.nextInt();
                        queue.addDataInQueue(num);
                        System.out.println("添加完成");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        int result = queue.getDataFromQueue();
                        System.out.println(queue.getFront());
                        System.out.println("结果是：" + result);
                        System.out.println("查询完成");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        int head = queue.showTheFirst();
                        System.out.println("队列头的数据是：" + head);
                        System.out.println("查询完成");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("程序正在退出");
                    isFlag = true;
                    break;
                case 6:
                    boolean result = queue.queueIsEmpty();
                    System.out.println("队列是否为空：" + result);
                    boolean full = queue.queueIsFull();
                    System.out.println("队列是否已满：" + full);
                    break;
            }
            if (isFlag){
                break;
            }
        }



    }
}
