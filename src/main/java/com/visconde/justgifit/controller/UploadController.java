package com.visconde.justgifit.controller;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Value("${spring.http.multipart.location}")
	private String location;
	
	//Curl command --> curl -F file=@c:/cats.mp4 -F start=0 -F end=0 -F speed=1 -F repeat=0 localhost:8081/upload

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.IMAGE_GIF_VALUE)
	public String upload(@RequestPart("file") MultipartFile file, @RequestParam("start") int start,
			@RequestParam("end") int end, @RequestParam("speed") int speed, @RequestParam("repeat") boolean repeat)
			throws IllegalStateException, IOException {

		File videoFile = new File(location + "/" + System.currentTimeMillis() + ".mp4");

		file.transferTo(videoFile);

		log.info("Save file to {}", videoFile.getAbsolutePath());
		
		return "going on";
	}

}
