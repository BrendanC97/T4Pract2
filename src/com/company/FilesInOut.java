package com.company;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

/**
 * CSCU9T4 Practical2 IO for files
 */
public class FilesInOut {

    public static void main(String[] args) throws FileNotFoundException {

        boolean toUpperCase = false; //Initializing variable for changing text to upper case
        String inputFileName = null; //Initializing variables for output and input file names
        String outputFileName = null;

        Scanner in = new Scanner(System.in);

        // Program will try to read an input file from the user's text input.
        System.out.println("Supply input file name to be used:");
        try {
            inputFileName = in.nextLine();
            File inputFile = new File(inputFileName);
            Scanner inFile = new Scanner(inputFile);
            inFile.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage() + "not found, please try again with correct file name");
        }

        // Program will try to write to an output file from the user's text input.
        System.out.println("Supply output file name to be used:");
        try {
            outputFileName = in.nextLine();
            File outputFile = new File(outputFileName);
            PrintWriter printWriter = new PrintWriter(outputFileName);
            printWriter.close();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage() + "file not found or not reachable");
            System.exit(0);
        }
        System.out.println("Data in file to uppercase? (y/n): ");// checks if the user wants their output in uppercase
        String upperCaseResult = in.nextLine().toLowerCase();

        if (upperCaseResult.equals("y")) { //Changing boolean upperCaseResult to true if the user wants all data stored to be in upper case
            toUpperCase = true;
        }
        //Check of boolean and calling relevant method
        if (toUpperCase) {
            allToUpperCase(inputFileName, outputFileName);
        } else {
            changeTitleCase(inputFileName, outputFileName);
        }

    }

    /**
     * Method is called and will capitalise the first letter of each word and to handle middle intials.
     * Dates are also adjust to be displayed correctly by adding a '/' between each two digits.
     *
     * @param inputFile  - File that will be read.
     * @param outputFile - File that will be written to.
     */
    public static void changeTitleCase(String inputFile, String outputFile) throws FileNotFoundException {
        File input = new File(inputFile);
        File output = new File(outputFile);
        PrintWriter printWriter = new PrintWriter(output);

        Scanner in = new Scanner(input);
        while (in.hasNextLine()) {
            Scanner in2 = new Scanner(in.nextLine());
            // All inputs in text file will be read in a line using lineinput and in2
            while (in2.hasNext()) {
                String lineInput = in2.next();
                //Check to see if at a line if the word contains only one letter which is used to find if the input was an initial
                if (Character.isLetter(lineInput.charAt(0)) && lineInput.length() == 1) {
                    lineInput = lineInput.toUpperCase() + ".";
                } else if (Character.isLetter(lineInput.charAt(0))) { //Check to see if character is the first letter and capitalise it if so
                    lineInput = Character.toUpperCase(lineInput.charAt(0)) + lineInput.substring(1);
                } else if (Character.isDigit(lineInput.charAt(0))) { //Check to see if character is a digit.
                    lineInput = lineInput.substring(0, 2) + "/" + lineInput.substring(2, 4) + "/" + lineInput.substring(4, 8) + ".";//Add slashes at relevant positions.
                }
                printWriter.print(lineInput + " "); //Adding spaces to each after each lineinput for formatting
            }
            printWriter.println();
            in2.close();
        }
        in.close();
        printWriter.close();
    }

    /**
     * Each word is converted to uppercase including the middle intial and add a '.'
     * formatting for the date to add '/' is also used.
     *
     * @param inputFile  - File to be read from
     * @param outputFile - File to be written to
     */
    public static void allToUpperCase(String inputFile, String outputFile) throws FileNotFoundException {
        File input = new File(inputFile);
        File output = new File(outputFile);
        PrintWriter printWriter = new PrintWriter(output);
        Scanner in = new Scanner(input);
        while (in.hasNextLine()) {
            Scanner in2 = new Scanner(in.nextLine());
            // read all the inputs in a line
            while (in2.hasNext()) {
                String lineInput = in2.next();
                //Check to see if it is a single letter and to capitalise and add a '.'
                if (Character.isLetter(lineInput.charAt(0)) && lineInput.length() == 1) {
                    lineInput = lineInput.toUpperCase() + ".";
                } else if (Character.isLetter(lineInput.charAt(0))) { //Looking for letters and converting all the letters to be capitalised
                    lineInput = lineInput.toUpperCase();
                } else if (Character.isDigit(lineInput.charAt(0))) { //Checking to see if the character is a digit and adding '/' added '.' for neatness
                    lineInput = lineInput.substring(0, 2) + "/" + lineInput.substring(2, 4) + "/" + lineInput.substring(4, 8) + ".";
                }
                printWriter.print(lineInput + " ");
            }
            printWriter.println();
            in2.close();
        }
        in.close();
        printWriter.close();
    }
}