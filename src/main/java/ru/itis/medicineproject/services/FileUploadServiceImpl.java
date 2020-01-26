package ru.itis.medicineproject.services;

import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.model.Disease;
import ru.itis.medicineproject.model.News;
import ru.itis.medicineproject.repositories.DiseaseRepository;
import ru.itis.medicineproject.repositories.NewsRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUploadServiceImpl implements FileUploadService  {

    private static final String UPLOAD_DIR = "uploads";

    private DiseaseRepository diseaseRepository;
    private NewsRepository newsRepository;

    public FileUploadServiceImpl(DiseaseRepository diseaseRepository, NewsRepository newsRepository) {
        this.diseaseRepository = diseaseRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public List<String> downloadFile(HttpServletRequest req) {
        List<String> errors = new ArrayList<>();
        String applicationPath = req.getServletContext().getRealPath("");
        Part part;
        String packageName;
        try {
            if(!req.getParts().isEmpty()) {
                if((part = req.getPart("disease_image")) != null) {
                    //Если картинка для болезни
                    packageName = "disease-images";
                    String uploadFilePath = getUploadDir(applicationPath, packageName);
                    String fileName = getFileName(part);
                    String name = req.getParameter("disease_name");
                    String description = req.getParameter("disease_text");
                    part.write(uploadFilePath + File.separator + fileName);
                    diseaseRepository.save(Disease.builder()
                            .setName(name)
                            .setDescription(description)
                            .setImagePath(File.separator + UPLOAD_DIR + File.separator + packageName + File.separator + fileName)
                            .setAuthorId(Bean.getInstance().getUserDto().getId())
                            .build());
                } else if ((part = req.getPart("news_image")) != null) {
                    //Если картинка для новости
                    packageName = "news-images";
                    String uploadFilePath = getUploadDir(applicationPath, packageName);
                    String fileName = getFileName(part);
                    String title = req.getParameter("news_name");
                    String text = req.getParameter("news_text");
                    part.write(uploadFilePath + File.separator + fileName);
                    newsRepository.save(News.builder()
                            .setAuthorId(Bean.getInstance().getUserDto().getId())
                            .setTitle(title)
                            .setText(text)
                            .setImagePath(File.separator + UPLOAD_DIR + File.separator + packageName + File.separator + fileName)
                            .setPreviewText(text.substring(0, 70))
                            .build());
                }
            }
            return errors;
        } catch (IOException | ServletException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    private String getUploadDir(String applicationPath, String packageName) {

        String toReturn = applicationPath + UPLOAD_DIR + File.separator + packageName;
        File file = new File(toReturn);
        if (!file.exists()) {
            file.mkdirs();
        }
        System.out.println(1 + "  " + toReturn);
        return toReturn;
    }
}
