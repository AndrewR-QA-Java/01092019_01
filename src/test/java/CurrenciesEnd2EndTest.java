import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageFactory.CurrenciesPage;
import PageFactory.PHPTravelsUI;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class CurrenciesEnd2EndTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    CurrenciesPage objCurrencies;
    PHPTravelsUI objOffers;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\andre\\IdeaProjects\\ar_Sel_Test01\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void CurrenciesEnd2EndTest() throws Exception {
        objCurrencies = new CurrenciesPage(driver);
        objOffers = new PHPTravelsUI(driver);
        String s = RandomStringUtils.randomAlphabetic(10);
        BigDecimal rate = new BigDecimal(2.0);
        driver.get("https://www.phptravels.net/admin");
        driver.manage().window().maximize();
        driver.findElement(By.name("email")).sendKeys("admin@phptravels.com");
        driver.findElement(By.name("password")).sendKeys("demoadmin");
        driver.findElement(By.cssSelector(".btn-block")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[text()=' General']")).click();
        driver.findElement(By.cssSelector("#menu-ui > li:nth-child(2) > a")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        objCurrencies.clickAddButton();
        objCurrencies.setCurrencyName("ZAR");
        objCurrencies.setCurrencySymbol("Z");
        objCurrencies.setCurrencyCode(s);
        objCurrencies.clearCurrencyRate();
        objCurrencies.setCurrencyRate(rate);
        objCurrencies.currencyStateViaDropDown(0);
        objCurrencies.clickSaveAndReturnButton();
        objCurrencies.clickSearchButton();
        objCurrencies.setSearchTextArea(s);
        objCurrencies.clickSearchGoButton();
        //verify element exists
        driver.findElement(By.xpath("//*[text()='" + s + "']"));

        driver.get("https://www.phptravels.net/");
        driver.manage().window().maximize();
        objOffers.clickOffers();
        String s1 = objOffers.getFirstOfferValue().substring(5).replace(",", "");
        BigDecimal actualValueUSD = BigDecimal.valueOf(Double.parseDouble(s1));
        BigDecimal expectedValueZAR = actualValueUSD.multiply(rate);

        objOffers.clickCurrentCurrencyValue();
        objOffers.clickZARCurrencyLink();
        objOffers.clickOffers();
        String s2 = objOffers.getFirstOfferValue().substring(12).replace(",", "");
        BigDecimal actualValueZAR = BigDecimal.valueOf(Double.parseDouble(s2));

        int result = actualValueZAR.compareTo(expectedValueZAR);
        if (result != 0) System.out.println("ERROR: bad rate calculation detected");
    }
}
