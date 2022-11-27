package linkedlist;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 单向环形链表解决约瑟夫问题
 * @date 2022/6/7 16:13
 */
public class SingleRingLinkedListDemo {
    public static void main(String[] args) {
        SingleRingLinkedList ringLinkedList = new SingleRingLinkedList();
        //创建单向环形链表
        ringLinkedList.add(5);

        //查看生成的环形链表
//        ringLinkedList.showRingLinkedList();

        //指定数字出圈
        ringLinkedList.JosephuQuestionResolve(1,2,5);

    }
}

class SingleRingLinkedList{
    //创建首元节点
    public RingNode head = new RingNode(1);
    //创建临时节点，辅助遍历链表
    public RingNode curTemp = head;

    //添加环形链表
    public void add(int num){
        //最少要求一个节点
        if (num < 1){
            System.out.println("输入的节点不正确");
            return;
        }
        for (int i = 2; i <= num; i++) {
            RingNode node = new RingNode(i);
            //当链表中只有首元节点的时候(不带头节点，头指针直接指向首元节点)
            //每次添加时curTemp = curTemp.nextNode都能保证curTemp指向当前链表最后一个节点
            if (head.nextNode == null){
                head.nextNode = node;
                node.nextNode = head;
                curTemp = curTemp.nextNode;
            }else{//当链表中不止一个节点时
                curTemp.nextNode = node;
                node.nextNode = head;
                curTemp = curTemp.nextNode;
            }
        }
    }
    /**
     * Description : 约瑟夫问题解决
     * @date 2022/6/7
     * @param startNum 开始数数的节点位置
     * @param countNum 要数的次数
     * @param LinkedListNum 链表节点的个数
     * @return void
     **/
    public void JosephuQuestionResolve(int startNum,int countNum,int LinkedListNum){
        //对链表进行校验
        if (head == null || startNum < 1 || startNum > LinkedListNum){
            System.out.println("链表中没有任何节点");
            return;
        }
        //确保head指针指向要求开始的位置
        //因为开始的位置也需要报数，因此只需移动 startNum - 1
        for (int i = 1; i <= startNum - 1 ; i++) {
            head = head.nextNode;
            curTemp = curTemp.nextNode;
        }
        //遍历报数出链表
        while(true) {
            //确保head指针指向要求出圈的位置
            //因为开始的位置也需要报数，因此只需移动 countNum - 1
            for (int i = 1; i <= countNum - 1; i++) {
                head = head.nextNode;
                curTemp = curTemp.nextNode;
            }
            //打印当前出圈节点信息
            System.out.println("编号：" + head.num + "的节点出圈，" + head);
            //将curTemp的下一个节点指向被删除节点的下一个
            curTemp.nextNode = head.nextNode;
            //确保head指向下次开始的位置
            head = head.nextNode;
            if (head == curTemp) {//此时代表单向环形链表中只有一个节点
                System.out.println("链表中最后一个节点出圈，编号：" + head.num + "的节点出圈，" + head);
                break;
            }
        }
    }

    //显示环形链表
    public void showRingLinkedList(){
        if (head.nextNode == null){
            System.out.println("链表为空");
            return;
        }
        //创建一个临时指针
        RingNode temp = head;
        while (true){
            //输出当前节点
            System.out.println(temp);
            //将指针下移
            temp = temp.nextNode;
            //当temp的位置和首元节点的位置相同时代表已经遍历过了链表一次
            if(temp == head){
                break;
            }
        }
    }
}

class RingNode{
    public int num;
    public RingNode nextNode;

    public RingNode() {
    }

    public RingNode(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "RingNode{" +
                "num=" + num +
                '}';
    }
}