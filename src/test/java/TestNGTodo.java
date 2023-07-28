import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class TestNGTodo {
    public String username = "puramv307";
    public String accesskey = "uaZhepgxAPMK3oNaueXIjee7K1GkEhUnRHlQHtUhy8248mN3G4";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @BeforeClass
    public void setUp() throws Exception {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("114.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "puramv307");
        ltOptions.put("accessKey", "uaZhepgxAPMK3oNaueXIjee7K1GkEhUnRHlQHtUhy8248mN3G4");
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("project", "Untitled");
        ltOptions.put("tunnel", true);
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
    }

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
                {new FirefoxDriver(), "https://www.lambdatest.com/selenium-playground"}
        };
    }

    @Test(dataProvider = "browsers")
    public void sampleAutomation(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("(//a[@class='text-black text-size-14 hover:text-lambda-900 leading-relaxed'])[1]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Simple Form Demo')]")).isDisplayed());
        driver.findElement(By.xpath("//input[@class='border border-gray-550 w-full h-35 rounded px-10']")).sendKeys("Welcome to LambdaTest");
        driver.findElement(By.id("showInput")).click();
        String actual = driver.findElement(By.xpath("//p[@id='message']")).getText();
        Assert.assertEquals(actual, "Welcome to LambdaTest");
        driver.quit();
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}
