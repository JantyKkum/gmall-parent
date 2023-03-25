import java.util.ArrayList;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/25 15:35
 * @description:
 */
public class MyTest2 {
    public static void main(String args[])
    {
        ArrayList obj = new ArrayList();
        obj.add("A");
        obj.add("B");
        obj.add("C");
        obj.add(2, "D");
        System.out.println(obj);
    }
}
