package com.github.sixro.brokko.finecobank;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.logging.Level;

public final class WebDriverFactory {

    // These rows should prevent ChromeDriver to log errors on std err...
    // but they don't work everytime...
    static {
        System.setProperty(
            ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium")
            .setLevel(Level.OFF);
    }

    private WebDriverFactory() { }

    public static WebDriver chrome() {
        return chrome(true);
    }

    public static WebDriver chrome(boolean hideUI) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver chromeDriver = new ChromeDriver(newChromeOptions(hideUI));
        return chromeDriver;
    }

    private static ChromeOptions newChromeOptions(boolean hideUI) {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(hideUI);
        return options;
    }

}
