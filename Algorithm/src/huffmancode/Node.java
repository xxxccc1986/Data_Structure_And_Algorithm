package huffmancode;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 构建哈夫曼树的节点
 * @date 2022/10/21 17:33
 */
public class Node implements Comparable<Node>{
    public Byte date;
    public int weight;
    public Node left;
    public Node right;

    public Node(Byte date, int weight) {
        this.date = date;
        this.weight = weight;
    }

    public Node(int weight) {
        this.weight = weight;
    }

    public Byte getDate() {
        return date;
    }

    public void setDate(Byte date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date=" + date +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }


    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
