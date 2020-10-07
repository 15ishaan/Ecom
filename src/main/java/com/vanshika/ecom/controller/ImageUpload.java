package com.vanshika.ecom.controller;

import com.vanshika.ecom.model.Product;
import com.vanshika.ecom.repository.ProductRepository;
import com.vanshika.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageUpload {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/image/{name}")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file, @PathVariable String name) throws Exception {
        try {
            Product product = productService.findUsingName(name);
            byte[] fileContent = file.getBytes();
            productRepository.setImageByteForProduct((product.getId()), fileContent);
            return new ResponseEntity<String>("Image stored successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}