import PageFactory.CurrenciesPage;
import PageFactory.PHPTravelsUI;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assume.*;

public class Currencies_CRUD_test {
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
        //driver.quit();
    }

    @Test
    public void Currencies_CRUD_test() throws Exception {
        objCurrencies = new CurrenciesPage(driver);
        objOffers = new PHPTravelsUI(driver);
        String s = RandomStringUtils.randomAlphanumeric(3);
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

        //Edit Currency
        objCurrencies.clickEditCurrencyBtn();
        objCurrencies.setCurrencyName("USD1");
        objCurrencies.setCurrencySymbol("$1");
        objCurrencies.clearCurrencyCode();
        objCurrencies.setCurrencyCode(s);
        objCurrencies.clearCurrencyRate();
        objCurrencies.setCurrencyRate(rate);
        objCurrencies.currencyStateViaDropDown(0);
        objCurrencies.clickSaveAndReturnButton();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        objCurrencies.clickSearchButton();
        objCurrencies.setSearchTextArea(s);
        objCurrencies.clickSearchGoButton();
        //verify element exists
        driver.findElement(By.xpath("//*[text()='" + s + "']"));

        objCurrencies.clickResetBtn();

        //Remove currency
        objCurrencies.clickRemoveCurrencyBtn();
        assumeThat(driver.switchTo().alert().getText(), is("Do you really want remove this entry?"));
        driver.switchTo().alert().accept();
        objCurrencies.clickSearchButton();
        objCurrencies.setSearchTextArea("USD");
        objCurrencies.clickSearchGoButton();
        assumeThat(driver.findElement(By.cssSelector("td")).getText(), is("Entries not found."));
    }
}
