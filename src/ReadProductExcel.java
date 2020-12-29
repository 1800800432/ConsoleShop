import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadProductExcel {
    /*数据测试*/
    public static void main(String[] args) throws ClassNotFoundException {
        InputStream in = Class.forName("Test").getResourceAsStream("/products.xlsx");
        Product[] products=new ReadProductExcel().readExcel(in);
        for(Product product:products){
            System.out.println(product.getProductid());
            System.out.println(product.getProductname());
            System.out.println(product.getProductdic());
            System.out.println(product.getProductpri());
        }
    }
    public Product[] readExcel(InputStream in) {
        Product products[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setProductid(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setProductname(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setProductdic(this.getValue(cell));//给address属性赋值
                    } else if (k == 3) {
                        product.setProductpri(this.getValue(cell));//给phone属性赋值
                    }
                }
                products[j-1] = product;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();

        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";//非字符串类型后加“”变成字符串类型
                break;
            case NUMERIC:
                DecimalFormat df=new DecimalFormat("#");
                value=df.format(cell.getNumericCellValue());
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}