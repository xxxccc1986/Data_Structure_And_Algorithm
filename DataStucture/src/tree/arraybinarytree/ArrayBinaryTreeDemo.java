package tree.arraybinarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 顺序存储二叉树测试
 * 要求：
 * 顺序存储二叉树即以数组的方式来存放二叉树
 * 且在遍历数组arr时，仍然可以以前序遍历，中序遍历和后序遍历的方式完成结点的遍历
 * @date 2022/10/14 16:05
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        // arrayBinaryTree.preOrder(0);
        // arrayBinaryTree.midOrder(0);
        arrayBinaryTree.postOrder(0);
    }

}


