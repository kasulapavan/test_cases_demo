package thrymr.net.hospital.management.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public interface ExcelService {
    public void getExcel(HttpServletResponse response) throws IOException;
}