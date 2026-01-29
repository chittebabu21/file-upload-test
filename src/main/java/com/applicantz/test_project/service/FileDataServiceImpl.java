package com.applicantz.test_project.service;

import com.applicantz.test_project.exception.CustomException;
import com.applicantz.test_project.model.FileData;
import com.applicantz.test_project.repository.FileDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Author: Masilamony Chittebabu
 * Created: 29/01/2026
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FileDataServiceImpl implements FileDataService {

    /**
     * Allowed file extensions set
     */
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("txt", "csv");

    /**
     * Repository interface for dependency injection
     */
    private final FileDataRepository fileDataRepository;

    /**
     * Method signature for the uploading and processing of files
     *
     * @param file the file to be uploaded
     * @return the file data entity
     * @throws CustomException this method throws a custom exception with a user-friendly message
     */
    @Override
    public FileData uploadAndProcessFile(MultipartFile file) throws CustomException {
        log.info("Starting to upload file");
        validateFile(file);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));

            int lineCount = 0;
            int wordCount = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lineCount++;
                if(!line.trim().isEmpty()){
                    wordCount += line.trim().split("\\s+").length;
                }
            }

            FileData fileData = new FileData();
            fileData.setFileName(file.getOriginalFilename());
            fileData.setLineCount(lineCount);
            fileData.setWordCount(wordCount);
            fileDataRepository.save(fileData);

            log.info("File uploaded and processed successfully: {}", fileData.getFileName());
            return fileData;
        } catch (IOException e) {
            log.error("Error uploading file", e);
            throw new CustomException("Failed to upload file");
        }
    }

    /**
     * Validation method to validate if the file has the allowed extensions.
     * @param file the file to be uploaded
     * @throws CustomException this method throws a custom exception with a user-friendly message
     */
    private void validateFile(MultipartFile file) throws CustomException {
        if (file.isEmpty()) {
            log.error("File is empty");
            throw new CustomException("File is empty");
        }

        String fileName = file.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) {
            log.error("Invalid file name");
            throw new CustomException("Invalid file name");
        }

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            log.error("Invalid file type");
            throw new CustomException("Invalid file type");
        }
    }
}
