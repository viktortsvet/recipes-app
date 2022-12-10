package com.viktor.recipebackend.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileIOService {

    public File writeFile(@NonNull XWPFDocument document, String filename, String fileType) throws IOException {
        File fileOut = File.createTempFile(filename, fileType);
        FileOutputStream outputStream = new FileOutputStream(fileOut);
        document.write(outputStream);
        outputStream.flush();
        outputStream.close();
        return fileOut;
    }

    public ContentDescriptor getFileDescription(File fileOut, String type) throws IOException {
        ContentDescriptor cd = new ContentDescriptor();
        cd.content = FileCopyUtils.copyToByteArray(fileOut);
        cd.filename = fileOut.getName();
        cd.fileMimeType = type;
        return cd;
    }

    public ContentDescriptor generateFile(File fileOut, HttpServletResponse response, String fileType) throws IOException {
        ContentDescriptor cd = getFileDescription(fileOut, "application/" + fileType);
        if (cd != null && cd.content != null) {
            String filename = new String(cd.filename.getBytes("Cp1251"), "Cp1252");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentType(cd.fileMimeType);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(cd.content);
            outputStream.close();
            Files.delete(Paths.get(fileOut.getPath()));
        }
        return cd;
    }

    public static class ContentDescriptor {
        public ContentDescriptor() {}
        public byte[] content;
        public String filename;
        public String fileMimeType;
    }

}
