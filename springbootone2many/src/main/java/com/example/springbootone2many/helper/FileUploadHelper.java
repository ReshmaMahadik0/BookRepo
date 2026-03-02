package com.example.springbootone2many.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    //public final String upload_dir="C:\\Users\\prade\\Downloads\\springbootone2many\\springbootone2many\\src\\main\\resources\\static\\image";
    public final String upload_dir=new ClassPathResource("static/image/").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException {
    }

    public boolean uploadfile(MultipartFile multipartFile){

        boolean f = false;
        try{
//            InputStream is = multipartFile.getInputStream();
//            byte date[] = new byte[is.available()];
//            is.read(date);
//
//            //write
//            FileOutputStream fileOutputStream =new  FileOutputStream(upload_dir+ File.separator+multipartFile.getOriginalFilename());
//            fileOutputStream.write(date);
//
//            fileOutputStream.flush();
//            fileOutputStream.hashCode();

            Files.copy(multipartFile.getInputStream(), Paths.get(upload_dir+File.separator+ multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            f=true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

}
