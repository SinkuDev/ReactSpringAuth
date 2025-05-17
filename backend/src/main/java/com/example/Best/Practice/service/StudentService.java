package com.example.Best.Practice.service;


import com.example.Best.Practice.entity.Studentdata;
import com.example.Best.Practice.repository.StudentdataRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


    private StudentdataRepository studentdataRepository;

    //Constructor
    public StudentService(StudentdataRepository studentdataRepository) {
        this.studentdataRepository = studentdataRepository;
    }

    public void saveStudentFromExcel(MultipartFile file) throws Exception{
        List<Studentdata> studentdata = new ArrayList<>();

        InputStream is = file.getInputStream();

        Workbook workbook = new XSSFWorkbook(is);

        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet){
            if (row.getRowNum() == 0)
                continue;

            Studentdata s= new Studentdata();

            s.setName(row.getCell(0).getStringCellValue());
            s.setRollNumber(row.getCell(1).getStringCellValue());
            s.setBranch(row.getCell(2).getStringCellValue());
            s.setYear((int) row.getCell(3).getNumericCellValue());

            studentdata.add(s);

            studentdataRepository.saveAll(studentdata);
            workbook.close();


        }
    }


}
