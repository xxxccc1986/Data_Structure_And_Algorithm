package linkedlist;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 单向链表(带头节点)，不考虑插入节点的编号的情况下
 * @date 2022/6/4 16:50
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //创建节点
        TestNode node1 = new TestNode(1,"宋江","及时雨");
        TestNode node2 = new TestNode(2,"卢俊义","玉麒麟");
        TestNode node3 = new TestNode(3,"吴用","智多星");

        //创建单项链表
        SingleLinkedList list = new SingleLinkedList();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.showLinkedList();
    }
}

//创建一个单向链表保存TestNode的节点
class SingleLinkedList{

    //创建一个头节点，不存放数据，仅指向下一个节点(首元节点)的位置
    private TestNode head = new TestNode(0,null,null);

    /**
     * Description : 添加节点并插入到单项链表的末尾
     * @date 2022/6/4
     * @param node 新添加的节点
     * @return void
     **/
    public void add(TestNode node){
        //头节点不能动，创建一个临时指针遍历
        TestNode temp = head;
        //遍历链表
        while (true){
            //当某个节点的nextNode指向为null代表这个节点是链表的最后一个元素
            if (temp.nextNode == null) {
                break;
            }
            //没有找到则将temp指针向后移动
            else {
                temp = temp.nextNode;
            }
        }
        //退出while循环则代表找到了最后一个元素
        //将该元素的nextNode指向添加进来的元素
        temp.nextNode = node;
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
        TestNode temp = head;
        while (true){
            //判断头节点的指向的下个节点是否为空
            if (temp.nextNode != null){
                //不为空则打印这个节点的信息，并将temp指针后移
                System.out.println(temp.nextNode);
                temp = temp.nextNode;
            }else{
                //进入这个判断则代表头节点的下一个节点为空
                //没有任何数据，直接结束循环
                break;
            }
        }
    }

}

//每一个TestNode对象都是一个节点
class TestNode{
    public int num;
    public String name;
    public String nickname;
    public TestNode nextNode; //指向当前节点的下一个节点

    public TestNode() {
    }

    public TestNode(int num, String name, String nickname) {
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

