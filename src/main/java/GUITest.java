import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
        Thread.sleep(6000);
        webDriver.findElement(By.xpath("//*[@id=\"submit-button\"]"))
                .click();


        Contacts(webDriver, url);
        testMail(webDriver, url);
        Calendar(webDriver,url);
        Briefcase(webDriver, url, picture);
        Options(webDriver, url);

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
        Thread.sleep(2000);
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

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"zb__COMPOSE-1__SAVE_DRAFT_title\"]")).click();
        Thread.sleep(2000);
//        webDriver.findElement(By.xpath("//*[@id=\"YesNoCancel_button4_title\"]")).click();
//        Thread.sleep(2000);

        // 刷新页面
        webDriver.get(url);
        Thread.sleep(1000);
    }

    public static void Contacts(WebDriver webDriver, String url) throws InterruptedException {
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
                .sendKeys("test");
        Thread.sleep(2000);
        //输入筛选条件
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_conditions\"]/tbody/tr/td[4]/div/input"))
                .sendKeys("a");
        Thread.sleep(2000);
        //点击创建新的筛选条件
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_conditions\"]/tbody/tr/td[6]/table/tbody/tr/td[1]"))
                .click();
        Thread.sleep(2000);
        //输入新的筛选条件
        webDriver.findElement(By.xpath("//*[@id=\"ZmFilterRuleDialog_conditions\"]/tbody/tr[2]/td[4]/div/input"))
                .sendKeys("i");
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

    private static void Calendar(WebDriver webDriver, String url) throws InterruptedException {
        Thread.sleep(3000);
        //点击日历
        webDriver.findElement(By.xpath("//*[@id=\"zb__App__Calendar_title\"]"))
                .click();
        Thread.sleep(2000);
        //鼠标拖动来选定时间
        Actions action = new Actions(webDriver);
        action.moveToElement(webDriver.findElement(By.xpath("//*[@id=\"DWT89\"]"))).
                moveByOffset(0, 500).clickAndHold().moveByOffset(0, -200).release().perform();

        webDriver.findElement(By.xpath("//*[@id=\"DWT110\"]/input"))
                .sendKeys("看唱跳rap和打篮球");

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"DWT113\"]/input"))
                .sendKeys("bilibili");
        Thread.sleep(1000);
        //选定有空
        webDriver.findElement(By.xpath("//*[@id=\"DWT116_dropdown\"]/div"))
                .click();
        Thread.sleep(1000);
       action.moveToElement(webDriver.findElement(By.xpath("//*[@id=\"DWT116_title\"]"))).moveByOffset(0,20)
                .click().perform();

       //创建约会
        webDriver.findElement(By.xpath("//*[@id=\"DWT109_button2_title\"]"))
                .click();
        Thread.sleep(5000);
        //双击约会进入详细页面
        action.moveToElement(webDriver.findElement(By.xpath("//*[@id=\"DWT89\"]"))).moveByOffset(0,400).doubleClick().perform();

        //编辑详情
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"ZmHtmlEditor1_body\"]")).sendKeys("鸡你太美");
        //保存并关闭
        Thread.sleep(2000);
        action.moveToElement(webDriver.findElement(By.xpath("//*[@id=\"zb__APPT-1__SAVE_title\"]"))).click().perform();

        //删除刚刚创建的约会
        Thread.sleep(2000);
        action.sendKeys(Keys.BACK_SPACE).perform();
        Thread.sleep(2000);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(3000);

        webDriver.get(url);



//        Thread.sleep(1000);
//        action.moveToElement(webDriver.findElement(By.xpath("//*[@class=\"Row RowEven RowEven Row-selected\"]"))).doubleClick().perform();

    }
}
