package hashtable;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 雇员类
 * @date 2022/10/12 22:19
 */
public class Employee {
    private int id;
    private String name;
    private Employee nextEmp;

    public Employee() {
    }

    public Employee(int id, String name, Employee nextEmp) {
        this.id = id;
        this.name = name;
        this.nextEmp = nextEmp;
    }

    public Employee getNextEmp() {
        return nextEmp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNextEmp(Employee nextEmp) {
        this.nextEmp = nextEmp;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '}' + "\t ==>";
    }
}
