package tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class task2 {
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
    public  void sampleAutomation(WebDriver driver,String url) {
        driver.get(url);
        //maximize browser window
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]")).click();
        Actions actions=new Actions(driver);
        WebElement element=driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
        actions.dragAndDropBy(element,215,0).perform();
        Assert.assertEquals(driver.findElement(By.id("range")).getText(),"95");
        driver.quit();
    }
}
