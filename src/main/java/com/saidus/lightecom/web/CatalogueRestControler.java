package com.saidus.lightecom.web;

import com.saidus.lightecom.dao.ProductRepository;
import com.saidus.lightecom.entities.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@CrossOrigin("*")
public class CatalogueRestControler {

    private ProductRepository productRepository;

    public CatalogueRestControler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/photoProduct/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws IOException {
        Product p = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")
                + "/ecom-photos/products/" + p.getPhotoName()));

    }

    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(@RequestParam MultipartFile file, @PathVariable Long id) throws IOException {
        Product p = productRepository.findById(id).get();
        p.setPhotoName(file.getOriginalFilename());
        // p.setPhotoName(id+".jpg");
        Files.write(Paths.get(System.getProperty("user.home")
                +"/ecom-photos/products/"+p.getPhotoName()), file.getBytes());

        productRepository.save(p);
    }


}
