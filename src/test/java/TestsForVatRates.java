import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;
import util.RandomNumberGenerator;

import java.sql.SQLOutput;

public class TestsForVatRates {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

//három országgal megjelennek e a százalékok

    @ParameterizedTest
    @CsvFileSource(resources = "UserIsAbleToChooseAValidVATRateForTheSelectedCountry.csv")
    public void UserIsAbleToChooseAValidVATRateForTheSelectedCountry(String countryName, String vatRates) {
            calculator.selectCountry(countryName);
        System.out.println(vatRates);
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
