import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pagefactory.CalculatorPage;
import util.RandomNumberGenerator;

public class TestsForCountrySelection {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

    @Test
    public void User_can_select_a_country_which_applies_VAT_scheme () {
        for (int i = 0; i < 3; i++) {
            String countryValue = String.valueOf(RandomNumberGenerator.getRandomNumber(1, 170));
            calculator.selectCountry(countryValue);
            Assertions.assertTrue(calculator.vatRatesAreAvailable());
        }
    }
}
