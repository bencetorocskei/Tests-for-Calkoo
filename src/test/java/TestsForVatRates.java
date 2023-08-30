import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;
import util.RandomNumberGenerator;

public class TestsForVatRates {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }


    @Test
    public void User_is_able_to_choose_a_valid_VAT_rate_for_the_selected_country() {
        //calculator.tryToSelectVatRate();
        //calculator.vatRatesAreClickable();
        for (int i = 0; i < 3; i++) {
            String countryValue = String.valueOf(RandomNumberGenerator.getRandomNumber(1, 170));
            calculator.selectCountry(countryValue);
            Assertions.assertTrue(calculator.vatRatesAreClickable());
        }
    }

    @Test
    public void If_there_are_more_options_the_user_can_choose_only_one() {
        for (int i = 0; i < 3; i++) {
            String countryValue = String.valueOf(RandomNumberGenerator.getRandomNumber(1, 170));
            calculator.selectCountry(countryValue);
            Assertions.assertTrue(calculator.onlyOneVatRateIsSelected());
        }
    }
}
