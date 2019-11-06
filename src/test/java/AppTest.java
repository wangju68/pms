import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AppTest {
    @Test
    public void testMethod(){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("id");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue("姓名");
        Cell cell3 = row.createCell(2);
        cell3.setCellValue("日期");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\Desktop\\a.xlsx"));
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*@Test
    public  void test01() throws  Exception{


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        helper.setFrom("wj2424588930@163.com");
        helper.setTo("twz1781748566@163.com");
        helper.setSubject("这是0708班的邮件测试");
        helper.setText("<html><head></head><body>嘿嘿嘿 <img src=cid:identifie>  <span style='color:red'>嘿嘿</span>嘿嘿，阿哦哦<h1>阿哦</h1>好奥</body></html>",true);

        //作为附件下载
        FileSystemResource file = new FileSystemResource(new File("d:\\Desktop\\beautiful.jpg"));
        helper.addAttachment("CoolImage.jpg", file);

        //图片作为内置资源
        FileSystemResource file1 = new FileSystemResource(new File("d:\\Desktop\\baoguo.jpg"));
        helper.addInline("identifie",file1);
        //发送邮件
        bean.send(mimeMessage);
        System.out.println("EMAIL PASS");
    }*/
}
