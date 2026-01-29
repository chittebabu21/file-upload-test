package com.applicantz.test_project.controller;

import com.applicantz.test_project.dto.FileUploadResponse;
import com.applicantz.test_project.exception.CustomException;
import com.applicantz.test_project.model.FileData;
import com.applicantz.test_project.service.FileDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Slf4j
public class FileDataController {

    /**
     * Service class to be injected
     */
    private final FileDataService fileDataService;

    /**
     * Controller method that handles the uploading of the file
     * @param file the file to be uploaded
     * @return a custom response with the filename and status
     */
    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileData fileData = fileDataService.uploadAndProcessFile(file);
            FileUploadResponse fileUploadResponse = new FileUploadResponse();
            fileUploadResponse.setFileName(fileData.getFileName());
            fileUploadResponse.setStatus("success");

            return ResponseEntity.ok(fileUploadResponse);
        } catch (CustomException e) {
            log.error("Error uploading file", e);

            FileUploadResponse fileUploadResponse = new FileUploadResponse();
            fileUploadResponse.setStatus("failed");
            fileUploadResponse.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fileUploadResponse);
        }
    }
}
