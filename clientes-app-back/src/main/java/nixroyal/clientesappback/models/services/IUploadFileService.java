package nixroyal.clientesappback.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
    public Resource getFile(String fileName) throws MalformedURLException;

    public String saveFile(MultipartFile file) throws IOException;

    public boolean deleteFile(String fileNameOld);

    public Path getPathFile(String fileName);
}
