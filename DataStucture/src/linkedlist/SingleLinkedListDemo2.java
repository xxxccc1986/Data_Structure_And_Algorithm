package linkedlist;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 单向链表(带头节点)，考虑插入节点的编号的情况下
 * @date 2022/6/4 16:50
 */
public class SingleLinkedListDemo2 {
    public static void main(String[] args) {
        //进行测试
        //创建节点
        TestNode2 node1 = new TestNode2(1,"宋江","及时雨");
        TestNode2 node2 = new TestNode2(2,"卢俊义","玉麒麟");
        TestNode2 node3 = new TestNode2(3,"吴用","智多星");
        TestNode2 node4 = new TestNode2(4,"林冲","豹子头");

        //创建单项链表
        System.out.println("修改前的链表");
        SingleLinkedList2 list = new SingleLinkedList2();
//        list.add(node1);
//        list.add(node1);
//        list.add(node4);
//        list.add(node3);
//        list.add(node2);
//        list.showLinkedList();

//        //修改后
//        System.out.println("修改后的链表");
//        list.update(new TestNode(1,"小宋","及时雨"));
//        list.showLinkedList();

        //删除后
        System.out.println("删除后的链表");
//        list.delete(1);
//        list.delete(2);
//        list.delete(3);
//        list.delete(4);

//        list.showLinkedList();

        //查询链表中的有效节点个数
        list.countList();

    }
}

//创建一个单向链表保存TestNode的节点
class SingleLinkedList2{

    //创建一个头节点，不存放数据，仅指向下一个节点(首元节点)的位置
    private TestNode2 head = new TestNode2(0,null,null);

    /**
     * Description : 计算链表中有效节点的个数
     * @date 2022/6/5
     * @return void
     **/
    public int countList(){
        //不能对head头节点本身进行操作，创建一个临时变量
        TestNode2 temp = head;
        int count = 0;//记录链表中的有效节点
        while (true){
            //判断链表是否为空
            if (temp.nextNode == null){
                return count;
            }else{
                count += 1;//有效节点个数+1
                temp = temp.nextNode;//将指针移动到下一个节点，指针下移之前已经把当前这个有效节点统计了
                if (temp.nextNode == null){//判断是否是链表中的最后一个节点
                    return count;
                }
            }
        }
    }


    /**
     * Description : 删除链表中指定的元素
     * @date 2022/6/5
     * @param node 被删除的节点位置
     * @return void
     **/
    public void delete(int node){
        //同理，头节点不能动，创建一个临时指针
        TestNode2 temp = head;
        //遍历队列
        while (true){
            //判断链表是否为空
            if (temp.nextNode == null){
                System.out.println("链表为空");
                break;
            }
            //判断下一个节点是否为要删除的节点
            else if (temp.nextNode.num == node){
                //获取被删除的节点
                TestNode2 deletedNode = temp.nextNode;
                //获取被删除节点的下一个节点
                TestNode2 DeletedNextNode = deletedNode.nextNode;
                if (DeletedNextNode != null){
                    temp.nextNode = DeletedNextNode;
                    break;
                }else {//代表删除的是最后一个节点
                    System.out.println("当前删除的是链表中最后一个节点");
                    temp.nextNode = null;
                    break;
                }
            }
            //上述的判断都都不符合
            else{
                temp = temp.nextNode;
                //判断当前temp指针是否为链表中最后一个节点
                if (temp.nextNode == null){
                    System.out.println("指定删除的节点不存在");
                    break;
                }
            }
        }

    }


    /**
     * Description : 修改链表中指定元素的值
     * @date 2022/6/5
     * @param node 指定的元素
     * @return void
     **/
    public void update(TestNode node){
        //同理头节点不能动，创建一个临时指针
        TestNode2 temp = head;

        //遍历链表
        while (true){
            if (temp.nextNode == null){
                System.out.println("链表为空");
                break;
            }else if (temp.nextNode.num == node.num){
                temp.nextNode.name =node.name;
                temp.nextNode.nickname =node.nickname;
                break;
            }else{
                temp = temp.nextNode;
            }
        }
    }

    /**
     * Description : 添加节点并插入到单项链表的末尾
     * @date 2022/6/4
     * @param node 新添加的节点
     * @return void
     **/
    public void add(TestNode2 node){
        //头节点不能动，创建一个临时指针遍历
        TestNode2 temp = head;
        //遍历链表
        while (true){
            //表示链表中没有任何元素
            if (temp.nextNode == null){
                //表示链表中没有数据，会直接添加新的元素
                temp.nextNode = node;
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
        TestNode2 temp = head;
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
class TestNode2{
    public int num;
    public String name;
    public String nickname;
    public TestNode2 nextNode; //指向当前节点的下一个节点

    public TestNode2() {
    }

    public TestNode2(int num, String name, String nickname) {
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

