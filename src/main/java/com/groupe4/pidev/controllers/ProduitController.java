package com.groupe4.pidev.controllers;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupe4.pidev.entities.Produit;
import com.groupe4.pidev.entities.ProduitCategory;
import com.groupe4.pidev.services.IProduitService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("produit")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProduitController implements ServletContextAware {
    private IProduitService iProduitService;

    ServletContext context;


    @PostMapping("/add")
    public Produit newProduct(
            @RequestParam("product") String product,
            @RequestParam("file") MultipartFile image) throws JsonParseException, JsonMappingException, Exception {

        Produit arti = new ObjectMapper().readValue(product, Produit.class);
        boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
        if (!isExit) {
            new File(context.getRealPath("/Imagess/")).mkdir();
            System.out.println("mk dir Imagess.............");
        }
        System.out.println("Save Article  22222.............");


        String fileName = image.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
        try {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile, image.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Save Article 333333.............");
        arti.setImage(newFileName);

        System.out.println("Save Article 333333.............");
        // arti.setProducts(photos);


        return iProduitService.addProduit(arti);
    }

    @PutMapping("/update")
    public Produit editProduit(@RequestBody Produit Produit) {
        return iProduitService.editProduit(Produit);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduit(@PathVariable Long id) {
        iProduitService.deleteProduit(id);

    }

    @GetMapping("/getAll")
    public List<Produit> findAllProduit() {
        return iProduitService.findAllProduit();
    }

    @GetMapping("/get/{id}")
    public Produit findProduitById(@PathVariable Long id) {
        return iProduitService.findProduitById(id);
    }

    @GetMapping("/get/{produitCategory}")
    public List<Produit> findProdByCat(@PathVariable ProduitCategory produitCategory) {
        return iProduitService.findProdByCat(produitCategory);
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }

    @GetMapping(path = "/Imgarticle/{id}")
    public byte[] getProductImage(@PathVariable("id") Long id) throws Exception {
        Produit Article = iProduitService.findProduitById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + Article.getImage()));
    }

}

