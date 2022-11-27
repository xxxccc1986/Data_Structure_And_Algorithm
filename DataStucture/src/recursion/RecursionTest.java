package recursion;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 递归机制demo
 * @date 2022/6/13 17:06
 */
public class RecursionTest {
    public static void main(String[] args) {
//        test(4);
        int result = factorial(2);
        System.out.println(result);
    }

    //打印问题
    public static void test(int n){
        if (n > 2){
             test(n-1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n-1) * 2;
        }
    }
}
