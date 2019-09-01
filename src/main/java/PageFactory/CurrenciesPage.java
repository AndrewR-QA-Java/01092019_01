package PageFactory;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.math.BigDecimal;

public class CurrenciesPage {

    WebDriver driver;

    @FindBy(css = ".xcrud-top-actions > .btn-success")
    WebElement crudAddButton;

    @FindBy(name = "cHRfY3VycmVuY2llcy5uYW1l")
    WebElement currencyName;

    @FindBy(name = "cHRfY3VycmVuY2llcy5zeW1ib2w-")
    WebElement currencySymbol;

    @FindBy(name = "cHRfY3VycmVuY2llcy5jb2Rl")
    WebElement currencyCode;

    @FindBy(name = "cHRfY3VycmVuY2llcy5yYXRl")
    WebElement currencyRate;

    @FindBy(name = "cHRfY3VycmVuY2llcy5pc19hY3RpdmU-")
    WebElement currencyState;

    @FindBy(css = ".xcrud-action:nth-child(1)")
    WebElement saveAndReturnButton;

    @FindBy(css = ".xcrud-search-toggle")
    WebElement searchButton;

    @FindBy(name = "phrase")
    WebElement searchTextArea;

    @FindBy(css = ".btn-group > .btn-primary")
    WebElement searchGoButton;

    @FindBy(css = ".xcrud-row:nth-child(1) .xcrud-action:nth-child(2) > .fa")
    WebElement editCurrencyBtn;

    @FindBy(css = ".xcrud-row:nth-child(1) .xcrud-action:nth-child(3) > .fa")
    WebElement removeCurrencyBtn;

    @FindBy(xpath = "//a[text()=\"Reset\"]")
    WebElement resetBtn;

    public CurrenciesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAddButton() {
        crudAddButton.click();
    }

    public void clickEditCurrencyBtn() {
        editCurrencyBtn.click();
    }

    public void clickRemoveCurrencyBtn() {
        removeCurrencyBtn.click();
    }

    public void clickResetBtn() {
        resetBtn.click();
    }

    public void clickSaveAndReturnButton() {
        saveAndReturnButton.click();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clearCurrencyRate() {
        currencyRate.clear();
    }

    public void clearCurrencyCode() {
        currencyCode.clear();
    }

    public void clickSearchGoButton() {
        searchGoButton.click();
    }

    public void currencyStateViaDropDown(int index) {
        Select drop = new Select(currencyState);
        drop.selectByIndex(index);
    }

    public void setCurrencyName(String currencyName1) {
        currencyName.sendKeys(currencyName1);
    }

    public void setCurrencySymbol(String currencySymbol1) {
        currencySymbol.sendKeys(currencySymbol1);
    }

    public void setCurrencyCode(String currencyCode1) {
        currencyCode.sendKeys(currencyCode1);
    }

    public void setCurrencyRate(BigDecimal currencyRate1) {
        currencyRate.sendKeys(currencyRate1.toString());
    }

    public void setSearchTextArea(String searchTextArea1) {
        searchTextArea.sendKeys(searchTextArea1);
    }
}