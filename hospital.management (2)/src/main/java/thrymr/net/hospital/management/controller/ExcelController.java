package thrymr.net.hospital.management.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thrymr.net.hospital.management.service.impl.ExcelServiceImpl;

import java.io.IOException;

/**
 * @author Sneha
 * @ProjectName hospital.management (2)
 * @since 26-08-2023
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    ExcelServiceImpl excelService;
    @PostMapping("/upload")
    public String excelToDB(@RequestParam("file") MultipartFile file) throws IOException {
        excelService.excelToDB(file);
        return "Data saved successfully";
    }

    @GetMapping("/excel")
    public void generateExcel(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application");
        String headerKey="Content-disposition";
        String headerValue="filename=P4-4 huge file - 3M (1).csv";
        httpServletResponse.setHeader(headerKey,headerValue);
        excelService.getExcel(httpServletResponse);
    }

}