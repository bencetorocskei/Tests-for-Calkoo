import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.ReadFromFile;
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
    public void userCanEnterAmountsWithMax2DecimalDigitsPrecision(String valueTypeName, String inputName) throws IOException, CsvException {
        String value = "19898.1999";
        ReadFromFile.readFromCsv();
        calculator.setVatValueInput(valueTypeName, inputName, value);
        String actual = calculator.getValueInput(inputName);
        String expected = value.substring(0, (value.indexOf(".")+3)); //ToDo rewrite it so it handles "," as well, solve the value problem
        //String regex = "^[0-9]*\\.[0-9][0-9]$";
        Assertions.assertEquals(expected, actual);
    }
}
