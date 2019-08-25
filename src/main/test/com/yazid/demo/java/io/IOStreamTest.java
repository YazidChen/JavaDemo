package com.yazid.demo.java.io;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * @author YazidChen
 * @date 2019/07/27 0027 10:34
 */
public class IOStreamTest {

    @Test
    public void tempTest() {
        IOStreamDemo ioStreamDemo = new IOStreamDemo();
        ioStreamDemo.temp();
    }

    @Test
    public void textFileTest() throws FileNotFoundException, UnsupportedEncodingException {
        IOStreamDemo ioStreamDemo = new IOStreamDemo();
        ioStreamDemo.TextFile();
    }
}
