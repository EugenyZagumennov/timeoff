package ez.timeoff.core.service.storage;

import ez.timeoff.core.service.storage.exceptions.StorageException;
import ez.timeoff.core.service.storage.exceptions.StorageFileNotFoundException;
import ez.timeoff.core.service.utils.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileStorageService {

    private final Path rootLocation;

    @Autowired
    public FileStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getUploadPath());
    }

    public void store(MultipartFile file, String login){
        try {
            Path path = rootLocation.resolve(FileUtils.getAvatarDirectory(login));
            if(!path.toFile().exists()){
                Files.createDirectories(path);
            }
            Files.copy(file.getInputStream(), rootLocation.resolve(FileUtils.getAvatarFile(login)), REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    public Resource loadAsResource(String login) {
        try {
            Path file = load(FileUtils.getAvatarFile(login));
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file for: " + login);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file for: " + login, e);
        }
    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
}