package hashtable;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 哈希表，通过链表数组管理多个员工类链表
 * @date 2022/10/12 23:27
 */
public class MyHashTable {
    private static EmpLinkList[] empLinkListArray;
    private static int arrSize;

    public MyHashTable(int size) {
        arrSize = size;
        empLinkListArray = new EmpLinkList[size];//初始化链表数组
        //由于创建的是对象数组，默认对象都是null，则必须对数组的各个元素进行初始化
        for (int i = 0; i < empLinkListArray.length; i++) {
            empLinkListArray[i] = new EmpLinkList();
        }
    }

    /**
     * Description : 确认id所在数组并添加员工
     * @date 2022/10/12
     * @param employee 待添加的员工信息
     **/
    public void add(Employee employee){
        //确认所在位置
        int i = hashMethod(employee.getId());
        //获取数组对应的链表并添加员工
        EmpLinkList empLinkList = empLinkListArray[i];
        empLinkList.addEmp(employee);
    }

    public void showAllList(){
        for (EmpLinkList empLinkList : empLinkListArray) {
            empLinkList.showList();
        }
    }

    /**
     * Description : 获取对应id的员工信息
     * @date 2022/10/12
     * @param num 对应的id的员工
     **/
    public Employee get(int num){
        int i = hashMethod(num);
        EmpLinkList empLinkList = empLinkListArray[i];
        return empLinkList.get(num);
    }

    /**
     * Description : 删除对应id的员工信息
     * @date 2022/10/13
     * @param num 待删除的员工id
     **/
    public void del(int num){
        int i = hashMethod(num);
        EmpLinkList empLinkList = empLinkListArray[i];
        empLinkList.del(num);
    }


    /**
     * Description : 散列函数，确认当前id应该放在
     * @date 2022/10/12
     * @param id 当前员工id
     **/
    public int hashMethod(int id){
        return id % arrSize;
    }
}
