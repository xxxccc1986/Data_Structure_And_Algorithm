package tree.threadedbinarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 测试线索二叉树
 * @date 2022/10/14 19:20
 */
public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        //创建所需的节点
        HeroNode root = new HeroNode(1, "1");
        HeroNode node2 = new HeroNode(3, "3");
        HeroNode node3 = new HeroNode(6, "6");
        HeroNode node4 = new HeroNode(8, "8");
        HeroNode node5 = new HeroNode(10, "10");
        HeroNode node6 = new HeroNode(14, "14");

        //本次先手动构建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 创建二叉树
        ThreadedBinaryTree tree = new ThreadedBinaryTree(root);
        // tree.midOrder();
        //测试线索化
        tree.toMidThreadedBinaryTree();
        // System.out.println("10号节点的前驱节点：" + node5.getLeft());
        // System.out.println("10号节点的后继节点：" + node5.getRight());
        HeroNode.showThreadedBinaryTree(root);
    }
}
