package com.tools.pdfextractor.service;

import com.tools.pdfextractor.entity.Resume;
import com.tools.pdfextractor.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResumeExtractorService {

    private final ResumeRepository resumeRepository;

    public boolean extractAndInsertToDb() {
        try {
            final InputStream inputStream = getClass().getResourceAsStream("/resume.pdf");
            final PDDocument pdDocument = PDDocument.load(inputStream);
            final PDFTextStripper pdfTextStripper = new PDFTextStripper();
            final String pdfText = pdfTextStripper.getText(pdDocument);
            pdDocument.close();

            final String name = pdfText.substring(0, pdfText.indexOf("Customer Service"));
            final Resume resume = Resume.builder().name(name).pdfContent(inputStream.readAllBytes()).build();
            resumeRepository.save(resume);

            log.info("Successfully extracted PDF and updated database");
            return true;
        } catch (Exception exception) {
            log.error("error extracting Pdf " + exception);
            return false;
        }
    }
}
