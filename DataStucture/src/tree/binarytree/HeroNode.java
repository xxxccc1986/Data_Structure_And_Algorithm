package tree.binarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 节点类
 * @date 2022/10/13 22:25
 */
public class HeroNode {
    private int id;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null

    public HeroNode() {
    }

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

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    /**
     * Description : 前序遍历
     * 顺序是根节点 ==> 左子树 ==> 右子树
     * @date 2022/10/13
     **/
    public void preOrder() {
        //先输入当前节点
        System.out.println(this);
        /* 遍历左子树
        先判断左子树是否有数据,若有数据再次进行前序遍历
         */
        if (this.getLeft() != null) {
            this.getLeft().preOrder();
        }
        /* 遍历右子树
        先判断右子树是否有数据,若有数据再次进行前序遍历
         */
        if (this.getRight() != null) {
            this.getRight().preOrder();
        }
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
     * Description : 后序遍历
     * 顺序是左子树 ==> 右子树 ==> 根节点
     * @date 2022/10/13
     **/
    public void postOrder() {
        /* 遍历左子树
        先判断左子树是否有数据,若有数据再次进行后序遍历
         */
        if (this.getLeft() != null) {
            this.getLeft().postOrder();
        }
        /* 遍历右子树
        先判断右子树是否有数据,若有数据再次进行后序遍历
         */
        if (this.getRight() != null) {
            this.getRight().postOrder();
        }
        //先输入当前节点
        System.out.println(this);
    }

    /*===================================以上为遍历方法，以下是查找方法===================*/

    /**
     * Description : 前序遍历查找(需要查找4次)
     * 定位到需要对比的位置的比较不算查找，只有正在判断当前数是否所求的数字才是一次查找
     * node这个临时节点非常重要，如果没有这个临时节点即使找到目标数，也会继续执行每次递归
     * 栈中的剩余内容，有可能你会想到了直接return，但是return直接返回也不能阻止递归的执行
     * 直至，整个方法栈执行完毕，因此也不能找到正确的数。
     * @date 2022/10/13
     * @param id 待查找节点的id
     **/
    public HeroNode preSearch(int id){
        System.out.println("进行前序遍历~~");
        HeroNode node = null;
        //判断当前节点是否对查找节点
        if (this.getId() == id){
            return this;
        }
        //判断左子树是否为空，不为空则向左递归进行前序遍历查询
        if (this.getLeft() != null){
            node = this.getLeft().preSearch(id);
        }
        //如果node不为空，即代表已经找到对应的目标节点了
        if (node != null){
            return node;
        }
        //判断右子树是否为空，不为空则向右递归进行前序遍历查询
        if (this.getRight() != null){
            node = this.getRight().preSearch(id);
        }
        return node;
    }

    /**
     * Description : 中序遍历查找(需要查找3次)
     * @date 2022/10/13
     * @param id 待查找节点的id
     **/
    public HeroNode midSearch(int id){
        HeroNode node = null;
        //判断左子树是否为空，不为空则向左递归进行中序遍历查询
        if (this.getLeft() != null){
            node = this.getLeft().midSearch(id);
        }
        //判断当前节点是否对查找节点
        if (this.getId() == id){
            return this;
        }
        System.out.println("进行中序遍历~~");
        if (node != null){
            return node;
        }
        //判断右子树是否为空，不为空则向右递归进行中序遍历查询
        if (this.getRight() != null){
            node = this.getRight().midSearch(id);
        }
        return node;
    }

    /**
     * Description : 后序遍历查找
     * @date 2022/10/13
     * @param id 待查找节点的id
     **/
    public HeroNode postSearch(int id){
        HeroNode node = null;
        //判断左子树是否为空，不为空则向左递归进行后序遍历查询
        if (this.getLeft() != null){
            node = this.getLeft().postSearch(id);
        }
        if (node != null){
            return node;
        }
        //判断右子树是否为空，不为空则向右递归进行后序遍历查询
        if (this.getRight() != null){
            node = this.getRight().postSearch(id);
        }
        System.out.println("进行后序遍历~~");
        //判断当前节点是否对查找节点
        if (this.getId() == id){
            return this;
        }
        return node;
    }

    /*===================================以上为查找方法，以下是删除方法===================*/

    /*
    要求：
        如果删除的节点是叶子节点，则删除该节点
        如果删除的节点是非叶子节点，则删除该子树.
        测试，删除掉 5号叶子节点 和 3号子树.
     */
    /**
     * Description : 删除指定id的二叉树节点
     * 由于这个二叉树是一个单向的，因此不能去判断当前节点是否为需要删除的节点
     * 因为无法通过当前节点找到其父节点去修改指向，其实就是单向链表删除的思路
     * 具体思路：1.先判断当前删除的是不是root根节点，是的话直接置空root整个树
     * 2.不是根节点的话，先判断删除的是否为根节点的左子节点或根节点的右子节点
     * 3.如果都不是，就先开始向左递归查找对应节点
     * 4.如果左边递归找不到，再开始向右递归查找对应节点
     * @date 2022/10/14
     * @param id 待删除的节点id
     **/
    public void deleteNode(int id){
        //由于root在一开始就判断过了，因此能进来的节点必不可能是root节点
        //假设root = 1
        HeroNode leftNode = this.getLeft();//2
        HeroNode rightNode = this.getRight();//3
        //不为空左子节点或者右子节点才能进入
        if (leftNode != null && rightNode != null) {
                //判断左子节点是否为需要删除的节点
                if (leftNode.getId() == id){
                    //表示不是叶子节点，左子树或右子树有节点(没必要，懒得删除这段了)
                    // if (leftNode.getLeft() != null || leftNode.getRight() != null){
                    //     //删除所有子节点
                    //     loopDelete(leftNode);
                    // }
                    //删除此相等的节点
                    this.setLeft(null);
                    return;//置空后立即结束，停止递归
                }
                //判断右子节点是否为需要删除的节点
                if (rightNode.getId() == id){
                    //表示不是叶子节点，左子树或右子树有节点(没必要，懒得删除这段了)
                    // if (rightNode.getLeft() != null && rightNode.getRight() != null){
                    //     //删除所有子节点
                    //     loopDelete(rightNode);
                    // }
                    //删除此相等的节点
                    this.setRight(null);
                    return;
                }
        }
        //向左递归遍历寻找
        if (this.getLeft() != null){
            this.getLeft().deleteNode(id);
        }
        //向右递归遍历寻找
        if (this.getRight() != null){
            this.getRight().deleteNode(id);
        }
    }

    /**
     * Description : 递归删除方法
     * 想复杂了这个方法其实不需要的，因为删除的如果是作为子树的节点的话，
     * 其下方的所有节点也在同一时间被删除了，因为没有指向子树，那么子树下的节点也不会被指向了
     * @date 2022/10/14
     * @param node 待删除的节点
     **/
    public void loopDelete(HeroNode node){//3 ==> 5 ==> 4
        HeroNode leftNode = node.getLeft();//获取当前左子节点
        HeroNode rightNode = node.getRight();//获取当前右子节点
        //向左递归删除所有左子节点
        if (leftNode.getLeft() != null){
           loopDelete(leftNode);
        }
        //执行到这里代表，已经找到了左边的叶子节点
        node.setLeft(null);
        //向右递归删除所有右子节点
        if (rightNode.getRight() != null){
            loopDelete(rightNode);
        }
        //执行到这里代表，已经找到了右边的叶子节点
        node.setRight(null);
    }
}
