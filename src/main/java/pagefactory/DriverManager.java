package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class DriverManager {

    public static WebDriver driver = null;

    public static WebDriver createDriver(String browserType) {
        if (driver == null) {
            switch (browserType) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                default:
                    driver = new ChromeDriver();
                    break;
            }
        }
        return driver;
    }
    public static void shutDown() {
        driver.quit();
        driver = null;
    }
}
