package huffmancode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 赫夫曼编码测试
 * 思路：1.统计各个字符在字符串中出现的次数
 * 将字符串转换为byte数组，并统计各自byte的次数
 * 2.将各个字符的值和次数作为属性创建对应的node节点
 * 3.将创建的node进行排序，添加进List中
 * 4.根据之前创建赫夫曼树的方式再次进行创建赫夫曼树
 * @date 2022/10/21 17:30
 */
public class HuffManCode {
    public static void main(String[] args) {
        String  string = "i like like like java do you love a java";
        byte[] bytes = string.getBytes();
        // System.out.println(Arrays.toString(bytes));//40
        List<Node> nodeList = createNodeList(bytes);
        Node root = createHuffManTree(nodeList);
        root.preOrder();

    }

    /**
     * Description : 创建用于创建赫夫曼树的集合
     * @date 2022/10/21
     * @param bytes 由字符串转为byte的数组
     **/
    public static List<Node> createNodeList(byte[] bytes){
        List<Node> listNodes = new ArrayList<>();
        boolean flag = false;//用于判断对象是否在集合中存在
        for (byte data : bytes) {
            //上来就先创建对象，并赋值为1
            Node node = new Node(data, 1);
            //再判断是否需要加入集合中还是修改权值
            for (Node listNode : listNodes) {
                if (listNode.date.equals(node.date)) {//flag = true代表重复,仅修改权值不添加入集合
                    flag = true;
                    listNode.weight += 1;
                    break;
                }
            }
            if (!flag) { //表示不重复直接添加
                listNodes.add(node);
            }
            flag = false;//重置flag避免曾经变为true没有修改过的情况
        }
        Collections.sort(listNodes);
        return listNodes;
    }

    /**
     * Description : 创建赫夫曼树
     * @date 2022/10/21
     * @param nodeList 节点集合
     **/
    public static Node createHuffManTree(List<Node> nodeList){
        while (nodeList.size() > 1){
            //取出第一第二个节点(二叉树)
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);

            //创建新的二叉树,此二叉树没有data，只有权值，因为权值都是放在了叶子节点
            Node node = new Node(leftNode.weight + rightNode.weight);
            node.left = leftNode;
            node.right = rightNode;
            //移除取出的节点
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            //将新的二叉树加入集合中重新进行排序
            nodeList.add(node);
            Collections.sort(nodeList);
        }
        return nodeList.get(0);
    }
}
