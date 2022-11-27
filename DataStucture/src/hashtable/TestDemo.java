package hashtable;

import java.util.Scanner;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 测试类
 * @date 2022/10/12 23:43
 */
public class TestDemo {
    public static void main(String[] args) {
        //创建哈希表
        MyHashTable hashTable = new MyHashTable(7);
        while (true){
        Scanner scanner = new Scanner(System.in);
        System.out.println("================测试菜单================");
        System.out.println("1.add：添加员工");
        System.out.println("2.list：显示列表");
        System.out.println("3.get：查找指定id员工");
        System.out.println("4.del：删除指定id员工");
        System.out.println("9.exit：退出");
        System.out.print("请输入你的选择：");
            int choose = scanner.nextInt();
            if (choose == 9){
                break;
            }
            switch (choose){
                case 1:
                    Employee employee = new Employee();
                    System.out.print("请输入id：");
                    String id = scanner.next();
                    System.out.print("请输入名字：");
                    String name = scanner.next();
                    employee.setId(Integer.parseInt(id));
                    employee.setName(name);
                    hashTable.add(employee);
                    System.out.println("操作结束！");
                    break;
                case 2:
                    hashTable.showAllList();
                    break;
                case 3:
                    System.out.print("请输入id：");
                    String num = scanner.next();
                    Employee emp = hashTable.get(Integer.parseInt(num));
                    if (emp == null){
                        System.out.printf("id是%d在散列表中不存在\t",Integer.parseInt(num));
                    }else{
                        System.out.println(emp);
                    }
                    break;
                case 4:
                    System.out.print("请输入id：");
                    String delId = scanner.next();
                    hashTable.del(Integer.parseInt(delId));
                    break;
            }
        }

    }
}
