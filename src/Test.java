import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bool = true;
        while (bool) {
            System.out.println("请输入您的用户名");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("您的用户名为" + username);

            System.out.println("请输入您的密码");
            String password = sc.next();
            System.out.println("您的密码为" + password);
            //File file= new File("C:\\Users\\lenovo\\IdeaProjects\\ConsoleShop\\User.xls");
            InputStream inusers = Class.forName("Test").getResourceAsStream("/users.xls");
            InputStream inproduct = Class.forName("Test").getResourceAsStream("/products.xls");

            /*ReadProductExcel readProductExcel =new ReadProductExcel();
            Product[] products=readProductExcel.readExcel(inproduct);*/
            ReadUsersExcel readUsersExcel = new ReadUsersExcel();
            User[] users = readUsersExcel.readExcel(inusers);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword())) {
                    System.out.println("登陆成功");
                    bool = false;
                    break;
                } else {
                    System.out.println("登陆失败");
                    break;
                }
            }
        }
    }

}
