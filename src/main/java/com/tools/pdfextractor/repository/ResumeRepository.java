package com.tools.pdfextractor.repository;

import com.tools.pdfextractor.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
