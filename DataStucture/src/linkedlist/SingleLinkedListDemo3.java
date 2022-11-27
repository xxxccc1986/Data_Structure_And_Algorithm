package linkedlist;

import java.util.Stack;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 链表的面试题
 * @date 2022/6/4 16:50
 */
public class SingleLinkedListDemo3 {
    public static void main(String[] args) {
        //进行测试
        //创建节点
        TestNode3 node1 = new TestNode3(1,"宋江","及时雨");
        TestNode3 node2 = new TestNode3(2,"卢俊义","玉麒麟");
        TestNode3 node3 = new TestNode3(3,"吴用","智多星");
        TestNode3 node4 = new TestNode3(4,"林冲","豹子头");

        //创建单项链表
        System.out.println("修改前的链表");
        SingleLinkedList3 list = new SingleLinkedList3();
        list.add(node1);
        list.add(node4);
        list.add(node3);
        list.add(node2);

        list.showLinkedList();

        //查询链表中的有效节点个数
//        System.out.println(list.countList());

        //查找倒数第1个节点
//        System.out.println(list.getAssignNode(1));

//        System.out.println("链表反转后");
//        SingleLinkedList3 reverseLinkedList = SingleLinkedListDemo3.reverseLinkedList2(list);
//        reverseLinkedList.showLinkedList();
//        SingleLinkedListDemo3.reverseLinkedList2(list.head);
//        list.showLinkedList();

        //链表逆序输出
        System.out.println("链表逆序输出：");
        SingleLinkedListDemo3.ReservePrintLinkedList(list);

    }

    /**
     * Description : 合并两个有序的单链表，合并之后的链表依然有序
     * @date 2022/6/6
     **/

    /**
     * Description : 百度面试题，逆序输出链表(不能破坏链表的结构)
     * @date 2022/6/6
     * @param reservedList 待逆序输出的链表
     * @return void
     **/
    public static void ReservePrintLinkedList(SingleLinkedList3 reservedList){
        if (reservedList.head.nextNode == null){
            System.out.println("链表为空");
            return;
        }
        //创建一个栈，stack
        Stack<TestNode3> stack = new Stack<>();

        //遍历链表取出每一个节点放入栈中
        //创建一个临时指针
        TestNode3 temp = reservedList.head.nextNode;
        while (temp != null){
            TestNode3 curNode = reservedList.getNode(temp.num);
            stack.add(curNode);
            temp = temp.nextNode;
        }
        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    /**
     * Description : 腾讯的面试题，链表的反转 就地反转解决
     * @date 2022/6/6
     * @param headNode 待反转链表的头节点
     * @return linkedlist.TestNode3
     **/
    public static TestNode3 reverseLinkedList2(TestNode3 headNode){
        //创建一个新的头节点
        TestNode3 newHead = new TestNode3(0,null,null);

        //链表中没有节点或者链表只有一个节点的情况
        if (headNode == null || headNode.nextNode == null){
            return headNode;
        }

        //创建临时变量分别保存前一个和后一个节点,以及一个临时指针遍历待反转的链表
        TestNode3 pre = null;
        TestNode3 next = null;
        TestNode3 temp = headNode.nextNode;

        while (temp != null){
            //分别保存当前节点的前一个和后一个节点
            next = temp.nextNode;
            pre = newHead.nextNode;
            //将新的头节点的下一个节点指向当前取出的节点
            newHead.nextNode = temp;
            //将当前节点的下一个节点指向原先新头节点指向的下一个节点
            temp.nextNode = pre;
            //将指针后移
            temp = next;
        }
        //将旧链表的下一个节点指向新的头节点的下一个节点
        return headNode.nextNode = newHead.nextNode;
    }

    /**
     * Description : 腾讯的面试题，链表的反转 头插法解决
     * @date 2022/6/5
     * @param linkedList 要被反转的链表
     * @return linkedlist.SingleLinkedList3
     **/
    public static SingleLinkedList3 reverseLinkedList(SingleLinkedList3 linkedList){

        SingleLinkedList3 singleLinkedList3 = new SingleLinkedList3();
        //创建一个新的头节点
        TestNode3 newHead = singleLinkedList3.head;
        //遍历单向链表
        //头节点head不能动，创建一个临时指针
        TestNode3 temp = linkedList.head.nextNode;
        //保存当前节点的下一个节点
        TestNode3 curNext = null;

        //链表中没有节点或者链表只有一个节点的情况
        if (temp == null || temp.nextNode == null){
            return linkedList;
        }
        //开始遍历
        while (temp != null){//temp = 1
                //记录当前节点的下一个节点
                curNext = temp.nextNode;//2
                //将新的头节点与待反转链表的节点逐个相连
                temp.nextNode = newHead.nextNode;//1.next--> null
                //将新的头节点指向就待反转链表的头节点下一个
                newHead.nextNode = temp;  // 0.next --> 1
                //指针后移
                temp = curNext; //temp = temp.next
            }
            return singleLinkedList3;
    }
}

//创建一个单向链表保存TestNode的节点
class SingleLinkedList3{

