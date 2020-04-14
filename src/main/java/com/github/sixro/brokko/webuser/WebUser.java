package com.github.sixro.brokko.webuser;

import org.openqa.selenium.By;

import java.time.Duration;

/**
 * Represents a web user allowing to describe actions in a simple manner.
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
public interface WebUser {

    /**
     * Open the specified url.
     *
     * @param url a url
     */
    void open(String url);

    /**
     * Wait for the specified element to be visible.
     *
     * @param xpath a xpath expression
     */
    void waitFor(By xpath);

    /**
     * Wait for the specified element to be visible in the specified timeout.
     *
     * @param xpath a xpath expression
     * @param timeout max time to wait
     */
    void waitFor(By xpath, Duration timeout);

    /**
     * Insert specified text in the specified element.
     *
     * @param text text to insert
     * @param xpath a xpath representing the element
     */
    void insert(String text, By xpath);

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
     * The user leaves.
     */
    void leave();

}
