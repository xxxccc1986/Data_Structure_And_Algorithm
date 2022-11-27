package tree.binarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 二叉树遍历
 * @date 2022/10/13 22:26
 */
public class BinaryTree {
    private HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    /**
     * Description : 二叉树的前序遍历方法
     * 在其内调用了节点自身的前序遍历方法
     * 相当于这个方法只是向外暴露了调用接口
     * 实际上的具体逻辑是由节点完成的
     * 其他两个遍历也是一样的
     *
     * @date 2022/10/13
     **/
    public void preOrder() {
        //判断当前的根节点是否为空
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    public void midOrder() {
        if (this.root != null) {
            this.root.midOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    /**
     * Description : 前序遍历查找
     * @date 2022/10/13
     **/
    public HeroNode preSearch(int id){
        if (this.root == null){
            return null;
        }
        return this.root.preSearch(id);
    }

    /**
     * Description : 中序遍历查找
     * @date 2022/10/13
     **/
    public HeroNode midSearch(int id){
        if (this.root == null){
            return null;
        }
        return this.root.midSearch(id);
    }

    /**
     * Description : 后序遍历查找
     * @date 2022/10/13
     **/
    public HeroNode postSearch(int id){
        if (this.root == null){
            return null;
        }
        return this.root.postSearch(id);
    }


    /**
     * Description : 删除指定id的节点
     * @date 2022/10/14
     * @param id 待删除的节点id
     **/
    public void delete(int id){
        if (this.root == null){
            System.out.println("需要删除的二叉树为空");
        }
        //判断删除的是否为根节点
        if (this.root.getId() == id){
            this.root = null;
        }else {
            this.root.deleteNode(id);
        }
    }
}
