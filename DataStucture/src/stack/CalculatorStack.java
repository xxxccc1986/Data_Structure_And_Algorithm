package stack;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 使用数组模拟栈
 * @date 2022/6/8 22:03
 */
public class CalculatorStack {
    public static void main(String[] args) {
        //创建数字栈
        CustomizedStack numStack = new CustomizedStack(10);
        //创建符号栈
        CustomizedStack charStack = new CustomizedStack(10);

        //创建需要计算的字符串
//        String string = "5+2*6-2";//15
        String string = "30*2-6*9+1";//19
//        String string = "100+2*2-6";//5
//        String string = "7*2*2-5+1-5+3-4";//18
//        String string = "70+20*6-4";//40
        String tooMany = "";
        //将字符串转换为char型数组，并分割保存每一个字符
        char[] chars = string.toCharArray();
        //遍历每一个字符判断放入哪个栈中
        for (int i = 0; i < chars.length; i++) {
            //判断当前的字符是数字还是运算符
            if (!numStack.isCh(chars[i])){//代表是数字
                // 判断当前位置下一个是否为字符,是则直接添加进数字栈
                tooMany += chars[i];
                //说明此时i已经是最后一个字符了，不必再考虑其后面的情况，可以直接添加
                if (i == chars.length -1){
                    numStack.addData(chars[i] - 48);
                }else if (charStack.isCh(chars[i+1])){//表示当前i位置后面是运算符
                    numStack.addData(Integer.parseInt(tooMany));
                }else {//当前i的后面还是数字，跳过此次循环
                    continue;
                }
                //非常关键的一步！！！！，不清空则会影响后面的字符叠加
                tooMany = "";
            }else if (charStack.isCh(chars[i])){//代表是运算符
                //判断当前字符栈是否为空，为空则直接入栈
                if (charStack.isEmpty()){
                    charStack.addData(chars[i]);
                }else{
                    //判断当前操作符和栈中已存在的操作符的优先级
                    int curPriority = charStack.charPriority(chars[i]);//当前操作符优先级
                    int ch = charStack.stackTopValue();//栈中已存在的操作符
                    int stackPriority = charStack.charPriority(ch);//栈中已存在的操作符的优先级
                    //代表当前操作符优先级比栈中已存在的操作符的优先级要低
                    if (curPriority <= stackPriority){
                        int num1 = numStack.popData();
                        int num2 = numStack.popData();
                        int c = charStack.popData();
                        int result = numStack.count(num1, num2,c);
                        //将计算结果放入数字栈中
                        numStack.addData(result);
                        //将当前操作符放入字符栈中
                        charStack.addData(chars[i]);
                    }else{
                        //将当前操作符直接放入字符栈中
                        charStack.addData(chars[i]);
                    }
                }
            }
        }

        while (true){
            if (charStack.isEmpty()){
                break;
            }
            int num1 = numStack.popData();
            int num2 = numStack.popData();
            int ch = charStack.popData();
            int result = numStack.count(num1,num2,ch);

            //将结果放入栈中
            numStack.addData(result);
            if (charStack.getTop() == -1){
                break;
            }
        }

        System.out.println("计算的值是：" + numStack.popData());
        System.out.println();
        numStack.showStack();
        System.out.println();
        charStack.showStack();

    }
}

class CustomizedStack{
    private int arraySize;//记录数组的实际容量
    private int top = -1;//栈顶
    private int bottom = -1;//栈底
    private int[] array;//数组模拟的栈

    public CustomizedStack() {
    }

    public CustomizedStack(int arraySize) {
        this.arraySize = arraySize;
        this.array = new int[this.arraySize];
    }

    @Override
    public String toString() {
        return "CustomizedStack{" +
                "top=" + top +
                ", bottom=" + bottom + '}';
    }

    public int getTop() {
        return top;
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
    public int popData(){
        if (isEmpty()){
            System.out.println("栈中没有任何数据");
            return -2;
        }
        //先把数组中top位置的数值返回，再将top指针-1，调整栈的元素
        return array[top--];
    }

    //判断运算字符的优先级,数字越大优先级越高
    public int charPriority(int ch){
        if (ch == '*' || ch == '/'){
            return 1;
        }else if (ch == '+' || ch == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是否为一个运算符,返回true则代表是运算符，否则则代表为数字
    public boolean isCh(char ch){
        return ch == '*' || ch == '/' || ch == '-'|| ch == '+';
    }

    //进行运算
    public int count(int num1,int num2,int ch){
        int result = 0;//储存最终的计算结果
        switch (ch){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                //经过推算，无论是num2>num1或num2<num1的情况都适用
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;

    }

    //查看当前栈的栈顶值
    public int stackTopValue(){
        return array[top];
    }

}


