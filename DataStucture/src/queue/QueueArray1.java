package queue;

import java.util.Scanner;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 使用数组模拟队列，这个队列还存在无法重复使用的问题
 * @date 2022/5/26 22:56
 */
public class QueueArray1 {
    private int front;  //队列头部指针
    private int rear; //队列尾部指针
    private int[] queueArray; //队列数组，用于存放数据
    private int maxSize; //表示数组的最大容量

    public QueueArray1() {
    }

    //创建队列的构造器
    public QueueArray1(int maxSize) {
        this.front = -1;//队列头部指针,指向队列头的前一个位置
        this.rear = -1;//队列尾部指针，指向队列尾的具体的数据
        this.queueArray = new int[maxSize];
        this.maxSize = maxSize;
    }

    //判断队列是否已满
    public boolean queueIsFull(){
        return rear == maxSize-1;
    }

    //判断队列是否为空
    public boolean queueIsEmpty(){
        return front == rear;
    }

    //添加数据到队列中
    public void addDataInQueue(int num){
        //判断队列是否已满
        if (queueIsFull()){
            throw new RuntimeException("队列已满,无法添加数据");
        }else{
            rear++;
            queueArray[rear] = num;
        }
    }

    //从队列中获取数据
    public int getDataFromQueue(){
        //判断队列数组是否为空
        if (queueIsEmpty()){
            //由于数组中有可能存在-1的值，故采取抛出一个异常进行提示
            throw new RuntimeException("队列没有任何数据，获取失败");
        }
        front++; //front后移
        return queueArray[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        if (queueIsEmpty()){
            throw new RuntimeException("队列没有任何数据");
        }else {
            for (int i = 0; i < queueArray.length; i++) {
                System.out.print(queueArray[i] + "\t");
                if (i == queueArray.length - 1){
                    System.out.println();
                }
            }
        }
    }

    //显示队列的头数据(不是取出数据)
    public int showTheFirst(){
        if (queueIsEmpty()){
            throw new RuntimeException("队列没有任何数据");
        }else{
            return queueArray[front+1];
        }
    }

}

class TestQueue{
    public static void main(String[] args) {
        //创建一个队列
        QueueArray1 queue = new QueueArray1(3);

        //创建一个选择菜单
        while(true){
            boolean isFlag = false;
            System.out.println("1.show:显示队列");
            System.out.println("2.add:添加数据");
            System.out.println("3.get:获取数据");
            System.out.println("4.head:获取队列头的数据");
            System.out.println("5.exit:退出程序");
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
            }
            if (isFlag){
                break;
            }
        }



    }
}
