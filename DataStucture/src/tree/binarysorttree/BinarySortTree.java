package tree.binarysorttree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 二叉排序树
 * @date 2022/10/21 23:07
 */
public class BinarySortTree {
    private Node root;

    public void delete(int targetNodeVal){
        if (root.left == null && root.right == null){
            root = null;
        }else{
            root.delete(targetNodeVal);
        }
    }


    // public Node searchTargetNodeParent(Node node){
    //     if (node != null){
    //         return root.searchTargetNodeParent(node);
    //     }
    //     return null;
    // }
    // //
    // public Node searchTargetNode(int targetNodeVal){
    //     if (targetNodeVal > 0){
    //         return root.searchTargetNode(targetNodeVal);
    //     }
    //     return null;
    // }

    public void add(Node node){
        if (root == null){ //如果根节点为null直接添加
            root = node;
        }else{
            root.add(node);
        }
    }

    public void midOrder(){
        if (root == null){
            System.out.println("当前root节点为空，不可遍历");
        }else{
            root.midOrder();
        }
    }

}
