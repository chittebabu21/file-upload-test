package com.applicantz.test_project.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

/**
 * Author: Masilamony Chittebabu
 * Created: 29/01/2026
 */
@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class FileData {

    /**
     * The unique identifier for the row
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    /**
     * Tha name of the file
     */
    @Column(name = "file_name", nullable = false)
    private String fileName;

    /**
     * The number of lines of content in the file
     */
    @Column(name = "line_count")
    private int lineCount;

    /**
     * The number of words in the file
     */
    @Column(name = "word_count")
    private int wordCount;
}
