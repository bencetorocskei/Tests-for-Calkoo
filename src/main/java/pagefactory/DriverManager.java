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
    /*public static WebDriver createWebDriver(Boolean isRemote, String browserType) throws MalformedURLException {
        if (webDriver == null) {
            if (!isRemote) {
                switch (browserType) {
                    case "firefox":
                        webDriver = new FirefoxDriver();
                        break;
                    case "chrome":
                    default:
                        webDriver = new ChromeDriver();
                        break;
                }
            } else {
                if (Objects.equals(browserType, "chrome")){
                    ChromeOptions chromeOptions = new ChromeOptions();
                    webDriver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                }
                else {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    webDriver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                }
            }
        }
        return webDriver;
    }*/