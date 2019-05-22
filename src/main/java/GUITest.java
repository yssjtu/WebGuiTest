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

    private static void Briefcase(WebDriver webDriver, String url, String picture) throws InterruptedException {
        Thread.sleep(3000);
        //点击公文包
        webDriver.findElement(By.xpath("//*[@id=\"zb__App__Briefcase_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击上传文件
        webDriver.findElement(By.xpath("//*[@id=\"zb__BDLV-main__NEW_FILE_title\"]"))
                .click();
        Thread.sleep(2000);
        //上传图片
        webDriver.findElement(By.name("uploadFile"))
                .sendKeys(picture);
        Thread.sleep(2000);
        //输入备注
        webDriver.findElement(By.tagName("textarea"))
                .sendKeys("test jpg");
        Thread.sleep(2000);
        //点击确定
        List<WebElement> webElements = webDriver.findElement(By.className("DwtDialogButtonBar"))
                .findElements(By.className("ZWidgetTitle"));
        webElements.get(0).click();
        Thread.sleep(5000);
        //点击选中第一个文件
        webDriver.findElement(By.xpath("//*[@id=\"zlhi__BDLV-main__se\"]"))
                .click();
        Thread.sleep(2000);
        //点击删除
        webDriver.findElement(By.xpath("//*[@id=\"zb__BDLV-main__DELETE_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击确定
        webDriver.findElement(By.xpath("//*[@id=\"CONFIRM_DIALOG_button5_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击已删除邮件
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Briefcase__3_textCell\"]"))
                .click();
        Thread.sleep(2000);
        //
        webDriver.findElement(By.xpath("//*[@id=\"zlhi__BDLV-main__se\"]"))
                .click();
        Thread.sleep(2000);
        //点击永久删除
        webDriver.findElement(By.xpath("//*[@id=\"zb__BDLV-main__DELETE_title\"]"))
                .click();
        Thread.sleep(1000);
        //点击确定
        webDriver.findElement(By.xpath("//*[@id=\"CONFIRM_DIALOG_button5_title\"]"))
                .click();
        Thread.sleep(2000);
        webDriver.get(url);
    }

    private static void Options(WebDriver webDriver, String url) throws InterruptedException {
        Thread.sleep(3000);
        //点击首选项
        webDriver.findElement(By.xpath("//*[@id=\"zb__App__Options_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击过滤器
        webDriver.findElement(By.xpath("//*[@id=\"zti__main_Options__PREF_PAGE_FILTERS_textCell\"]"))
                .click();
        Thread.sleep(2000);
        //点击创建过滤器
        webDriver.findElement(By.xpath("//*[@id=\"zb__FRV__ADD_FILTER_RULE_title\"]"))
                .click();
        Thread.sleep(2000);
        //输入过滤器名称
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_name\"]"))
                .sendKeys("律师函");
        Thread.sleep(2000);
        //输入筛选条件
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_conditions\"]/tbody/tr/td[4]/div/input"))
                .sendKeys("cxk");
        Thread.sleep(2000);
        //点击创建新的筛选条件
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_conditions\"]/tbody/tr/td[6]/table/tbody/tr/td[1]"))
                .click();
        Thread.sleep(2000);
        //输入新的筛选条件
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_conditions\"]/tbody/tr[2]/td[4]/div/input"))
                .sendKeys("ikun");
        Thread.sleep(2000);
        //点击确定创建过滤器
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_button2_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击移除过滤器
        webDriver.findElement(By.xpath("//*[@id=\"DwtChooserRemoveButton_1_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击添加可用过滤器中的待选过滤器
        webDriver.findElement(By.xpath("//*[@id=\"DwtChooserButton_1_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击运行过滤器
        webDriver.findElement(By.xpath("//*[@id=\"zb__FRV__RUN_FILTER_RULE_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击添加第一个筛选选项
        webDriver.findElement(By.xpath("//*[@id=\"zti__ZmFilterRulesController_incoming__2_checkbox\"]"))
                .click();
        Thread.sleep(2000);
        //点击添加第二个筛选选项
        webDriver.findElement(By.xpath("//*[@id=\"zti__ZmFilterRulesController_incoming__5_checkbox\"]"))
                .click();
        Thread.sleep(2000);
        //点击确定开始运行过滤器
        webDriver.findElement(By.xpath("//*[@id=\"ChooseFolderDialog_button2_title\"]"))
                .click();
        Thread.sleep(5000);
        //点击确定完成过滤
        webDriver.findElement(By.xpath("//*[@id=\"ZmMsgDialog_button2_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击删除过滤器
        webDriver.findElement(By.xpath("//*[@id=\"zb__FRV__REMOVE_FILTER_RULE_title\"]"))
                .click();
        Thread.sleep(2000);
        //点击确定删除过滤器
        webDriver.findElement(By.xpath("//*[@id=\"YesNoMsgDialog_button5_title\"]"))
                .click();
        Thread.sleep(2000);
        webDriver.get(url);
    }
}
