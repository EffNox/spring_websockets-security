package nixroyal.clientesappback.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImp implements IUploadFileService {

    private static final Logger lg = LoggerFactory.getLogger(UploadFileServiceImp.class);

    private final static String UPLOAD_DIR = "uploads";

    @Override
    public Resource getFile(String fileName) throws MalformedURLException {
        Path pathFile = getPathFile(fileName);
        Resource recurso = null;
        recurso = new UrlResource(pathFile.toUri());
        if (!recurso.exists() && !recurso.isReadable()) {
            pathFile = Paths.get("src/main/resources/static/img").resolve("no-u.jpg").toAbsolutePath();
            recurso = new UrlResource(pathFile.toUri());
            lg.info("Error no se pudo cargar la imagen: " + fileName);
        }
        return recurso;
    }

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
        // Path pathFile = getPath(fileName); // lg.info(pathFile.toString());
        Files.copy(file.getInputStream(), getPathFile(fileName));
        return fileName;
    }

    @Override
    public boolean deleteFile(String fileNameOld) {
        if (fileNameOld != null && fileNameOld.length() > 0) {
            File fileOld = getPathFile(fileNameOld).toFile();
            if (fileOld.exists() && fileOld.canRead()) {
                fileOld.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPathFile(String fileName) {
        return Paths.get(UPLOAD_DIR).resolve(fileName).toAbsolutePath();
    }

}
