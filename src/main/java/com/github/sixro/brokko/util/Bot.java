package com.github.sixro.brokko.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Map;

/**
 * Represents a bot.
 *
 * <p>
 * This abstraction is just to simplify usage of selenium, indeed as you
 * can see we are using anyway the {@code By} object to define the target of
 * actions.
 * </p>
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public interface Bot {

    /**
     * Open the specified url.
     *
     * @param url a url
     *
     * @deprecated Use {@link #open(String, ExpectedCondition)}
     */
    void open(String url);
    /**
     * Open the specified url and wait for expected condition.
     *
     * @param url a url
     * @param expectedCondition expected condition to wait before returning
     */
    void open(String url, ExpectedCondition<WebElement> expectedCondition);

    /**
     * Wait for the specified element to be visible.
     *
     * @param xpath a xpath expression
     */
    void waitFor(By xpath);

    /**
     * Fill specified elements with specified texts.
     *
     * @param textsForEachElement texts for each element
     */
    void fill(Map<By, String> textsForEachElement);

    /**
     * Click on the specified element.
     *
     * @param xpath a xpath representing the element
     */
    void click(By xpath);

    /**
     * Returns true if the user can see the specified element.
     *
     * @param xpath a xpath representing the element
     * @return true if the user can see the specified element, otherwise false
     */
    boolean see(By xpath);

    /**
     * Scroll down the page of the specified amount of pixels.
     *
     * @param pixels number of pixels
     */
    void scrollDown(int pixels);
}
