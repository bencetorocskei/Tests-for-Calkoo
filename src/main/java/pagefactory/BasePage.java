package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    String browserType = System.getProperty("browserType");

    public BasePage() {
        if (browserType == null) {
            browserType = "chrome";
        }

        this.driver = DriverManager.createDriver(browserType);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void shutDown() {
        DriverManager.shutDown();
    }
}
