package tree.binarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 二叉树测试
 * @date 2022/10/13 18:31
 */
public class BinaryTreeTest {
    public static void main(String[] args) {

        //创建所需的节点
        HeroNode root = new HeroNode(1, "1");
        HeroNode node2 = new HeroNode(2, "2");
        HeroNode node3 = new HeroNode(3, "3");
        HeroNode node4 = new HeroNode(4, "4");
        HeroNode node5 = new HeroNode(5, "5");

        //本次先手动构建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        // 创建二叉树
        BinaryTree binaryTree = new BinaryTree(root);

        // //测试遍历
        // System.out.println("前序遍历");
        // binaryTree.preOrder();
        // System.out.println("中序遍历");
        // binaryTree.midOrder();
        // System.out.println("后序遍历");
        // binaryTree.postOrder();

        // //测试查找
        // System.out.println("前序遍历");
        // System.out.println(binaryTreeSearch.preSearch(5));
        // System.out.println("中序遍历");
        // System.out.println(binaryTreeSearch.midSearch(5));
        // System.out.println("后序遍历");
        // System.out.println(binaryTreeSearch.postSearch(5));

        //测试删除
        System.out.println("删除前的二叉树：");
        binaryTree.preOrder();
        System.out.println("\n删除后的二叉树：");
        binaryTree.delete(5);
        binaryTree.preOrder();
    }
}

