package com.applicantz.test_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Masilamony Chittebabu
 * Created: 29/01/2026
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {

    /**
     * The name of the file
     */
    private String fileName;

    /**
     * Status message for the file upload
     */
    private String status;

    /**
     * Error message for the file upload
     */
    private String errorMessage;
}
