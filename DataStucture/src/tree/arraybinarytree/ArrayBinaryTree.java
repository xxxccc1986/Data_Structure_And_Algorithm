package tree.arraybinarytree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 顺序存储二叉树
 * @date 2022/10/14 16:13
 */
public class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree() {
    }

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }


    /**
     * Description : 以数组的方式遍历完成前序遍历
     * @date 2022/10/14
     * @param index 传入的下标值
     **/
    public void preOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("数组为空不能执行前序遍历");
            return;
        }
        //输出当前数
        System.out.println(array[index]);
        //搜索左子节点的位置并向左执行递归遍历
        int left = 2*index+1;
        if (left < array.length){
            preOrder(left);
        }

        //搜索右子节点的位置并向右执行递归遍历
        int right = 2*index+2;
        if (right < array.length){
            preOrder(right);
        }
    }

    /**
     * Description :以数组的方式遍历完成中序遍历
     * @date 2022/10/14
     * @param index 传入的下标值
     **/
    public void midOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("数组为空不能执行中序遍历");
            return;
        }
        //搜索左子节点的位置并向左执行递归遍历
        int left = 2*index+1;
        if (left < array.length){
            midOrder(left);
        }
        //输出当前数
        System.out.println(array[index]);
        //搜索右子节点的位置并向右执行递归遍历
        int right = 2*index+2;
        if (right < array.length){
            midOrder(right);
        }
    }


    /**
     * Description : 以数组的方式遍历完成后序遍历
     * @date 2022/10/14
     * @param index 传入的下标值
     **/
    public void postOrder(int index){
        if (array == null || array.length == 0){
            System.out.println("数组为空不能执行中序遍历");
            return;
        }
        //搜索左子节点的位置并向左执行递归遍历
        int left = 2*index+1;
        if (left < array.length){
            postOrder(left);
        }
        //搜索右子节点的位置并向右执行递归遍历
        int right = 2*index+2;
        if (right < array.length){
            postOrder(right);
        }
        //输出当前数
        System.out.println(array[index]);
    }
}
