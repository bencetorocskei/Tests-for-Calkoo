
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;

import java.io.IOException;


public class TestsForValueInputs {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "UserCanChooseOneInputType.csv")
    public void UserCanChooseOneInputType(String valueTypeName, String inputName1, String inputName2, String inputName3) {
        calculator.selectValueRadioBtn(valueTypeName);
        calculator.selectValueInput(inputName1);
        Assertions.assertTrue(!calculator.valueInputIsDisabled(inputName1)
                && calculator.valueInputIsDisabled(inputName2)
                && calculator.valueInputIsDisabled(inputName3));
    }

    @ParameterizedTest
    @CsvFileSource (resources = "UserCanEnterAmountsWithMax2DecimalDigitsPrecision.csv")
    public void userCanEnterAmountsWithMax2DecimalDigitsPrecision(String valueTypeName, String inputName,
                                                                  String value, String expected) {
        calculator.setVatValueInput(valueTypeName, inputName, value);
        String actual = calculator.getValueInput(inputName);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "theUserGivesAValueTheCalculatorWillCalculateAndShowTheOther2Amounts.csv")
    public void theUserGivesAValueTheCalculatorWillCalculateAndShowTheOther2Amounts(String valueTypeName, String inputName,
                                                                                    String inputName2, String inputName3,
                                                                                    String value, String expected1,
                                                                                    String expected2) {
        calculator.setVatValueInput(valueTypeName, inputName, value);
        String actualInputValue2 = calculator.getValueInput(inputName2);
        String actualInputValue3 = calculator.getValueInput(inputName3);
        Assertions.assertTrue(expected1.equals(actualInputValue2) && (expected2.equals(actualInputValue3)));

    }

    @AfterEach
    public void tearDown() {
        calculator.shutDown();
    }

}
