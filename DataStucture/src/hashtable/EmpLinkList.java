package hashtable;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 员工类的链表，用于存储多个员工
 * @date 2022/10/12 22:25
 */
public class EmpLinkList {
    //相当于头指针，直接指向第一个emp对象，默认为null
    //可以理解为把第一个emp对象当作了头指针
    private Employee headEmp;

    /**
     * Description : 添加员工，默认添加到末尾
     * @date 2022/10/12
     * @param employee 待添加的员工信息
     **/
    public void addEmp(Employee employee){
        if (headEmp == null){ //说明head头指针后为空,直接添加
            headEmp = employee;
        }else {
            Employee temp = headEmp;//临时对象
            //head节点后有数据
            //从这个循环出来，代表temp当前nextEmp是为空的
            while (temp.getNextEmp() != null) {
                //解决哈希冲突
                if (temp.getId() == employee.getId()) {
                    break;
                }
                temp = temp.getNextEmp();
            }
            //解决哈希冲突
            if (temp.getId() == employee.getId()) {
                System.out.println("当前员工已存在，无法添加，请重试");
                return;
            }
            temp.setNextEmp(employee);
        }
    }

    /**
     * Description : 展示链表的数据
     * @date 2022/10/12
     **/
    public void showList(){
        if (headEmp == null){
            System.out.println("链表无数据");
        }else {
            Employee tempEmp = headEmp;
            do {
                System.out.print(tempEmp);
                tempEmp = tempEmp.getNextEmp();
            } while (tempEmp != null);
        }
    }

    /**
     * Description : 寻找某个指定id的员工
     * @date 2022/10/12
     **/
    public Employee get(int id){
        if (headEmp != null){
            boolean flag = false;
            Employee tempEmp = headEmp;
            while (true){
                if (tempEmp.getId() == id){
                    flag = true;
                    break;
                }
                tempEmp = tempEmp.getNextEmp();
            }
            if (flag){
                return tempEmp;
            }
        }
        return null;
    }

    /**
     * Description : 删除指定id的员工
     * 具体思路：
     * 1.先找到被删除的节点的上一个节点
     * 2.使用一个临时节点记录被删除节点的下一个节点
     * 3.让被删除的节点的上一个节点直接指向被删除节点的下一个节点(也就是临时节点记录的节点)
     * @date 2022/10/13
     * @param id 员工id
     **/
    public void del(int id){
        if (headEmp == null){
            System.out.println("链表无数据");
        }else {
            boolean flag = false;//用于判断是否进行删除操作
            Employee tempEmp = headEmp;//辅助节点

            while (true){
                //当删除的是头节点
                if (tempEmp.getId() == id){
                    flag = true;
                    //头节点后没有数据
                    if (headEmp.getNextEmp() == null){
                        headEmp = null;//将头节点清空
                    }else{ //头节点后有数据
                        headEmp = headEmp.getNextEmp();//将头节点指向头节点下一个节点
                    }
                    break;
                }
                //当删除的不是头节点，但也不是最后一个节点时，找到待删除的员工的上一个员工
                if (tempEmp.getNextEmp().getId() == id && tempEmp.getNextEmp() != null){
                    flag = true;
                    Employee delEmp = tempEmp.getNextEmp();//被删除的节点
                    //设置当前节点的下一个节点为被删除节点的下一个节点，让被删除节点架空，等jvm自动回收
                    tempEmp.setNextEmp(delEmp.getNextEmp());
                    break;
                }
                //当删除的不是头节点，但是最后一个节点时，找到待删除的员工的上一个员工
                if (tempEmp.getNextEmp().getId() == id && tempEmp.getNextEmp().getNextEmp() == null){
                    //直接让当前节点的下一个节点置为null，让需要被删除的节点等JVM自动回收
                    tempEmp.setNextEmp(null);
                }
                tempEmp = tempEmp.getNextEmp();
            }
            if (flag){
                System.out.println("删除成功");
            }else{
                System.out.printf("id是%d的员工不存在",id);
            }
        }
    }
}
