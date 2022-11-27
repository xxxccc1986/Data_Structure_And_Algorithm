package tree.avl;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 节点类
 * @date 2022/10/25 20:13
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    public void add(Node node){
        if (node.value < this.value) {
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }
        }

        if (node.value > this.value) {
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        }

        /*因为每次添加节点之后树的左右子树的高度都会产生变化，
        必须在添加完一个节点后就判断是否是平衡二叉树，是否需要旋转
        当右子树-左子树高度大于1时，说明需要向左旋转
        当左子树-右子树高度大于1时，说明需要向右旋转
        具体思路参考md文档的双旋转描述
         */
        if(this.getRightTree() - this.getLeftTree() > 1){
            if(this.right.getLeftTree() > this.right.getRightTree()){
                this.right.rightRotation();
            }
            this.leftRotation();
        }else if (this.getLeftTree() - this.getRightTree() > 1){
            if(this.left.getRightTree() > this.left.getLeftTree()){
                this.left.leftRotation();
            }
            this.rightRotation();
        }
    }

    public void midOrder(){
        if (this.left != null){
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.midOrder();
        }
    }

    /**
     * Description : 获取以当前节点作为根节点的树的高度
     * 因为树有可能有两个子树或者只有一个，因此需要比较两个子树的高度返回最大的
     * 为什么需要+1？因为这上面的比较只是计算了从当前节点的子节点起的，而当前节点
     * 也算是1层，因此必须+1保证获取树高度的正确性
     * @date 2022/10/25
     **/
    public int getTreeHeight(){
        return Math.max(this.left == null? 0 : this.left.getTreeHeight(),
                this.right == null ? 0 : this.right.getTreeHeight()) + 1;
    }

    /**
     * Description : 获取某个节点左子树的高度
     * @date 2022/10/25
     **/
    public int getLeftTree(){
        if (this.left != null){
            return this.left.getTreeHeight();
        }
        return 0;
    }

    /**
     * Description : 获取某个节点右子树的高度
     * @date 2022/10/25
     **/
    public int getRightTree(){
        if (this.right != null){
            return this.right.getTreeHeight();
        }
        return 0;
    }

    /**
     * Description : 将非平衡二叉树左旋转为平衡二叉树
     * 基本思路：
     * 1.创建一个新节点，复制当前根节点的值作为它的值
     * 2.将新节点的左子节点设置为当前根节点的左子节点
     * 3.将新节点的右子节点设置为当前根节点的右子节点的左子节点
     * 4.将当前根节点的值修改为它的右子节点的值
     * 5.将当前根节点的右子节点设置为它右子节点的右子节
     * 6.将当前根节点的左子节点设置为新节点
     * @date 2022/10/25
     **/
    public void leftRotation(){
        //1.
        Node newNode = new Node();
        newNode.value = this.value;
        //2.
        newNode.left = this.left;
        //3.
        newNode.right = this.right.left;
        //4.
        this.value = this.right.value;
        //5.
        this.right = this.right.right;
        //6.
        this.left = newNode;
    }

    /**
     * Description : 将非平衡二叉树右旋转为平衡二叉树
     * 基本思路：
     * 1.创建一个新节点，复制当前根节点的值作为它的值
     * 2.将新节点的右子节点设置为当前根节点的右子节点
     * 3.将新节点的左子节点设置为当前根节点的左子节点的右子节点
     * 4.将当前根节点的值修改为它的左子节点的值
     * 5.将当前根节点的左子节点设置为它左子节点的左子节
     * 6.将当前根节点的右子节点设置为新节点
     * @date 2022/10/25
     **/
    public void rightRotation(){
        //1.
        Node newNode = new Node();
        newNode.value = this.value;
        //2.
        newNode.right = this.right;
        newNode.left = this.left.right;
        //3.
        this.value = this.left.value;
        //4.
        this.left = this.left.left;
        //5.
        this.right = newNode;
    }
}
