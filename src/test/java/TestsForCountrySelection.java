import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
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
    public void UserCanSelectACountryWhichAppliesVATScheme (String countryName) {
            calculator.selectCountry(countryName);
            Assertions.assertTrue(calculator.vatRatesAreAvailable());
    }

    @ParameterizedTest
    @CsvFileSource (resources = "UserCanSelectACountryWhichAppliesVATScheme.csv")
    public void UserCanSelectACountryWhichAppliesVATSchemeParameterized (String countryName) {
        UserCanSelectACountryWhichAppliesVATScheme(countryName);
    }
}
