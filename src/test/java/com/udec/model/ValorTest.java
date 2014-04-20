/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import com.udec.benlly.Log;
import com.udec.controlador.LogJpaController;
import java.text.SimpleDateFormat;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class ValorTest extends TestCase {
    
    public ValorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetPoint() {
        Valor v = new Valor();
       v.setValorReal(123.0f);
        LogJpaController lc = new LogJpaController();
        Log log = lc.findLog(8);
        v.setLog(log);
        Object[] point = v.getPoint();
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("\nVALOR LOG: \t"+fecha.format(point[0])+" "+point[1]);       
    }
    
}
