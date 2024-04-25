package com.tools.pdfextractor;

import com.tools.pdfextractor.service.ResumeExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PdfExtractorApplication implements CommandLineRunner {

	@Autowired
	private ResumeExtractorService resumeExtractorService;

	public static void main(String[] args) {
		SpringApplication.run(PdfExtractorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		resumeExtractorService.extractAndInsertToDb();
	}
}
