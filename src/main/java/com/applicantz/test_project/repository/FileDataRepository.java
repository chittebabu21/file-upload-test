package com.applicantz.test_project.repository;

import com.applicantz.test_project.model.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Author: Masilamony Chittebabu
 * Created: 29/01/2026
 */
@Repository
public interface FileDataRepository extends JpaRepository<FileData, UUID> {
}
