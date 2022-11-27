package linkedlist;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 双向链表
 * @date 2022/6/6 17:25
 */
public class DoubleSidedLinkedListDemo {
    public static void main(String[] args) {
        DoubleSidedLinkedList linkedList = new DoubleSidedLinkedList();
        Node node1 = new Node(1,"宋江","及时雨");
        Node node2 = new Node(2,"卢俊义","玉麒麟");
        Node node3 = new Node(3,"吴用","智多星");
        Node node4 = new Node(4,"林冲","豹子头");
        linkedList.add(node4);
        linkedList.add(node1);
        linkedList.add(node2);
        linkedList.add(node3);

//        linkedList.update(new Node(1,"小宋","及时雨"));
        //删除前
//        System.out.println("删除前");
//        linkedList.showLinkedList();

//        linkedList.delete(4);
//        System.out.println("删除后");
        linkedList.showLinkedList();

    }
}

//双向链表
class DoubleSidedLinkedList{
    public Node head = new Node(0,null,null);

    //遍历
    public void showLinkedList(){
        if (head.next == null){
            System.out.println("当前链表为空");
            return;
        }
        Node temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加(考虑编号，按顺序添加)
    public void add(Node node){
        if (head.next == null){//链表为空，直接添加
            head.next = node;
            node.pre = head;
            return;
        }
        Node temp = head.next;
        while (true){
            if (temp.num == node.num){
                System.out.println("当前添加的节点" + node + "在链表中已存在，添加失败");
                break;
            }
            int curNum = temp.num;//当前节点的位置
            int preNum = temp.pre.num;//当前节点的上一个节点的位置
            int nodeNum = node.num;//新加入节点的位置
            //当要添加的节点的位置比当前节点的上一个节点的位置大，
            //比当前节点的位置小
            //例如双向链表是 0 --> 1 --> 4, 新添加节点位置是3时
            if (preNum < nodeNum && nodeNum < curNum){
                temp.pre.next = node;//将当前节点前一个的下一个节点指向 新添加的节点
                node.next = temp;//将新添加的节点的下一个指向 当前节点
                node.pre = temp.pre;//将新添加的节点的上一个指向 当前节点的上一个
                temp.pre = node;//将当前节点的前一个指向 新添加的节点
                break;
            }
            //当temp后的节点为空直接添加
            // 例如双向链表是 0 --> 1, 新添加节点位置是2时
            if (temp.next == null){
                temp.next = node;
                node.pre = temp;
                break;
            }else{
                temp = temp.next;
            }
        }
    }

    //修改
    public void update(Node node){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = head.next;
        while (temp != null){
            if (temp.num == node.num){
                temp.name = node.name;
                temp.nickName = node.nickName;
                break;
            }else {
                temp = temp.next;
            }
        }
    }

    //删除
    public void delete(int nodeNum){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        Node temp = head.next;
        while (true){
            if (temp.num == nodeNum){
                //将删除节点的前一个节点的下一个节点指向被删除节点的下一个节点
                temp.pre.next = temp.next;
                //将被删除节点的后一个节点的pre节点指向被删除节点的前一个节点
                //当删除的nodeNum等于链表中最后一个节点，这一步没必要执行
                if (temp.next != null){
                    temp.next.pre = temp.pre;
                }
                break;
            }else{
                temp = temp.next;
                if (temp == null){//代表已经遍历到链表最后一个节点了
                    System.out.println("链表中不存在该节点，无法删除");
                    break;
                }
            }
        }
    }

}

//每一个node都是一个节点
class Node{
    public int num;
    public String name;
    public String nickName;
    public Node pre;//指向前一个节点
    public Node next;//指向下一个节点

    public Node() {
    }

    public Node(int num, String name, String nickName) {
        this.num = num;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Node{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + "'"
                + "}";
    }
}
