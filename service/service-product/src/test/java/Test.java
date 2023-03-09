import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/8 12:44
 * @description:
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("李四");
        list.add("李五");
        list.add("李六");
        list.add("赵七");

//        for (int i = 0; i < list.size(); i++) {
//            if(list.get(i).charAt(0) == '李'){
//                list.remove(i);
//            }
//        }
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String next =  iterator.next();
//            if(next.charAt(0) == '李'){
//                iterator.remove();
//            }
//        }


        System.out.println("=========");

        list.forEach(System.out::println);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list1.stream().reduce(0, (x1, x2) -> x1 + x2));

        new Thread(() -> {
            
        }).start();

    }
}
