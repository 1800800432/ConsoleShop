import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bool = true;
        while (bool) {
            System.out.println("请输入您的用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("您的用户名为：" + username);

            System.out.println("请输入您的密码：");
            String password = sc.next();
            System.out.println("您的密码为：" + password);
            //File file= new File("C:\\Users\\lenovo\\IdeaProjects\\ConsoleShop\\User.xls");
            InputStream inusers = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inproduct = Class.forName("Test").getResourceAsStream("/products.xlsx");


            ReadUsersExcel readUsersExcel = new ReadUsersExcel();
            User[] users = readUsersExcel.readExcel(inusers);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername().trim()) && password.equals(users[i].getPassword())) {
                    System.out.println("登陆成功！");
                    /*读取商品信息*/
                    ReadProductExcel readProductExcel =new ReadProductExcel();
                    Product[] products=readProductExcel.getAllproducts(inproduct);

                    for(Product product:products){
                        System.out.print("\t"+product.getProductid());
                        System.out.print("\t"+product.getProductname());
                        System.out.print("\t\t"+product.getProductdic());
                        System.out.println("\t\t"+product.getProductpri());
                    }
                    /*根据id输出商品信息*/
                    int count=0;
                    Product product2[]=new Product[3];
                    System.out.println("请输入商品id：");
                    String pId = sc.next();
                    ReadProductExcel readProductExcel1 =new ReadProductExcel();
                    inproduct = null;
                    inproduct = Class.forName("Test").getResourceAsStream("/products.xlsx");
                    Product product = readProductExcel1.getproductById(pId,inproduct);


                    /*一次购物后的操作，查看购物车或继续购物*/
                    boolean bool1=true;
                    while (bool1) {
                        if (product != null) {
                            product2[count++] = product;
                            System.out.println("已找到商品，并加入购物车！");
                            System.out.println("请输入选项：");
                            System.out.println("输入1，查看购物车，输入2，继续购物");
                            int cho = sc.nextInt();
                            if (cho == 1) {
                                System.out.print("\t" + product2[count++]);
                                System.out.print("\t" + product2[count]);
                                System.out.print("\t" + product2[count]);
                                System.out.println("\t" + product2[count]);
                                bool1=false;
                            } else if (cho == 2) {
                                ReadProductExcel readProductExcel2 = new ReadProductExcel();
                                inproduct = null;
                                inproduct = Class.forName("Test").getResourceAsStream("/products.xlsx");
                                Product[] productes = readProductExcel2.getAllproducts(inproduct);
                                for (Product product1 : productes) {
                                    System.out.print("\t" + product1.getProductid());
                                    System.out.print("\t" + product1.getProductname());
                                    System.out.print("\t\t" + product1.getProductdic());
                                    System.out.println("\t\t" + product1.getProductpri());
                                    /*二次购买*/
                                    System.out.println("请输入商品id：");
                                    String pId1 = sc.next();
                                    ReadProductExcel readProductExcel3 = new ReadProductExcel();
                                    inproduct = null;
                                    inproduct = Class.forName("Test").getResourceAsStream("/products.xlsx");
                                    product = readProductExcel3.getproductById(pId1, inproduct);
                                }
                            }
                        }
                    }
                    bool = false;
                    break;
                } else {
                    System.out.println("登陆失败！请重新登录：");
                    break;
                }
            }
        }
    }

}
