package tree.threadedbinarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 节点类
 * 目前出现一个问题 在线索化要判断当前节点是否需要线索化且需要设置当前节点是什么类型的节点
 * 因为考虑到线索化后的二叉树，有些节点的左指针有可能是指向它的左子节点
 * 但也些节点的左指针是指向前驱节点的，因此在遍历的时候需要判断是否什么类型的节点
 * 第一点可用通过判断是否有空余的指针解决，剩下这个有点难解决
 * 有没有一种方式同时判断或者设置当前节点的类型？？？
 * 通过在节点上设置两个属性来记录对应的类型
 * @date 2022/10/13 22:25
 */
public class HeroNode {
    private int id;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null
    private boolean leftType; //默认为false，false代表指向左子树，true代表指向前驱节点
    private boolean rightType;//默认为false，false代表指向右子树，true代表指向后继节点

    public HeroNode() {}

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public boolean isLeftType() {
        return leftType;
    }

    public void setLeftType(boolean leftType) {
        this.leftType = leftType;
    }

    public boolean isRightType() {
        return rightType;
    }


    public void setRightType(boolean rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    /**
     * Description : 中序遍历
     * 顺序是左子树 ==> 根节点 ==> 右子树
     * @date 2022/10/13
     **/
    public void midOrder() {
        /* 遍历左子树
        先判断左子树是否有数据,若有数据再次进行中序遍历
         */
        if (this.getLeft() != null) {
            this.getLeft().midOrder();
        }
        //先输入当前节点
        System.out.println(this);
        /* 遍历右子树
        先判断右子树是否有数据,若有数据再次进行中序遍历
         */
        if (this.getRight() != null) {
            this.getRight().midOrder();
        }
    }

    /**
     * Description : 中序二叉树转为中序线索二叉树
     * @date 2022/10/14
     * @param node 当前遍历的节点
     **/
    public void midThreadedBinaryTreeTest(HeroNode node){//1
            //为空不可线索化，递归结束的条件
            if (node == null){
                return;
            }
            //1.对左子树线索化
            midThreadedBinaryTreeTest(node.getLeft());
            /*2.对中间数线索化
            当从上面的递归方法来到这步，则代表遍历到最左边的叶子节点了
             */
            if (node.getLeft() == null){
                //线索化左边指针
                node.setLeft(ThreadedBinaryTree.pre);
                node.setLeftType(true);
            }
            /*
            ThreadedBinaryTree.pre是定义在别的类中保存当前节点的上一个节点的静态属性，
            让其值不会随着栈的压栈操作结束消失。
            当这个公共属性不为空且它的右指针为空时代表就是上个未进行右指针线索化的节点。
             */
            if (ThreadedBinaryTree.pre != null && ThreadedBinaryTree.pre.getRight() == null){
                //线索化上个节点尚未完成的右指针
                ThreadedBinaryTree.pre.setRight(node);
                ThreadedBinaryTree.pre.setRightType(true);

            }
            /*
            这一步很重要，让pre属性能够动态保存多个节点的对应的自己的上个节点，
            缺少这步线索化直接失败
             */
            ThreadedBinaryTree.pre = node;

            //3.对右子树线索化
            midThreadedBinaryTreeTest(node.getRight());
    }

    /**
     * Description : 遍历中序线索二叉树
     * @date 2022/10/17
     * @param node 根节点
     **/
    public static void showThreadedBinaryTree(HeroNode node){

        while (node != null){
            //先找到第一个左边指针指向前驱节点的元素，此节点的leftType属性被定义为了true
            while (!node.leftType){
                node = node.getLeft();
            }
            System.out.println(node);
            /*
            node.rightType为true表示当前节点的右指针指向其后继节点，
            后继节点为当前节点的下一个节点
             */
            while (node.rightType){
                node = node.getRight();
                System.out.println(node);
            }
            //当从上面循环出来，代表此时n当前节点的右指针指向其子树
            //用当前节点的右子树节点代替当前节点继续判断
            node = node.getRight();
        }
    }



    /* 失败版本*/
    public void midThreadedBinaryTree(HeroNode node,HeroNode tempNode){//1
        //当前能进来的节是根节点且绝对不可能为空
        //由于是中序遍历需要获得当前节点的左子节点
        HeroNode leftNode = node.getLeft();
        // HeroNode pre = null;
        //遍历子树的左节点
        if(leftNode != null){
                //当leftNode代表找到了左子树叶子节点，进行判断是否需要左右线索化
                if (leftNode.getLeft() == null){
                    leftNode.setLeft(tempNode);
                    leftNode.setLeftType(true);
                    if (leftNode.getRight() == null){
                        leftNode.setRightType(true);
                        leftNode.setRight(node);
                    }
                }
                else{
                    tempNode = node;
                    midThreadedBinaryTree(leftNode,tempNode);

                }
            }

            //由于是中序遍历需要获得当前节点的右子节点
            HeroNode rightNode = node.getRight();//6
            //遍历子树的右节点
            if (rightNode != null){
                if (rightNode.getLeft() == null){
                    rightNode.setLeft(node);
                    rightNode.setLeftType(true);
                    if (rightNode.getRight() == null){
                        rightNode.setRight(tempNode);
                        rightNode.setRightType(true);
                    }
                }

                else{
                    midThreadedBinaryTree(rightNode,tempNode);
                }
            }
    }
}
