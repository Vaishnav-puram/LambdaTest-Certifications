package tasks;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class task1 {
    @BeforeTest
    public void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupEdgeDriver() {
        WebDriverManager.edgedriver().setup();
    }
    @BeforeTest
    public void setupFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @DataProvider(name = "browsers")
    public Object[][] getBrowsers() {
        return new Object[][]{
                {new ChromeDriver(), "https://www.lambdatest.com/selenium-playground"},
                {new EdgeDriver(), "https://www.lambdatest.com/selenium-playground"},
                {new FirefoxDriver(),"https://www.lambdatest.com/selenium-playground"}
        };
    }
    @Test(dataProvider = "browsers")
    public  void sampleAutomation(WebDriver driver, String url  ) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("(//a[@class='text-black text-size-14 hover:text-lambda-900 leading-relaxed'])[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Simple Form Demo')]")).isDisplayed());
        driver.findElement(By.xpath("//input[@class='border border-gray-550 w-full h-35 rounded px-10']")).sendKeys("Welcome to LambdaTest");
        driver.findElement(By.id("showInput")).click();
        String actual=driver.findElement(By.xpath("//p[@id='message']")).getText();
        Assert.assertEquals(actual,"Welcome to LambdaTest");
        driver.quit();
    }
}
