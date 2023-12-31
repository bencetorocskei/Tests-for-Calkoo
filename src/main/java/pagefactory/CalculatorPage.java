package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


public class CalculatorPage extends BasePage {

    @FindBy(xpath = "//select[@name='Country']")
    WebElement countryDrp;
    @FindBy(xpath = "//label [contains (@for,'VAT')]")
    List<WebElement> rateItems;
    @FindBy(xpath = "//input[@id='NetPrice']")
    WebElement netPriceInput;
    @FindBy(xpath = "//input[@id='VATsum']")
    WebElement vatValueInput;
    @FindBy(xpath = "//input[@id='Price']")
    WebElement vatInclPriceInput;
    @FindBy(xpath = "//label[normalize-space()='Price incl. VAT']")
    WebElement vatInclPriceBtn;
    @FindBy(xpath = "//label[normalize-space()='Value-Added Tax']")
    WebElement vatValueBtn;
    @FindBy(xpath = "//label[normalize-space()='Price without VAT']")
    WebElement netPriceBtn;
    @FindBy (xpath = "//input [ contains(@id, 'VAT_')]")
    List <WebElement> vatRadioButtons;
    @FindBy(xpath = "//div[@class = 'fc-dialog-container']//p[@class = 'fc-button-label']")
    WebElement doNotConsentBtn;
    @FindBy(xpath = "//div[@id='chart_div']/div[@id='google-visualization-errors-all-1']/div[@id='google-visualization-errors-0']/span[1]")
    WebElement errorMsg;

    public void openCalculatorPage() {
        driver.get("https://www.calkoo.com/en/vat-calculator");
        try {
            doNotConsentBtn.click();
        } catch (NoSuchElementException e) {
            System.out.println("Dialog is closed");
        }
    }

    public void selectCountry(String countryName) {
        wait.until(ExpectedConditions.elementToBeClickable(countryDrp));
        Select country = new Select(countryDrp);
        country.selectByVisibleText(countryName);
    }

    public boolean vatRatesAreAvailable() {
        wait.until(ExpectedConditions.elementToBeClickable(rateItems.get(0)));
        return rateItems.size() != 0;
    }
    public boolean onlyOneVatRateIsSelected() {
        if (rateItems.size() == 0) {
            throw new IllegalArgumentException("there are no rates to select");
        }
        wait.until(ExpectedConditions.elementToBeClickable(rateItems.get(0)));
        List<String> idForRadioBtn = new ArrayList<>();
        rateItems.forEach(rateItem -> idForRadioBtn.add(rateItem.getAttribute("for")));
        System.out.println("idforradioBtn list " + idForRadioBtn);
        for (int i = 0; i < rateItems.size(); i++) {
            rateItems.get(i).click();
            String idNotToCheck = rateItems.get(i).getAttribute("for");
            List<String> idsToCheck = idForRadioBtn.stream().filter(s -> !s.equals(idNotToCheck)).toList();
            for (String id : idsToCheck) {
                if (driver.findElement(By.cssSelector("#" + id)).isSelected()) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean selectValidVATRate(String rate) {
        if (rateItems.size() == 0) {
            throw new IllegalArgumentException("there are no rates to select");
        }
        for (WebElement rateItem : rateItems) {
            if (rateItem.getText().strip().equals(rate)) {
                rateItem.click();
                return true;
            }
        }
        return false;
    }

    public void selectValueRadioBtn(String valueRadioBtnName) {
        WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='" + valueRadioBtnName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
        valueRadioBtn.click();
    }

    public void selectValueInput(String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        valueInput.click();
    }

    public void setVatValueInput(String valueRadioBtnName, String valueInputName, String value) {
        WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='" + valueRadioBtnName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
        valueRadioBtn.click();
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        valueInput.sendKeys(value);
    }

    public String getValueInput(String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        return valueInput.getAttribute("value");
    }


    public boolean valueInputIsDisabled(String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        return valueInput.getAttribute("class").contains("disabled");
    }

    public boolean errorMessageIsDisplayed() {
        return errorMsg.isDisplayed();
    }
}
