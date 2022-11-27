package stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 使用两个栈实现中缀表达式转换为后缀表达式，以及后缀表达式的计算
 * @date 2022/6/10 0:16
 */
public class InversePolandNotation {
    public static void main(String[] args) {
        //中缀表达式 (3+4)*5-6对应的后缀表达式
        // (((3+4)*5)-6) ==> 3 4 + 5 * 6 -
        //为了方便逆波兰表达式的计算使用空格将每个字符进行分割
//        String suffixExpression = "3 4 + 5 * 6 -";

        //中缀表达式 4*5-8+60+8/2对应的后缀表达式
        // ((((4*5)-8)+60)+(8/2)) ==> 4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        String suffixExpression = "1+((2+3)*4)-5";
        List<String> list = InversePolandNotation.inFixExpression(suffixExpression);
        System.out.println("ArrayList：" + list);

        //将中缀表达式表转换为后缀表达式
        List<String> theSuffixExpression = InversePolandNotation.inFixToSuffixExpression(list);
        System.out.println("SuffixExpression:" + theSuffixExpression);

//        String[] n = new String[list.size()];
//        int start = 0;
//        for (String nb : list){
//            while (list.size() >= 0){
//                n[start] = nb;
//                start += 1;
//            }
//
//        }



        //计算后缀表达式的结果
//        String[] array = suffixExpression.split(" ");
//        int result = InversePolandNotation.ReversePolishNotationCount(n);
//        System.out.println("后缀表达式的结果是：" + result);


    }


    //计算当前的逆波兰表达式的值
    public static int ReversePolishNotationCount(String[] array){
        if (array.length == 0){
            throw new RuntimeException("传入的数组为空");
        }
        int result = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            /*匹配的是1-10和多位数字，判断是否为数字
            如果是，则直接压入栈中，
            不是则将栈中两个数字弹出按照当前运算符类型进行计算，再将结果压入栈中
            */
            if (array[i].matches("\\d+")){
                stack.push(array[i]);
            }else{
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                if ("+".equals(array[i])){
                    result = num2 + num1;
                }else if ("-".equals(array[i])){
                    result = num2 - num1;
                }else if ("*".equals(array[i])){
                    result = num2 * num1;
                }else if ("/".equals(array[i])){
                    result = num2 / num1;
                }else{
                    throw new RuntimeException("当前字符不合法");
                }
                //将计算得到的结果入栈
                stack.push(String.valueOf(result));
            }
        }
        return result;
    }

    //将输入的中缀表达式各个符号逐个分割
    public static List<String> inFixExpression(String expression){
        if ("".equals(expression)){
            throw new RuntimeException("表达式为空");
        }
        List<String> list = new ArrayList<>();//用于储存扫描后的中缀表达式
        String string = "";//用于拼接字符串

        //将字符串变为数组以便于遍历
        char[] array = expression.toCharArray();
        for (int i = 0; i < array.length; i++) {
            //i == 数组最后一个元素则直接添加即可
            if (i == array.length - 1){
                list.add(String.valueOf(array[i]));
                break;
            }
            //判断是否为符号
            if (InversePolandNotation.isCh(array[i])){
                list.add(String.valueOf(array[i]));
            }else{
                //判断此位置后面是否还是数字
                string += String.valueOf(array[i]);
                if (!InversePolandNotation.isCh(array[i+1])){
                    continue;
                }else{
                    list.add(string);
                }
            }
            string = "";
        }
        return list;
    }

    //将中缀表达式转换为后缀表达式
    public static List<String> inFixToSuffixExpression(List<String> list){
        //将传入的list集合转为char数组便于遍历
//        System.out.println(list);
        char[] array = new char[list.size()];
        for (int i = 0; i <= list.size() -1 ; i++) {
            String s = list.get(i);
            array[i] = s.charAt(0);
        }

        //创建两个栈用于存储不同的字符
        Stack<Character> numStack = new Stack<>();//放数字
        Stack<Character> charStack = new Stack<>();//放操作符

        //遍历转换后中的集合的数组
        for (int i = 0; i < array.length; i++) {
            if (InversePolandNotation.isCh(array[i])){//属于字符
                while (true){
                    //如果当前字符栈为空,或当操作符和栈顶操作符相同且都是左括号（时,
                    //可用直接将当前字符添加入字符栈中
                    if (charStack.empty() || array[i] == charStack.peek() && charStack.peek() == '('){
                        charStack.push(array[i]);
                        break;
                    }
                    //又或者是左括号（时也可以直接添加入符号栈
                    else if (array[i] == '('){
                        charStack.push(array[i]);
                        break;
                    }
                    //如果当前字符为)右括号，则需要将符合栈中的运算符弹出压入数字栈
                    // ，直至遇到(左括号
                    else if (array[i] == ')'){
                        while (charStack.peek() != '('){
                            //弹出符号栈栈顶元素
                            Character first = charStack.pop();
                            //压入数字栈中
                            numStack.push(first);
                        }
                        //将当前的(出栈
                        charStack.pop();
                        break;
                    } else{//不为空
                        Character top = charStack.peek();//查看栈顶的值
                        int topLevel = InversePolandNotation.charPriority(top);
                        int curLevel = InversePolandNotation.charPriority(array[i]);
                        //栈顶运算比当前运算符优先级低
                        if (curLevel > topLevel){//当前操作符等级更高，直接入栈
                            charStack.push(array[i]);
                            break;
                        }else{//当前操作符等级比栈顶运算符等级低
                            Character c1 = charStack.pop();//弹出符号栈栈顶元素
                            numStack.push(c1);//将被弹出的运算符压入数字栈中
                            charStack.push(array[i]);//将当前运算符加入到符号栈
                            break; //必须结束循环，否则会出现死循环
                        }
                    }
                }
            }else{//属于数字
                numStack.push(array[i]);
            }
        }
        //将字符栈的元素逐个弹出压入数字栈
        while (!charStack.empty()){
            Character theEnd = charStack.pop();
            numStack.push(theEnd);
        }

        List<String> endList = new ArrayList<>();
        Character[] test = new Character[numStack.size()];
        int start = 0;
        while (numStack.size() > 0){
            Character pop = numStack.pop();
            if (start < test.length){
                test[start] = pop;
            }
            start += 1;
        }
        for (int i = test.length-1; i >= 0 ; i--) {
            endList.add(String.valueOf(test[i]));
        }
        return endList;
    }


    //判断是否为一个运算符,返回true则代表是运算符，否则则代表为数字
    public static boolean isCh(char ch){
        return ch == '*' || ch == '/' || ch == '-'|| ch == '+' || ch == '(' ||ch == ')';
    }

    //判断运算字符的优先级,数字越大优先级越高
    public static int charPriority(int ch){
        if (ch == '*' || ch == '/'){
            return 1;
        }else if (ch == '+' || ch == '-'){
            return 0;
        }else if (ch == '(' || ch == ')'){
            return -1;
        }else {
            return -2;
        }
    }

}




