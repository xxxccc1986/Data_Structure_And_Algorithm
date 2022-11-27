package tree.binarysorttree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 排序二叉树测试
 * @date 2022/10/22 0:13
 */
public class TestDemo {
    public static void main(String[] args) {
        int[] array = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int j : array) {
            tree.add(new Node(j));
        }
        tree.midOrder();
        // System.out.println(tree.searchTargetNode(7));
        // Node targetNode = tree.searchTargetNode(2);
        // System.out.println(targetNode);
        // Node node = tree.searchTargetNodeParent(targetNode);
        // System.out.println(node);
        System.out.println();

        // tree.delete(2);
        // tree.delete(5);
        tree.delete(9);
        // tree.delete(12);
        // tree.delete(7);
        // tree.delete(3);
        // tree.delete(10);
        // tree.delete(1);
        // tree.delete(10);

        System.out.println();
        tree.midOrder();

    }
}
