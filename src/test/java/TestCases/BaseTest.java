package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver basedriver;  //
    private Object driver;
    FluentWait wait = new FluentWait(driver);
    @BeforeClass
    public void setup() {
        System.setProperty("web driver.chrome.driver", "path/to/chromedriver");
        basedriver = new ChromeDriver();
        basedriver.manage().window().maximize();
        basedriver.get("https://www.saucedemo.com/");
    }

    @AfterClass
    public void teardown() {
        if (basedriver != null) {
            basedriver.quit();
        }
    }
}