package stack;

import java.util.Scanner;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 使用数组模拟栈
 * @date 2022/6/8 22:03
 */
public class ArrayImitationStack {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("请输入你要创建的栈长度：");
        int in = keyboard.nextInt();
        //用数组模拟创建栈
        MyCustomizedStack stack = new MyCustomizedStack(in);
        boolean isFlag = true;
        while (isFlag){
            System.out.println("1.入栈");
            System.out.println("2.出栈");
            System.out.println("3.显示栈的数据");
            System.out.println("4.退出程序");
            System.out.print("请输入你的选择：");
            int choose = keyboard.nextInt();
            switch (choose){
                case 1:
                    System.out.print("请输入你要添加的数字：");
                    int num = keyboard.nextInt();
                    stack.addData(num);
                    System.out.println("添加成功");
                    break;
                case 2:
                    stack.popData();
//                    System.out.println("输出成功");
                    break;
                case 3:
                    System.out.println("正在显示栈的数据");
                    stack.showStack();
                    System.out.println("输出成功");
                    break;
                case 4:
                    isFlag = false;
                    break;
            }
        }
    }
}

class MyCustomizedStack{
    private int arraySize;//记录数组的实际容量
    private int top = -1;//栈顶
    private int bottom = -1;//栈底
    private int[] array;//数组模拟的栈

    public MyCustomizedStack() {
    }

    public MyCustomizedStack(int arraySize) {
        this.arraySize = arraySize;
        this.array = new int[this.arraySize];
    }

    @Override
    public String toString() {
        return "MyCustomizedStack{" +
                "top=" + top +
                ", bottom=" + bottom + '}';
    }

    //显示栈的数据
    public void showStack(){
        if (isEmpty()){
            System.out.println("栈中没有任何数据");
            return;
        }
        for (int i = top; i > bottom; i--){
            System.out.println(array[i]);
        }
    }

    //判断栈是否已满
    public boolean isFull(){
        //arraySize是数组最大容量
        //当top = arraySize - 1代表已经添加到数组最后一个位置，
        //此时栈已满
        return top >= arraySize - 1;
    }

    //判断栈是否为空
    public boolean isEmpty(){
        //top默认为-1，-1处没有元素
        return top == -1;
    }

    //入栈
    public void addData(int num){
        if (isFull()){
            System.out.println("栈已满，添加失败");
            return;
        }
        //top++;；
        top++;
        //给数组中top位置赋值
        array[top] = num;
    }

    //出栈
    public void popData(){
        if (isEmpty()){
            System.out.println("栈中没有任何数据");
            return;
        }
        //先把数组中top位置的数值返回，再将top指针-1，调整栈的元素
        System.out.println(array[top--]);

    }

}


