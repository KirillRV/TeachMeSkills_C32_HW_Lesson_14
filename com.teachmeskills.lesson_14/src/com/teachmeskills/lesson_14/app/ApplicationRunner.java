package com.teachmeskills.lesson_14.app;

import com.teachmeskills.lesson_14.validator.DocumentValidator;
import com.teachmeskills.lesson_14.utils.Constants; // Импортируем Constants

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ApplicationRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the path to the input file: ");
        Path inputFilePath = Paths.get(scanner.nextLine());

        DocumentValidator validator = new DocumentValidator();
        validator.processDocuments(inputFilePath);

        System.out.println("Processing completed. Check the files:");
        System.out.println("Valid docnum: " + Constants.VALID_DOCNUMS_REPORT);
        System.out.println("Valid contract: " + Constants.VALID_CONTRACTS_REPORT);
        System.out.println("Invalid documents: " + Constants.INVALID_DOCUMENT_REPORT);
    }
}