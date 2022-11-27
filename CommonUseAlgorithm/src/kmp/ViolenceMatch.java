package kmp;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 暴力匹配字符串问题
 * @date 2022/11/24 18:03
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";
        int result = violenceMatch(s1, s2);
        if (result == -1){
            System.out.println("该字符串在待匹配字符串中不存在");
        }else{
            System.out.println("找到了，下标索引为：" + result);
        }
    }

    public static int violenceMatch(String matchStr,String str){
        char[] matchChar = matchStr.toCharArray();
        char[] strChar = str.toCharArray();
        int i = 0; //维护matchChar数组的指向
        int j = 0; //维护strChar数组的指向
        while (i < matchChar.length && j < strChar.length){
            if (matchChar[i] == strChar[j]){
                //匹配两个数组各自的下一个字符
                i++;
                j++;
            }else {
                //重置i和j的指向
                //回溯到匹配前的下一个字符继续匹配
                i = i - j + 1; //i-j表示将i调回到遍历j个数之前，+1表示为i的下个数
                j = 0;
            }
        }
        if (j == strChar.length){
            return i-j;
        }else {
            return -1;
        }
    }
}
