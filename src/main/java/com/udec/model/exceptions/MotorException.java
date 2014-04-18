/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.exceptions;

/**
 *
 * @author windows7
 */
public class MotorException extends Exception {

    /**
     * Creates a new instance of <code>MotorException</code> without detail
     * message.
     */
    public MotorException() {
    }

    /**
     * Constructs an instance of <code>MotorException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MotorException(String msg) {
        super(msg);
    }
}
