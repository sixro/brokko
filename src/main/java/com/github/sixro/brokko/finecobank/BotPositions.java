package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Position;
import com.github.sixro.brokko.Positions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;

class BotPositions implements Positions {

    private static final By TAB_PORTFOLIO = By.xpath("//li[@id='tab" +
        "-portafoglio']/a");
    private static final ExpectedCondition<WebElement> PORTFOLIO_LIST =
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id" +
            "='portfolio-sintesi-elenco']"));
    private static final By SINGLE_POSITION = By.xpath("//tbody" + "[@id" +
        "='titoli-table-body']/tr/td[1]/a");
    private static final int TEN_SECONDS = 10;

    private final WebDriver webDriver;
    private final Wait<WebDriver> wait;

    BotPositions(WebDriver webDriver) {
        this(webDriver, new WebDriverWait(webDriver, TEN_SECONDS));
    }

    BotPositions(WebDriver webDriver, Wait<WebDriver> wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public Iterator<Position> iterator() {
        webDriver.findElement(TAB_PORTFOLIO)
            .click();
        wait.until(PORTFOLIO_LIST);

        return webDriver.findElements(SINGLE_POSITION)
            .stream()
            .map(el -> (Position) new BotPosition(webDriver, el))
            .iterator();
    }

}
