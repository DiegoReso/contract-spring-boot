package dev.reso.workshop.contract.util;

import dev.reso.workshop.contract.exceptions.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${app.upload.dir}")
    private String uploadDir;


        public String saveFile(MultipartFile file) {
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

            try {
                Files.createDirectories(uploadPath);
            } catch (IOException ex) {
                throw new FileStorageException("Could not create upload directory: " + uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex > 0) {
                fileExtension = originalFilename.substring(dotIndex);
            }
            String fileName = UUID.randomUUID().toString() + fileExtension;

            Path filePath = uploadPath.resolve(fileName);

            try {
                Files.copy(file.getInputStream(), filePath);
                return filePath.toString();
            } catch (IOException ex) {
                throw new FileStorageException("Could not store file " + originalFilename + ". Please, try again!");
            }
        }
}