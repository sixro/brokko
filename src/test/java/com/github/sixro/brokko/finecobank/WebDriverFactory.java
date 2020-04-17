package com.github.sixro.brokko.finecobank;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class WebDriverFactory {

    // These rows should prevent ChromeDriver to log errors on std err...
    // but they don't work everytime...
    static {
        System.setProperty(
            ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        Logger.getLogger("org.openqa.selenium")
            .setLevel(Level.OFF);

        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
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
