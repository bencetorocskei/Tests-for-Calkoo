package pagefactory;

import org.openqa.selenium.By;
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
    @FindBy (xpath = "//input[@id='NetPrice']")
    WebElement netPriceInput;
    @FindBy (xpath = "//input[@id='VATsum']")
    WebElement vatValueInput;
    @FindBy (xpath = "//input[@id='Price']")
    WebElement vatInclPriceInput;
    @FindBy (xpath = "//label[normalize-space()='Price incl. VAT']")
    WebElement vatInclPriceBtn;
    @FindBy (xpath = "//label[normalize-space()='Value-Added Tax']")
    WebElement vatValueBtn;
    @FindBy (xpath = "//label[normalize-space()='Price without VAT']")
    WebElement netPriceBtn;

    @FindBy (css = "input[value='Reset']")
    WebElement resetBtn;
    public void openCalculatorPage() {
        driver.get("https://www.calkoo.com/en/vat-calculator");
    }

    public void selectCountry(String countryValue) {
        Select country = new Select(countryDrp);
        country.selectByValue(countryValue);
        List<WebElement> allCountry = country.getOptions();
        for (WebElement c : allCountry) {
            System.out.println(c.getText());
        }
    }
    
    /*public List<String> getCountryNames () {
        Select country = new Select(countryDrp);
        List<WebElement> allCountry = country.getOptions();
        List <String> names = new ArrayList<>();
        for (WebElement c : allCountry) {
            //System.out.println(c.getText());
            names.add(c.getText());
        }
        return names;
    }

    public void getRatesForNames() {
        Select country = new Select(countryDrp);
        List <String> names = getCountryNames();
        for(String name : names) {
            country.selectByVisibleText(name);
            for (WebElement rateItem : rateItems) {
                System.out.print(rateItem.getText());
            }
            System.out.println();
        }
    }*/

    public void SelectTaxRate(String rate) {
        //Todo exception handling

        for (WebElement rateItem : rateItems) {
            if (rateItem.getText().strip().equals(rate)) {
                rateItem.click();
            }
            //System.out.println(rate);
            //System.out.print(rateItem.getText());
        }
        //System.out.println(rateItems.size());
    }
    public void selectValueRadioBtn (String valueRadioBtnName) {
        WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='"+valueRadioBtnName+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
        valueRadioBtn.click();
    }

    public void selectValueInput(String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='"+valueInputName+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        valueInput.click();
    }

    public void clickOnTheNetPriceBtn () {
        netPriceBtn.click();
    }
    public void clickOnTheVatValueBtn () {
        vatValueBtn.click();
    }
    public void clickOnTheVatInclPriceBtn () {
        vatInclPriceBtn.click();
    }
    public void clickOnTheNetPriceInput() {
        netPriceInput.click();
    }
    public void clickOnTheVATValueInput() {
        vatValueInput.click();
    }
    public void clickOnTheVATInclPriceInput() {
        vatInclPriceInput.click();
    }

    public boolean netPriceInputIsDisabled() {
        return netPriceInput.getAttribute("class").contains("disabled");
    }
    public boolean vatValueInputIsDisabled() {
        return vatValueInput.getAttribute("class").contains("disabled");
    }

    public boolean valueInputIsDisabled(String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='"+valueInputName+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        return valueInput.getAttribute("class").contains("disabled");
    }

}
