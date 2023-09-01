import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;


import java.util.List;

public class TestsForVatRates {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "UserIsAbleToChooseAValidVATRateForTheSelectedCountry.csv")
    public void UserIsAbleToChooseAValidVATRateForTheSelectedCountry(String countryName, String vatRates) {
        calculator.selectCountry(countryName);
        List<String> rates = List.of(vatRates.split(" "));
        System.out.println(rates);
        for (String rate : rates) {
            Assertions.assertTrue(calculator.selectValidVATRate(rate.strip()));
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "UserCanSelectACountryWhichAppliesVATScheme.csv")
    public void IfThereAreMoreOptionsTheUserCanChooseOnlyOne(String countryName) {
        calculator.selectCountry(countryName);
        Assertions.assertTrue(calculator.onlyOneVatRateIsSelected());
    }
}
