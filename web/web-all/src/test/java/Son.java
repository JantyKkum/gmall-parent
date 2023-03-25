/**
 * @author: Janty
 * @projectName: gmall-parent
 * @date: 2023/3/25 16:36
 * @description:
 */



public class Son extends Father{
    Son() {
        super("Father ");
        new Father("Son ");
    }
    public static void main(String[] args){
        new Son();
    }
}
