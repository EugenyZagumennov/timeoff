package ez.timeoff.controllers;

import ez.timeoff.core.service.storage.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static ez.timeoff.core.service.utils.FileUtils.AVATAR_FILE_NAME;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/avatar/{login:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String login) {
        Resource file = fileStorageService.loadAsResource(login);
        return ResponseEntity.ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + file.getFilename() + "\"")
                 .body(file);
    }
}
