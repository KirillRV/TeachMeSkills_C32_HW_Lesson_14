package com.teachmeskills.lesson_14.validator;

import com.teachmeskills.lesson_14.utils.Constants; // Импортируем Constants
import java.io.*;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class DocumentValidator {
    private final Pattern docnumPattern = Pattern.compile("^docnum[\\w]{10}$"); // 15 символов
    private final Pattern contractPattern = Pattern.compile("^contract[\\w]{8}$"); // 15 символов

    public void processDocuments(Path inputFilePath) {
        Path docnumOutputPath = Path.of(Constants.VALID_DOCNUMS_REPORT);
        Path contractOutputPath = Path.of(Constants.VALID_CONTRACTS_REPORT);
        Path invalidOutputPath = Path.of(Constants.INVALID_DOCUMENT_REPORT);

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath.toFile()));
             BufferedWriter docnumWriter = new BufferedWriter(new FileWriter(docnumOutputPath.toFile()));
             BufferedWriter contractWriter = new BufferedWriter(new FileWriter(contractOutputPath.toFile()));
             BufferedWriter invalidWriter = new BufferedWriter(new FileWriter(invalidOutputPath.toFile()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.length() != 15) {
                    invalidWriter.write(line + " - Invalid length (must be 15 characters)\n");
                    continue;
                }

                if (docnumPattern.matcher(line).matches()) {
                    docnumWriter.write(line + "\n");
                } else if (contractPattern.matcher(line).matches()) {
                    contractWriter.write(line + "\n");
                } else {
                    invalidWriter.write(line + " - Invalid starting sequence or invalid characters\n");
                }
            }

        } catch (IOException e) {
            System.err.println("Error processing documents: " + e.getMessage());
        }
    }
}