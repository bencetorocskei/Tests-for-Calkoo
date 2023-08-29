import org.junit.jupiter.api.Test;
import pagefactory.CalculatorPage;


public class TestForCalculator {

    @Test
    public void validateCountry() {
        CalculatorPage calculator = new CalculatorPage();
        calculator.openCalculatorPage();
        calculator.selectCountry("27");
        calculator.SelectTaxRate("5%");
        calculator.clickOnTheVatInclPriceBtn();
        calculator.clickOnTheNetPriceBtn();
        calculator.clickOnTheVatValueBtn();
        calculator.clickOnTheNetPriceInput();
        calculator.clickOnTheVATValueInput();
        calculator.clickOnTheVATInclPriceInput();
    }

}
