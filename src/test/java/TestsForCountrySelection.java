import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;


public class TestsForCountrySelection {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

    @ParameterizedTest
    @CsvFileSource (resources = "UserCanSelectACountryWhichAppliesVATScheme.csv")
    public void UserCanSelectACountryWhichAppliesVATScheme (String countryName) {
            calculator.selectCountry(countryName);
            Assertions.assertTrue(calculator.vatRatesAreAvailable());
    }

    @AfterEach
    public void tearDown() {
        calculator.shutDown();
    }
}
