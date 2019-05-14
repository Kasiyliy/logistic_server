package kz.logistic.logistic_server.services.impl;

import kz.logistic.logistic_server.exceptions.ServiceException;
import kz.logistic.logistic_server.models.entities.Car;
import kz.logistic.logistic_server.models.entities.Company;
import kz.logistic.logistic_server.models.entities.Image;
import kz.logistic.logistic_server.repositories.CarRepository;
import kz.logistic.logistic_server.repositories.ImageRepository;
import kz.logistic.logistic_server.services.CarService;
import kz.logistic.logistic_server.services.ImageService;
import kz.logistic.logistic_server.shared.utils.Constants;
import kz.logistic.logistic_server.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image findByItemId(Long id) throws ServiceException {
        Image image = imageRepository.findByItemId(id);
        if (image == null) {
            image = new Image();
            image.setPath(Constants.NO_IMAGE);
        }
        return image;
    }

    @Override
    public Image save(Image image) throws ServiceException {
        return imageRepository.save(image);
    }
}
