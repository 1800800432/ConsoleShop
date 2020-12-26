import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("请输入您的用户名");
        Scanner sc=new Scanner(System.in);
        String username=sc.next();
        System.out.println("您的用户名为" + username);

        System.out.println("请输入您的密码");
        String password=sc.next();
        System.out.println("您的密码为" + password);
    }
}
