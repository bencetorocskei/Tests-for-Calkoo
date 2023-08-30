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

    public void User_can_can_choose_one_input_type(String valueTypeName, String inputName1, String inputName2, String inputName3) {
        calculator.selectValueRadioBtn(valueTypeName);
        calculator.selectValueInput(inputName1);
        Assertions.assertTrue(!calculator.valueInputIsDisabled(inputName1)
                && calculator.valueInputIsDisabled(inputName2)
                && calculator.valueInputIsDisabled(inputName3));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "User_can_can_choose_one_input_type.csv")
    public void User_can_can_choose_one_input_type_parameterized(String valueTypeName, String inputName1, String inputName2, String inputName3) {
        User_can_can_choose_one_input_type(valueTypeName, inputName1, inputName2, inputName3);
    }


    @Test
    public void User_can_enter_amounts_with_max_2_decimal_digits_precision() {
        String valueTypeName = "Price incl. VAT";
        String inputName = "Price";
        String value = "19898.1999";
        //String expected = value.indexOf("./,");
        calculator.setVatValueInput(valueTypeName, inputName, value);
        String actual = calculator.getValueInput(inputName);
        String expected = value.substring(0, (value.indexOf(".")+3)); //ToDo rewrite it so it handles "," as well

        //String regex = "^[0-9]*\\.[0-9][0-9]$";
        Assertions.assertEquals(expected, actual);
    }

}
