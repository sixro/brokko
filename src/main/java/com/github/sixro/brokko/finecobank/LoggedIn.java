package com.github.sixro.brokko.finecobank;

/**
 * Represents an object able to ensure that we are logged in.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
interface LoggedIn {

    /**
     * Ensure it is logged in.
     */
    void ensure();

}
