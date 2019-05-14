package kz.logistic.logistic_server.controllers.rest;

import kz.logistic.logistic_server.controllers.BaseController;
import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Image;
import kz.logistic.logistic_server.models.entities.Item;
import kz.logistic.logistic_server.services.ImageService;
import kz.logistic.logistic_server.services.ItemService;
import kz.logistic.logistic_server.shared.utils.Constants;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;

/**
 * @author Assylkhan
 * on 11.05.2019
 * @project logistic_server
 */
@RestController
@RequestMapping("/api/images")
public class ImageController extends BaseController {


    private ImageService imageService;
    private ItemService itemService;
    @Autowired
    public ImageController(ImageService imageService,
                           ItemService itemService) {
        this.imageService = imageService;
        this.itemService = itemService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity fileUpload(@RequestParam("file") MultipartFile file, @RequestParam Long itemId)
            throws IOException, ServiceException {
        Item item = itemService.findById(itemId);
        Image image = new Image();
        String path = "/var/tmp/" + new Date().getTime() + file.getOriginalFilename();
        File convertFile = new File(path);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        System.out.println(path);
        image.setPath(path);
        image.setItem(item);
        image = imageService.save(image);
        return buildResponse(image, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public @ResponseBody
    ResponseEntity<byte[]> getFile(@PathVariable Long id) throws IOException, ServiceException {
        Image image = imageService.findByItemId(id);
        String filename = image.getPath();
        File file = null;
        if(!image.getPath().equals(Constants.NO_IMAGE)){
            file = new File(filename);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        }else{
            file = new ClassPathResource(image.getPath()).getFile();
        }
        Tika tika = new Tika();
        String mimeType = tika.detect(file);
        URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
        File file2 = new File(location.getPath());
        System.out.println(file2.getPath());
        MediaType mediaType = null;
        if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE)) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)) {
            mediaType = MediaType.IMAGE_PNG;
        } else {
            throw ServiceException.builder().errorCode(ErrorCode.INVALID_ARGUMENT).message("mimetype error").build();
        }

        return ResponseEntity.ok().contentType(mediaType).body(Files.readAllBytes(file.toPath()));
    }
}