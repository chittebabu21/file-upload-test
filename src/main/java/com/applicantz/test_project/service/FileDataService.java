package com.applicantz.test_project.service;

import com.applicantz.test_project.exception.CustomException;
import com.applicantz.test_project.model.FileData;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author: Masilamony Chittebabu
 * Created: 29/01/2026
 */
public interface FileDataService {

    /**
     * Method signature for the uploading and processing of files
     * @param file the file to be uploaded
     * @return the file data entity
     * @throws CustomException this method throws a custom exception with a user-friendly message
     */
    FileData uploadAndProcessFile(MultipartFile file) throws CustomException;
}
