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

//        Briefcase(webDriver, url, picture);
//        Options(webDriver, url);
        Contacts(webDriver,url);
    }



    public static void Contacts(WebDriver webDriver,String url)  throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"zb__App__Contacts_title\"]"))
                .click();
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Contacts__13_textCell\"]"))
                .click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Contacts__-18_textCell\"]"))
                .click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Contacts__3_textCell\"]"))
                .click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Contacts__7_textCell\"]"))
                .click();
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"zb__NEW_MENU_title\"]"))
                .click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"editcontactform_FIRST_input\"]"))
                .sendKeys("一辉");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"editcontactform_LAST_input\"]"))
                .sendKeys("顾");
        Thread.sleep(1000);
        List<WebElement> inputElements = webDriver.findElements(By.tagName("input"));
        inputElements.get(11).sendKeys("1227691678@qq.com");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"zb__CN-1__SAVE_title\"]"))
                .click();
        Thread.sleep(1000);

        List<WebElement> uncheckedBoxElements = (webDriver.findElements(By.className("DwtListView-Rows")).get(1)).findElements(By.className("ImgCheckboxUnchecked"));
        uncheckedBoxElements.get(0).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"zb__CNS-main__DELETE_title\"]"))
                .click();
        Thread.sleep(3000);
        webDriver.get(url);
        Thread.sleep(1000);
    }

}
