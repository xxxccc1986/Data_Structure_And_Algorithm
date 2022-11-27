package tree.binarysorttree;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 二叉排序树节点
 * @date 2022/10/21 23:18
 */
public class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * Description : 递归添加节点(或理解为递归创建排序二叉树)
     * @date 2022/10/21
     * @param node 待添加的节点
     **/
    public void add(Node node){
        if (node == null){
            System.out.println("当前节点为空，添加失败");
            return;
        }
        /*
        判断与左右子节点的关系,有两种情况：
        情况1：待加入的节点比当前节点要小，且当前的节点的左节点下有数据
        情况2：待加入的节点比当前节点要大，且当前的节点的右节点下有数据
        情况3：待加入节点和当前节点相同，且当前左节点为空或当前的右节点为空
         */
        if (node.value < this.value){//表示待添加节点小于当前节点
            if (this.left != null){
                this.left.add(node);
            }else{
                this.left = node;
            }
        }else {//表示待添加节点大于或等于当前节点
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
    }

    /**
     * Description : 中序遍历 左节点 => 中间节点 => 右节点
     * @date 2022/10/22
     **/
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
     * Description : 删除对应的目标节点
     * 得先判断当前待删除的节点是否存在，再得考虑这三种情况：
     * 1.当前待删除的节点为叶子节点
     * 叶子节点为父节点的左子节点 或 叶子节点为父节点的右子节点
     * 2.当前待删除的节点只有一个子树(只有左子树或右子树)
     * 待删除节点是父节点的左节点，待删除节点有个左节点
     * 待删除节点是父节点的左节点，待删除节点有个右节点
     * 待删除节点是父节点的右节点，待删除节点有个左节点
     * 待删除节点是父节点的右节点，待删除节点有个右节点
     * 3.当前待删除的节点有两个子树
     * 待删除节点是父节点的左节点(则需要找到父节点的右子树的最小值，因为右子树的最小值比左子树的所有值都要大)
     * 待删除节点是父节点的右节点(则需要找到待删除节点的左子树的最大值，
     * 因为左子树的最大值比右子树的所有值都要小，比左子树的其他值都要大)
     * @date 2022/10/22
     * @param targetNodeVal 目标节点的值
     **/
    public void delete(int targetNodeVal){
        Node targetNode = searchTargetNode(targetNodeVal);//目标节点
        //先判断目标节点是否存在
        if (targetNode == null){
            System.out.println("目标节点不存在，无法进行删除");
        }else{
            Node targetNodeParent = searchTargetNodeParent(targetNode);//目标节点的父节点
            //再判断目标节点的父节点是否存在
            if (targetNodeParent == null){ //代表要删除的是根节点
                //代表要删除的根节点下有节点，因为在程序的入口已经判读过只剩下root根节点且无左右子节点的情况了
                if (targetNode.left != null && targetNode.right == null){
                    targetNode.value = findMaxInLeftSonTree(targetNode);
                }else{
                    targetNode.value = findMinInRightSonTree(targetNode);
                }
            }else{//父节点存在
                Node leftNode = targetNodeParent.left;
                Node rightNode = targetNodeParent.right;

                //第一种情况：删除的为叶子节点
                if (targetNode.left == null && targetNode.right == null){
                    if (leftNode != null && leftNode.value == targetNodeVal ){
                        targetNodeParent.left = null;
                    }else if (rightNode != null && rightNode.value == targetNodeVal ){
                        targetNodeParent.right = null;
                    }
                }else {
                    //第三种情况：如果同时不满足这个条件就代表符合下面的第二种情况的某个条件，
                    //相当于这个if的范围更小因此需要放在更前面
                    if (leftNode != null && rightNode != null){
                        targetNode.value = findMinInRightSonTree(targetNode);
                    }

                    //第二种情况：
                    if (leftNode != null && leftNode.left != null && leftNode.value == targetNodeVal){
                        targetNodeParent.left = leftNode.left;
                    }else if (leftNode != null && leftNode.right != null && leftNode.value == targetNodeVal){
                        targetNodeParent.left = leftNode.right;
                    }else if (rightNode != null &&  rightNode.left != null && rightNode.value == targetNodeVal){
                        targetNodeParent.left = rightNode.left;
                    }else if (rightNode != null &&  rightNode.right != null && rightNode.value == targetNodeVal){
                        targetNodeParent.left = rightNode.right;
                    }
                }
            }
        }
    }

    /**
     * Description : 查找被删除的目标节点
     * @date 2022/10/22
     * @param targetNodeVal 目标节点的值
     **/
    public Node searchTargetNode(int targetNodeVal){
        //先判断当前的节点是否是要找的节点
        if (targetNodeVal == this.value){
            return this;
        }
        //判断左节点是否有数据且查找的节点比当前节点小(排序二叉树的左子树都比当前节点要小)
        if (this.left != null && targetNodeVal < this.value){
            return this.left.searchTargetNode(targetNodeVal);
        }
        //判断右节点是否有数据且查找的节点比当前节点小(排序二叉树的左子树都比当前节点要小)
        if (this.right != null && targetNodeVal > this.value){
            return this.right.searchTargetNode(targetNodeVal);
        }
        return null;
    }

    /**
     * Description : 查找指定节点的父节点
     * @date 2022/10/22
     * @param node 待查找父节点的节点
     **/
    public Node searchTargetNodeParent(Node node){
        Node leftNode = this.left;
        Node rightNode = this.right;

        if (leftNode != null){
            if (node.value == leftNode.value){
                return this;
            }else if (node.value < this.value){
                return leftNode.searchTargetNodeParent(node);
            }
        }
        if (rightNode != null){
            if (node.value == rightNode.value){
                return this;
            }else if (node.value > this.value){
                return rightNode.searchTargetNodeParent(node);
            }
        }
        return null;
    }


    /**
     * Description : 在待删除节点的右子树中查找最小值
     * 思路解析：
     * 1.传入当前的待删除节点，假定当前待删除的节点就是最小值
     * 2.先获取待删除节点的右子节点，判断一下此右子节点是否还有它自己的左子树
     * 因为根据排序二叉树的性质，节点的左边数比它小，节点右边的数比它大，
     * 如果此右子节点还有它自己的左子树，也就代表着最小值在其左子树中
     * 当然还需要判断这个左子树是否还有左子树，因此需要使用循环判断
     * 但如果此右子节点已经没有自己的左子树，也就意味着它自己就是最小的了
     * 最后还需删除被筛选出的最小值节点的父类对它的引用，也就是还需要一个指针辅助删除
     * @date 2022/10/23
     * @param node 传入的待删除节点
     **/
    public int findMinInRightSonTree(Node node){
        int min = node.value;//假定当前的待删除节点为最小值

        Node rightNode = node.right;//当前节点的右子节点
        if (rightNode != null){ //判断右子节点是否为空
            if (rightNode.left == null){ //为空代表此右节点的值为最小的
                min = rightNode.value;
                //删除最小值节点的父类对其的引用
                node.right = null;
            }else{ //表示最小值还在右节点的左子树下
                //临时指针，用于遍历，指向右节点的左子节点
                Node tempLeft = rightNode.left;
                while (true){
                    //先将当前节点的值赋给最小值
                    min = tempLeft.value;
                    if (tempLeft.left == null){//代表此时tempLeft以是最小值所在的节点
                        break;
                    }
                    //指针全部下移
                    rightNode = rightNode.left;
                    tempLeft = tempLeft.left;
                }
                //删除最小值节点的父类对其的引用
                rightNode.left = null;
            }
        }
        return min;
    }

    /**
     * Description : 在待删除节点的左子树中查找最大值
     * 思路解析：
     * 1.传入当前的待删除节点，假定当前待删除的节点就是最大值
     * 2.先获取待删除节点的左子节点，判断一下此左子节点是否还有它自己的右子树
     * 因为根据排序二叉树的性质，节点的左边数比它小，节点右边的数比它大，
     * 如果此左子节点还有它自己的右子树，也就代表着最大值在这个右子树里面
     * 当然还需要判断这个右子树是否还有右子树，因此需要使用循环判断
     * 但如果此左子节点已经没有自己的右子树，也就意味着它自己就是最大的了
     * 最后还需删除被筛选出的最大值节点的父类对它的引用，也就是还需要一个指针辅助删除
     * @date 2022/10/23
     * @param node 传入的待删除节点
     *
     **/
    public int findMaxInLeftSonTree(Node node){
        //这下面的代码思路与下面的哪个查找待删除节点的右子树的最小值类似
        int max = node.value;

        Node leftNode = node.left;
        if (leftNode != null){
            if (leftNode.right == null){
                max = leftNode.value;
                node.left = null;
            }else{
                Node tempRight = leftNode.right;
                while (true){
                    max = tempRight.value;
                    if (tempRight.right == null){
                        break;
                    }
                    leftNode = leftNode.right;
                    tempRight = tempRight.right;
                }
                leftNode.right = null;
            }
        }
        return max;
    }
}
