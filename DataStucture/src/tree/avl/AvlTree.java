package tree.avl;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 平衡二叉树
 * @date 2022/10/24 22:12
 */
public class AvlTree {
    public Node root;

    public void add(Node node){
        if (root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    public void midOrder(){
        if (root == null){
            System.out.println("当前二叉树为空，无法完成中序遍历");
        }else{
            root.midOrder();
        }
    }

}
