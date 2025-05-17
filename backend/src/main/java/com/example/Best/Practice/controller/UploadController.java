package com.example.Best.Practice.controller;


import com.example.Best.Practice.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:5173")
public class UploadController {

    private StudentService studentService;

    //Constructor
    public UploadController(StudentService studentService){
        this.studentService = studentService;

    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        try{
            studentService.saveStudentFromExcel(file);
            return ResponseEntity.ok("file upload and data saved to DB.");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error or :"+ e.getMessage());
        }
    }

}
