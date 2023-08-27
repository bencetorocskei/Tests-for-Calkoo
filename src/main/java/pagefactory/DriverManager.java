package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverManager {

    public static WebDriver driver = null;

    public static WebDriver createDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void shutDown() {
        driver.quit();
        driver = null;
    }
}
