package kmp;

import java.util.Arrays;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: KMP算法解决字符串匹配问题
 * @date 2022/11/24 19:49
 */
public class KMPMach {
    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        // String s2 = "ABCDABD";
        String s2 = "CDA";
        // String s3 = "ABABCABAA";
        int result = kmpMatch(s1, s2);
        if (result == -1){
            System.out.println("该字符串在待匹配字符串中不存在");
        }else{
            System.out.println("找到了，下标索引为：" + result);
        }

        // System.out.println(Arrays.toString(getNextInt(s3)));
    }

    /**
     * Description : kmp字符串查找算法
     * @date 2022/11/25
     * @param textStr 文本串
     * @param modelStr 模式串
     **/
    public static int kmpMatch(String textStr,String modelStr){
        char[] text = textStr.toCharArray();
        char[] model = modelStr.toCharArray();
        //获得辅助比较的next数组
        int[] next = getNextInt(modelStr);
        int i = 0; //维护指向text数组的指针
        int j = 0; //维护指向model数组的指针
        while (i < text.length && j < model.length){
            if (text[i] == model[j]){ //表示当前i和j对应位字符都是一致的
                //匹配两个数组各自的下一个字符
                i++;
                j++;
            }else {
                //从next数组取出j当前失配位所对应的模式串中应该再次对比的字符下标
                j = next[j];
                //当j == -1或j == 0时表示文本创和模式串中的第一个字符就不匹配
                //将指向i的指针移动至文本串的下一个字符重新与模式串第一个字符重新匹配
                if (j == -1 || j == 0){
                    i += 1;
                    j = 0;
                }
            }
        }
        //如果是找到了才从while循环中出来，i已经是模式串对应的文本串中的最后一个匹配字符的下一个字符的下标，j已经越界
        //如果j == 模式串的长度 代表是完整遍历模式串，找到了对应字符串j越界才从while出来的，
        //如果没有完整遍历模式串，仅仅是单纯i越界出来的，j不可能大于模式串的长度，即没找到
        if (j == model.length){
            return i-j; //i-j是返回在文本串中匹配成功字符串的头部下标
        }else { //代表没找到返回-1
            return -1;
        }
    }

    /**
     * Description : 根据模式串获得next数组
     * @date 2022/11/25
     * @param modelStr 模式串(字串)
     **/
    public static int[] getNextInt(String modelStr){
        char[] str = modelStr.toCharArray();
        int[] prefixTable = new int[modelStr.length()]; //前缀表
        int i = 1; //维护str的索引，从1开始是因为0位的字串的最长公共前后缀一定为0
        int len = 0; //维护从0到i的字串最长公共前后缀的长度
        while (i < str.length){ //遍历str数组形成前缀表
            if (str[i] == str[len]){ //表示在str数组中的i和len的数相同，
                len++; //前后缀长度+1；
                prefixTable[i] = len; //将0-i的所对应的字串的前后缀长度设置为len的值
            }else { //不匹配的情况
                len = 0; //将记录最长公共前后缀长度变量重新置为0
                prefixTable[i] = 0;//将0-i的所对应的字串的前后缀长度设置为0，表示无公共前后缀
            }
            i++; //查找从0-(i+1)位置的字串的的最长公共前后缀
        }
        //将前缀表整体右移，舍弃最高位，同时首位补-1
        int[] next = new int[str.length];
        next[0] = -1;
        for (int j = 1; j < next.length; j++) {
            next[j] = prefixTable[j-1];
        }
        return next;
    }
}
