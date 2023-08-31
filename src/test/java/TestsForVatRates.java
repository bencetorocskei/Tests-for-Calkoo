import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;
import util.RandomNumberGenerator;

import java.sql.SQLOutput;
import java.util.List;

public class TestsForVatRates {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

//három országgal megjelennek e a százalékok, mit nézzek itt??? hogyy clickkelhető, hogy egyezik az adat?

    @ParameterizedTest
    @CsvFileSource(resources = "UserIsAbleToChooseAValidVATRateForTheSelectedCountry.csv")
    public void UserIsAbleToChooseAValidVATRateForTheSelectedCountry(String countryName, String vatRates) {
            calculator.selectCountry(countryName);
        List<String> rates = List.of(vatRates.split(" "));
        for (String rate : rates) {
            calculator.SelectTaxRate(rate);
        }
        System.out.println(rates);
            //Assertions.assertTrue(calculator.vatRatesAreClickable());
        }


    @Test
    public void IfThereAreMoreOptionsTheUserCanChooseOnlyOne() {
        for (int i = 0; i < 3; i++) {
            String countryValue = String.valueOf(RandomNumberGenerator.getRandomNumber(1, 170));
            calculator.selectCountry(countryValue);
            Assertions.assertTrue(calculator.onlyOneVatRateIsSelected());
        }
    }
}
