package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PHPTravelsUI {

    WebDriver driver;

    @FindBy(css = ".go-right:nth-child(2) > a")
    WebElement offersLink;

    @FindBy(css = ".nav:nth-child(1) strong")
    WebElement currentCurrencyValue;

    @FindBy(linkText = "USD")
    WebElement currentUSD;

    @FindBy(linkText = "ZAR")
    WebElement currentZAR;

    @FindBy(xpath = "//div[1][@class=\"col-md-6 owl-item go-right form-group\"]//b")
    WebElement firstOfferLink;

    public PHPTravelsUI(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOffers() {
        offersLink.click();
    }

    public void clickCurrentCurrencyValue() {
        currentCurrencyValue.click();
    }

    public void clickZARCurrencyLink() {
        currentZAR.click();
    }

    public String getFirstOfferValue() {
        return firstOfferLink.getText();
    }
}