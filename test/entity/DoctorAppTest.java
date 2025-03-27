/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Acer
 */
public class DoctorAppTest {

    @Test
    public void testMainExit() {
        String simulatedInput = "8\n";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            DoctorApp.main(new String[]{});

            String output = baos.toString();

            assertTrue("Output should contain the title of the management system",
                    output.contains("Doctor Management System"));

            assertTrue("Output should contain exit message",
                    output.contains("Exiting program"));
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
