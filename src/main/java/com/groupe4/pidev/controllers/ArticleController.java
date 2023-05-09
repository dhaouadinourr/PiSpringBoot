package com.groupe4.pidev.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupe4.pidev.Utils.FileUploadUtil;
import com.groupe4.pidev.entities.*;
import com.groupe4.pidev.repositories.ArticleRepo;
import com.groupe4.pidev.repositories.UserRepo;
import com.groupe4.pidev.services.IArticleService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private IArticleService articleService;
    private ArticleRepo articleRepo;
    private UserRepo userRepo;
    ServletContext context;

    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam(value = "q") String searchTerm){
        return articleRepo.findByTitleContaining(searchTerm);
    }
    private String saveImage(MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(context.getRealPath("/Imagess/" + multipartFile.getOriginalFilename()));
            Files.write(path, bytes);
            return multipartFile.getOriginalFilename();
        } catch (IOException e) {
            return null;
        }
    }
    @PostMapping("/add")
    public long add(@RequestParam("article") String article,
                    @RequestParam("file") MultipartFile image
                   ) throws JsonParseException, JsonMappingException, Exception {
        ArticleRequestBody arti = new ObjectMapper().readValue(article, ArticleRequestBody.class);
        boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
        String fileN = StringUtils.cleanPath(image.getOriginalFilename());
        arti.setPicture(fileN);
        System.out.println(arti.getId());
        String uploadDir = "Imagess/" + arti.getId();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileN, image);
        if (!isExit) {
            new File(context.getRealPath("/Imagess/")).mkdir();
        }
        String fileName = image.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
        File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile, image.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        arti.setPicture(newFileName);
        return articleService.addArticle(arti);
    }
    @GetMapping(path = "/Imgarticle/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        ArticleDetailsResponseBody Article = articleService.findArticleById(id);
        return Files.readAllBytes(Paths.get("Imagess/null/" + Article.getPicture()));
    }
    @GetMapping("/getAll")
    public List<ArticleResponseBody> findArticles() { return articleService.findAllArticle();}
    @GetMapping("/get/{id}")
    public ArticleDetailsResponseBody findArticleById(@PathVariable Long id){return articleService.findArticleById(id);}
    @PutMapping("/update/{id}")
    public Long editArticle(@PathVariable Long id,
                            @RequestParam("article") String article ,
                            @RequestParam("file") MultipartFile image
    )
            throws IOException{
            Article art = new ObjectMapper().readValue(article, Article.class);
            boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
            String fileN = StringUtils.cleanPath(image.getOriginalFilename());
            art.setPicture(fileN);
            System.out.println(art.getId());
            String uploadDir = "Imagess/" + art.getId();
            System.out.println(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileN, image);
            if (!isExit) {
                new File(context.getRealPath("/Imagess/")).mkdir();
            }
            String fileName = image.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
            File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
            try {
                FileUtils.writeByteArrayToFile(serverFile, image.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            art.setPicture(newFileName);
        return articleService.editArticle(id, art);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable Long id){
        articleService.deleteArticle(id);
    }
    @GetMapping("/add-etoile/{idA}/{idC}/{rev}")
    @ResponseBody
    public void moyEtoile(@PathVariable("idA") Long idA, @PathVariable("idC") Long idC, @PathVariable("rev") Double rev) {
        articleService.calculeEtoile(rev, idA, idC);
    }

}
