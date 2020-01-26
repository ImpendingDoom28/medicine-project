package ru.itis.medicineproject.services;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FileUploadService {

    List<String> downloadFile(HttpServletRequest req);
}
