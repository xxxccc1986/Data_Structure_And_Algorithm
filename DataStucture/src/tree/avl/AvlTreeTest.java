package tree.avl;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 平衡二叉树测试
 * @date 2022/10/25 20:43
 */
public class AvlTreeTest {
    public static void main(String[] args) {
        // int[] array = new int[]{4,3,6,5,7,8};
        // int[] array = new int[]{10,12,8,9,7,6};
        int[] array = new int[]{10,11,7,6,8,9};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < array.length; i++) {
            avlTree.add(new Node(array[i]));
        }
        avlTree.midOrder();
        // System.out.println("未进行左旋转前的二叉树");
        // System.out.println("根节点的高度：" + avlTree.root.getTreeHeight());
        // System.out.println("根节点左子树的高度：" + avlTree.root.getLeftTree());
        // System.out.println("根节点右子树的高度：" + avlTree.root.getRightTree());

        // System.out.println("进行左旋转后的二叉树");
        System.out.println("根节点的高度：" + avlTree.root.getTreeHeight());
        System.out.println("根节点左子树的高度：" + avlTree.root.getLeftTree());
        System.out.println("根节点右子树的高度：" + avlTree.root.getRightTree());
        System.out.println("根节点：" + avlTree.root.getTreeHeight());
    }
}
