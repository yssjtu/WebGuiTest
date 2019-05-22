import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GUITest {

    public static void main(String[] args) throws InterruptedException {
        Scanner in;
        String path;
        String username;
        String password;
        String picture;
        try {
            in = new Scanner(new File("config"));
            path = in.nextLine();
            username = in.nextLine();
            password = in.nextLine();
            picture = in.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //打开目标地址
        String url = "https://mail.sjtu.edu.cn";
        webDriver.get(url);

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"user\"]"))
                .sendKeys(username);
        webDriver.findElement(By.xpath("//*[@id=\"pass\"]"))
                .sendKeys(password);
        webDriver.findElement(By.xpath("//*[@id=\"captcha\"]"))
                .click();
        Thread.sleep(7000);
        webDriver.findElement(By.xpath("//*[@id=\"submit-button\"]"))
                .click();

        Briefcase(webDriver, url, picture);
        Options(webDriver, url);
        Contacts(webDriver,url);
        testMail(webDriver, url);

    }

    public static void testMail(WebDriver webDriver, String url) throws InterruptedException {
        // 刷新页面
        webDriver.get(url);
        Thread.sleep(1000);
        // 选择“邮箱”选项卡
        webDriver.findElement(By.xpath("//*[@id=\"zb__App__Mail_title\"]")).click();
        System.out.println("url:" + webDriver.getCurrentUrl());
        System.out.println("title:" + webDriver.getTitle());
        Thread.sleep(1000);
        // 查看不同类型的邮件
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Mail__5_textCell\"]")).click();//已发送
        Thread.sleep(800);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Mail__6_textCell\"]")).click();//草稿箱
        Thread.sleep(800);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Mail__4_textCell\"]")).click();//垃圾邮件
        Thread.sleep(800);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Mail__3_textCell\"]")).click();//已删除
        Thread.sleep(800);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Mail__2_textCell\"]")).click();//收件箱
        Thread.sleep(1000);
        // 多选勾选前三封邮件，并执行标记、取消标记操作
        webDriver.findElement(By.xpath("//*[@id=\"zl__TV-main__rows\"]/li[1]/div/div/div/div")).click();
        webDriver.findElement(By.xpath("//*[@id=\"zl__TV-main__rows\"]/li[2]/div/div/div/div")).click();
        webDriver.findElement(By.xpath("//*[@id=\"zl__TV-main__rows\"]/li[3]/div/div/div/div")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"zb__TV-main__ACTIONS_MENU\"]/table")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"DWT10\"]/tbody/tr[5]")).click();//标记
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"zb__TV-main__ACTIONS_MENU\"]/table")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"DWT10\"]/tbody/tr[6]")).click();//取消标记
        Thread.sleep(1000);
        //对邮件标记、设为未读
        webDriver.findElement(By.xpath("//*[@id=\"zl__TV-main__rows\"]/li[1]/div/div[2]/div[3]/div/div")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"zl__TV-main__rows\"]/li[1]/div/div/div[2]/div")).click();
        Thread.sleep(1000);
        //浏览前三封邮件
        webDriver.findElements(By.className("ZmRowDoubleHeader")).get(0).click();
        Thread.sleep(1000);
        webDriver.findElements(By.className("ZmRowDoubleHeader")).get(1).click();
        Thread.sleep(1000);
        webDriver.findElements(By.className("ZmRowDoubleHeader")).get(2).click();
        Thread.sleep(1000);
        //写信
        webDriver.findElement(By.xpath("//*[@id=\"zb__NEW_MENU_title\"]")).click();
        Thread.sleep(1000);
        WebElement to = webDriver.findElement(By.xpath("//*[@id=\"zv__COMPOSE-1_to_control\"]"));//收件人
        WebElement cc = webDriver.findElement(By.xpath("//*[@id=\"zv__COMPOSE-1_cc_control\"]"));//抄送
        WebElement subject = webDriver.findElement(By.xpath("//*[@id=\"zv__COMPOSE-1_subject_control\"]"));//主题
        WebElement content = webDriver.findElement(By.xpath("//*[@id=\"ZmHtmlEditor1_body\"]"));//内容
        //填写
        to.sendKeys("mytkeroro@sjtu.edu.cn");
        Thread.sleep(100);
        cc.sendKeys("");
        Thread.sleep(100);
        subject.sendKeys("将要被你玩弄的一封邮件");
        Thread.sleep(100);
        content.sendKeys("赶紧去测试 !!!");

        // 刷新页面
        webDriver.get(url);
        Thread.sleep(1000);
    }
}
