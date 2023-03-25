/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/25 15:39
 * @description:
 */
public class Test {
    static {
        System.out.println("static");
    }
    {
        System.out.println("block");
    }

    public Test() {
        System.out.println("A");
    }

    public static void main(String[] args) {
        Test a = new Test();
    }
}
