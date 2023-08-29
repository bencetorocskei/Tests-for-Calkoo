import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pagefactory.CalculatorPage;



public class TestsForValueInputs {

    CalculatorPage calculator;

    @BeforeEach
    public void init() {
        calculator = new CalculatorPage();
        calculator.openCalculatorPage();
    }

    public void User_can_can_choose_one_input_type(String valueTypeName, String inputName1, String inputName2) {
        calculator.selectValueRadioBtn(valueTypeName);
        Assertions.assertTrue(calculator.valueInputIsDisabled(inputName1) && calculator.valueInputIsDisabled(inputName2));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "User_can_can_choose_one_input_type.csv")
    public void User_can_can_choose_one_input_type_parameterized(String valueTypeName, String inputName1, String inputName2) {
        User_can_can_choose_one_input_type(valueTypeName, inputName1, inputName2);
    }

}
