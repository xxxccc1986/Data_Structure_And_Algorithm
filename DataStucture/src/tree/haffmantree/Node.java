package tree.haffmantree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 用于演示的节点类
 * 实现Comparable<Node>接口时为了使用Collections工具类的sort对
 * 装有node对象的List集合元素进行排序
 * @date 2022/10/18 23:56
 */
public class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    public Node(int value){
        this.value = value;
    }


    @Override
    public int compareTo(Node o) {
        //升序排序
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrder(Node root){
        if (root == null){
            System.out.println("当前节点为空,不能遍历");
        }else{
            //输出当前的节点
            System.out.println(root);
            //递归遍历输出左子树
            Node left = root.left;
            if (left != null){
                preOrder(left);
            }

            //递归遍历右子树
            Node right = root.right;
            if (right != null){
                preOrder(right);
            }

        }
    }
}
