package tree.haffmantree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 赫父曼树
 * @date 2022/10/18 23:25
 */
public class HuffManTree {
    public static void main(String[] args) {
        int[] array = {13,7,8,3,29,6,1};

        Node root = createHuffManTree(array);
        root.preOrder(root);

    }

    public static Node createHuffManTree(int[] array){
        List<Node> nodes = new ArrayList<>();
        for (int i: array){
            nodes.add(new Node(i));
        }
        //以集合来表示各个二叉树，每个元素都是一个二叉树
        Collections.sort(nodes);

        while (nodes.size() > 1){
            //取出集合中的前两个元素(即两个二叉树)
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //进行构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            //设置当前二叉树的左右节点
            parent.left = leftNode;
            parent.right = rightNode;

            //移除取出的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将新构建的二叉树加入集合并对集合内的对象元素重新排序
            nodes.add(parent);
            Collections.sort(nodes);
        }
        /*
        当从上面的循环中出来，此时集合中只有最终的一个根节点，
        因为在取出数据构成新二叉树后又进行了对原先取出数据的删除操作，
        再加入了新构建的节点(二叉树)
         */
        return nodes.get(0);
    }
}
