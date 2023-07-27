package tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class task3 {
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
        driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]")).click();
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        driver.findElement(By.id("name")).sendKeys("Vaishnav Puram");
        driver.findElement(By.xpath("//input[@id='inputEmail4']")).sendKeys("puramv307@gmail.com");
        driver.findElement(By.xpath("//input[@id='inputPassword4']")).sendKeys("vaishv@123");
        driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Rakuten");
        driver.findElement(By.xpath("//input[@id='websitename']")).sendKeys("https://github.com/Vaishnav-puram");
        WebElement element=driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/select[1]"));
        Select select=new Select(element);
        select.selectByVisibleText("United States");
        driver.findElement(By.xpath("//input[@id='inputCity']")).sendKeys("Bangalore");
        driver.findElement(By.xpath("//input[@id='inputAddress1']")).sendKeys("h.no-103");
        driver.findElement(By.xpath("//input[@id='inputAddress2']")).sendKeys("bakers street");
        driver.findElement(By.xpath("//input[@id='inputState']")).sendKeys("Florida");
        driver.findElement(By.xpath("//input[@id='inputZip']")).sendKeys("32613");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
        WebDriverWait webDriverWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element1=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='success-msg hidden']")));
        Assert.assertEquals(element1.getText(),"Thanks for contacting us, we will get back to you shortly.");
        driver.quit();
    }
}
