package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
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

    @FindBy(css = "input[value='Reset']")
    WebElement resetBtn;

    @FindBy(xpath = "//div[@class = 'fc-dialog-container']")
    WebElement dialog;
    @FindBy(xpath = "//div[@class = 'fc-dialog-container']//p[@class = 'fc-button-label']")
    WebElement doNotConsentBtn;


    public void openCalculatorPage() {
        driver.get("https://www.calkoo.com/en/vat-calculator");
    }

    public void selectCountry(String countryName) {
        wait.until(ExpectedConditions.elementToBeClickable(countryDrp));
        Select country = new Select(countryDrp);
        country.selectByVisibleText(countryName);
        /*List<WebElement> allCountry = country.getOptions();
        for (WebElement c : allCountry) {
            System.out.println(c.getText());
        }*/
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

    public boolean vatRatesAreAvailable() {
        wait.until(ExpectedConditions.elementToBeClickable(rateItems.get(0)));
        /*for (WebElement item : rateItems) {
            System.out.println(item.getText());
        }*/
        return rateItems.size() != 0;
    }

    public boolean vatRatesAreClickable() {
        if (rateItems.size() == 0) {
            throw new IllegalArgumentException("there are no rates to select");
        }

        try {
            if (vatRatesAreEnable()) return false;
        } catch (ElementClickInterceptedException e) {

            doNotConsentBtn.click();
            if (vatRatesAreEnable()) return false;
        }
        return true;
    }

    private boolean vatRatesAreEnable() {
        wait.until(ExpectedConditions.elementToBeClickable(rateItems.get(0)));
        for (WebElement item : rateItems) {
            //System.out.println(item.isEnabled());
            item.click();
            String idForRadioBtn = item.getAttribute("for");
            WebElement relatedRadioBtn = driver.findElement(By.cssSelector("#" + idForRadioBtn));
            //System.out.println(relatedRadioBtn.isSelected());
            //System.out.println(item.isEnabled());
            if (!relatedRadioBtn.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public boolean onlyOneVatRateIsSelected() {
        if (rateItems.size() == 0) {
            throw new IllegalArgumentException("there are no rates to select");
        }
        wait.until(ExpectedConditions.elementToBeClickable(rateItems.get(0)));

        //since the radio-buttons can't be reached only the related label is clickable,
        //but you can't apply the function isSelected() on a label,
        // I collect the button id's from the labels, and through the ids I can check if it's selected or not.

        List<String> idForRadioBtn = new ArrayList<>();
        rateItems.forEach(rateItem -> idForRadioBtn.add(rateItem.getAttribute("for")));
        try {
            if (oneRateOptionIsSelected(idForRadioBtn)) return false;
        } catch (ElementClickInterceptedException e) {
            doNotConsentBtn.click();
            if (oneRateOptionIsSelected(idForRadioBtn)) return false;
        }
        return true;
    }

    private boolean oneRateOptionIsSelected(List<String> idForRadioBtn) {
        for (int i = 0; i < rateItems.size(); i++) {
            //System.out.println(item.isEnabled());
            rateItems.get(i).click();
            String idNotToCheck = rateItems.get(i).getAttribute("for");
            List<String> idsToCheck = idForRadioBtn.stream().filter(s -> !s.equals(idNotToCheck)).toList();
            for (String id : idsToCheck) {
                if (driver.findElement(By.cssSelector("#" + id)).isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    /*public void tryToSelectVatRate() {
        WebElement rate20 = driver.findElement(By.xpath("(//input[@id='VAT_20'])[1]"));
        //WebElement rate5 = driver.findElement(By.xpath("(//input[@id='VAT_5'])[1]"));
        WebElement rate5 = driver.findElement(By.cssSelector("#VAT_5"));

        System.out.println(rate20.isEnabled());
        System.out.println(rate20.isSelected());
        System.out.println(rate5.isSelected());

        rate5.click();
        System.out.println(rate20.isSelected());
    }*/

    public void SelectTaxRate(String rate) {
        if (rateItems.size() == 0) {
            throw new IllegalArgumentException("there are no rates to select");
        }

        for (WebElement rateItem : rateItems) {
            if (rateItem.getText().strip().equals(rate)) {
                rateItem.click();
            }
            //System.out.println(rate);
            //System.out.print(rateItem.getText());
        }
        //System.out.println(rateItems.size());
    }

    public void selectValueRadioBtn(String valueRadioBtnName) {
        try {
            WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='" + valueRadioBtnName + "']"));
            wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
            valueRadioBtn.click();

        } catch (ElementClickInterceptedException e) {
            doNotConsentBtn.click();
            WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='" + valueRadioBtnName + "']"));
            wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
            valueRadioBtn.click();
        }
    }

    public void selectValueInput(String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        //action.moveToElement(valueInput).click().build().perform();
        valueInput.click();
    }
    public void setVatValueInput(String valueRadioBtnName ,String valueInputName ,String value) {

        try {
            WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='" + valueRadioBtnName + "']"));
            wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
            valueRadioBtn.click();
            WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
            valueInput.sendKeys(value);

        } catch (ElementClickInterceptedException e) {
            doNotConsentBtn.click();
            WebElement valueRadioBtn = driver.findElement(By.xpath("//label[normalize-space()='" + valueRadioBtnName + "']"));
            wait.until(ExpectedConditions.elementToBeClickable(valueRadioBtn));
            valueRadioBtn.click();
            WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
            valueInput.sendKeys(value);
        }
    }

    public String getValueInput (String valueInputName) {
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        return valueInput.getAttribute("value");
    }
    public void clickOnTheNetPriceBtn() {
        netPriceBtn.click();
    }

    public void clickOnTheVatValueBtn() {
        vatValueBtn.click();
    }

    public void clickOnTheVatInclPriceBtn() {
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
        WebElement valueInput = driver.findElement(By.xpath("//input[@id='" + valueInputName + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(valueInput));
        return valueInput.getAttribute("class").contains("disabled");
    }

}
