package com.example.springbootone2many.controller;

import com.example.springbootone2many.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadfile(@RequestParam("file")MultipartFile file) {
        try {
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getSize());
            System.out.println(file.getName());
            System.out.println(file.getContentType());

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");

            }
            if (!file.getContentType().equals("text/plain")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only text content type is allow");

            }
            // file upload  code..
            //where to upload the file on server
            //upload Directory
            boolean f = fileUploadHelper.uploadfile(file);
            if (f) {
                //  return ResponseEntity.ok("File is successfully upload");

                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());

            }


        } catch(Exception e){
                e.printStackTrace();
        }


        return ResponseEntity.ok("Working");
    }
}