    //创建一个头节点，不存放数据，仅指向下一个节点(首元节点)的位置
    public  TestNode3 head = new TestNode3(0,null,null);

    /**
     * Description : 新浪面试题，查找单链表中的倒数第k个节点
     * @date 2022/6/5
     * @param index 链表中倒数的index的节点
     * @return linkedlist.TestNode3
     **/
    public TestNode3 getAssignNode(int index){
        TestNode3 temp = head;
        //判断链表是否为空
        if(temp.nextNode == null){
            return null;
        }
        //获取链表中的有效节点个数
        int nodes = countList();

        //获取指定的这个index节点
        //因为temp复制的是head的地址值，所以要+1
        int num = nodes - index + 1;
        //判断要获取的index是否超过有效节点的个数
        if (num > 0 && num <= nodes){
            return getNode(num);
        }else{
            return null;
        }
    }


    /**
     * Description : 查找指定的节点
     * @date 2022/6/5
     * @param num 指定节点的位置
     * @return linkedlist.TestNode3
     **/

    public TestNode3 getNode(int num){
        TestNode3 temp = head;
        if (temp.nextNode == null){
            return null;
        }
        while (true){
            if (temp.nextNode.num == num){
                return temp.nextNode;
            }else{
                temp = temp.nextNode;
            }
        }
    }

    /**
     * Description : 计算链表中有效节点的个数
     * @date 2022/6/5
     * @return void
     **/
    public int countList(){
        //不能对head头节点本身进行操作，创建一个临时变量
        TestNode3 temp = head;
        int count = 0;//记录链表中的有效节点
        while (true){
            //判断链表是否为空
            if (temp.nextNode == null){
                return count;
            }else{
                count += 1;//有效节点个数+1
                temp = temp.nextNode;//将指针移动到下一个节点，指针下移之前已经把当前这个有效节点统计了
            }
        }
    }

    /**
     * Description : 添加节点并插入到单项链表的末尾
     * @date 2022/6/4
     * @param node 新添加的节点
     * @return void
     **/
    public void add(TestNode3 node){
        //头节点不能动，创建一个临时指针遍历
        TestNode3 temp = head;
        //遍历链表
        while (true){
            //表示链表中没有任何元素
            if (temp.nextNode == null){
                //表示链表中没有数据，会直接添加新的元素
                temp.nextNode = node;
                node.nextNode = null;
                break;
            }
            //表示当前数据已存在
            if (temp.nextNode.num == node.num){
                System.out.println("当前添加的数据：" + node + ",已存在于链表中");
                break;
            }
            //找到元素插入的位置
            int next = temp.nextNode.num; //当前元素指向的下一个元素的位置
            int now = node.num;//新添加元素的位置
            if (now < next){
                //把新元素的下个节点的指向变成temp指向的下个节点
                node.nextNode = temp.nextNode;
                //把temp指向的下个节点变成新的元素
                temp.nextNode = node;
                break;
            }else{
                temp = temp.nextNode;
            }
        }
    }

    /**
     * Description : 显示单项链表的内容
     * @date 2022/6/4
     * @return void
     **/
    public void showLinkedList(){
        //首先先判断链表是否为空
        if (head.nextNode == null){
            System.out.println("当前链表为空");
            return;
        }
        //显示链表的数据
        //同理头节点不能动，创建一个临时节点
        TestNode3 temp = head;
        while (true){
            //判断头节点的指向的下个节点是否为空
            if (temp.nextNode != null){
                //不为空则打印这个节点的信息，并将temp指针后移
                System.out.println(temp.nextNode);
                temp = temp.nextNode;
            }else{
                break;
            }
        }
    }

}

//每一个TestNode对象都是一个节点
class TestNode3{
    public int num;
    public String name;
    public String nickname;
    public TestNode3 nextNode; //指向当前节点的下一个节点

    public TestNode3() {
    }

    public TestNode3(int num, String name, String nickname) {
        this.num = num;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "TestNode{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + "'"
                 + '}';
    }
}

