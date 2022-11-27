package tree.threadedbinarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 线索二叉树类
 * @date 2022/10/14 19:20
 */
public class ThreadedBinaryTree {
    private HeroNode root;
    public static HeroNode pre = null; //保存其上一个节点，不会随着栈的压栈操作结束导致其值消失

    public ThreadedBinaryTree() {
    }

    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }

    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    public void toMidThreadedBinaryTree(){
        if (this.root == null){
            System.out.println("当前二叉树为空");
        }else{
            // root.midThreadedBinaryTree(root,null);
            root.midThreadedBinaryTreeTest(root);
        }
    }
}
