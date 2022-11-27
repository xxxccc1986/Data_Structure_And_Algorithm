package stack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 以下解决思路只是选择使用一个栈和一个集合相互辅助解决实现
 * 中缀表达式到后缀表达式的实现以及后缀表达式的计算结果
 * @date 2022/6/10 0:16
 */
public class InversePolandNotation2 {
    public static void main(String[] args) {
        //中缀表达式 (3+4)*5-6对应的后缀表达式
        // (((3+4)*5)-6) ==> 3 4 + 5 * 6 -
        //为了方便逆波兰表达式的计算使用空格将每个字符进行分割
//        String suffixExpression = "3 4 + 5 * 6 -";

        //中缀表达式 4*5-8+60+8/2对应的后缀表达式
        // ((((4*5)-8)+60)+(8/2)) ==> 4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        String suffixExpression = "1+((2+3)*4)-5";
        List<String> list = InversePolandNotation2.inFixExpression(suffixExpression);
        System.out.println("中缀表达式是：" + list);

        //将中缀表达式表转换为后缀表达式
        List<String> theSuffixExpression = InversePolandNotation2.inFixToSuffixExpression(list);
        System.out.println("后缀表达式是:" + theSuffixExpression);

        String[] array = theSuffixExpression.toArray(new String[0]);
        //计算后缀表达式的结果
        int result = InversePolandNotation.ReversePolishNotationCount(array);
        System.out.println("后缀表达式的计算结果是：" + result);


    }


    //计算当前的逆波兰表达式的值
    public static int ReversePolishNotationCount(String[] array){
        if (array.length == 0){
            throw new RuntimeException("传入的数组为空");
        }
        int result = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].matches("\\d+")){//匹配的是多位数
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
    public static List inFixExpression(String expression){
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
            if (InversePolandNotation2.isCh(array[i])){
                list.add(String.valueOf(array[i]));
            }else{
                //判断此位置后面是否还是数字
                string += String.valueOf(array[i]);
                if (!InversePolandNotation2.isCh(array[i+1])){
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
        //将传入的list集合转为string数组便于遍历
        String[] inFix = list.toArray(new String[0]);

        //创建一个集合和一个栈用于存储运算符和数字
        List<String> suffixList = new ArrayList<>();//最终的后缀表达式
        Stack<String> charStack = new Stack<>();//放运算符

        //遍历转换后中的集合的数组
        for (int i = 0; i < inFix.length; i++) {
            if (!inFix[i].matches("\\d+")){//属于字符
                while (true){
                    //如果当前字符栈为空,或当操作符和栈顶操作符相同且都是左括号（时,
                    //可用直接将当前字符添加入字符栈中
                    if (charStack.empty() || inFix[i].equals(charStack.peek()) && "(".equals(charStack.peek())){
                        charStack.push(inFix[i]);
                        break;
                    }
                    //又或者是左括号（时也可以直接添加入符号栈
                    else if ("(".equals(inFix[i])){
                        charStack.push(inFix[i]);
                        break;
                    }
                    //如果当前字符为)右括号，则需要将符合栈中的运算符弹出压入数字栈
                    // ，直至遇到(左括号
                    else if (")".equals(inFix[i])){
                        while (!"(".equals(charStack.peek())){
                            //弹出符号栈栈顶元素
                            String first = charStack.pop();
                            //压入记录后缀表达式的集合中中
                            suffixList.add(first);
                        }
                        //将当前的(出栈
                        charStack.pop();
                        break;
                    } else{//不为空
                        String top = charStack.peek();//查看栈顶的值
                        int topLevel = InversePolandNotation2.charPriority(top);
                        int curLevel = InversePolandNotation2.charPriority(inFix[i]);
                        //栈顶运算比当前运算符优先级低
                        if (curLevel > topLevel){//当前操作符等级更高，直接入栈
                            charStack.push(inFix[i]);
                            break;
                        }else{//当前操作符等级比栈顶运算符等级低
                            String c1 = charStack.pop();//弹出符号栈栈顶元素
                            suffixList.add(c1);//压入数字栈中
                            charStack.push(inFix[i]);//将当前运算符加入到符号栈
                            break; //必须结束循环，否则会出现死循环
                        }
                    }
                }
            }else{//属于数字
                suffixList.add(inFix[i]);
            }
        }
        //将字符栈的元素逐个弹出压入用于存储后缀表达式的集合中
        while (!charStack.empty()){
            String theEnd = charStack.pop();
            suffixList.add(theEnd);
        }
        return suffixList;
    }


    //判断是否为一个运算符,返回true则代表是运算符，否则则代表为数字
    public static boolean isCh(char ch){
        return ch == '*' || ch == '/' || ch == '-'|| ch == '+' || ch == '(' ||ch == ')';
    }

    //判断运算字符的优先级,数字越大优先级越高
    public static int charPriority(String ch){
        if ("*".equals(ch) ||"/".equals(ch)){
            return 1;
        }else if ("+".equals(ch) ||"-".equals(ch)){
            return 0;
        }else if ("(".equals(ch) ||")".equals(ch)){
            return -1;
        }else {
            return -2;
        }
    }

}




