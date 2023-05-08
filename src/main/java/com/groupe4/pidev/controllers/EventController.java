package com.groupe4.pidev.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupe4.pidev.Utils.FileUploadUtil;
import com.groupe4.pidev.entities.Categorie;
import com.groupe4.pidev.entities.Evenement;
import com.groupe4.pidev.entities.MultiPicture;
import com.groupe4.pidev.entities.Mymission;
import com.groupe4.pidev.repositories.MultiPictureRepo;
import com.groupe4.pidev.services.EventServiceImpl;
import com.groupe4.pidev.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@AllArgsConstructor
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController implements ServletContextAware {
    IEventService iEventService;
    ServletContext context;

    @Autowired
    MultiPictureRepo imageRepository;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;

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
    public long add(@RequestParam("files") MultipartFile[] files,
                    @RequestParam("event") String evenement,
                    @RequestParam("file") MultipartFile image) throws JsonParseException, JsonMappingException, Exception {

        Evenement arti = new ObjectMapper().readValue(evenement, Evenement.class);
        boolean isExit = new File(context.getRealPath("/Imagess/")).exists();
        String fileN = StringUtils.cleanPath(image.getOriginalFilename());
        arti.setPicture(fileN);
        System.out.println("====================");

        System.out.println(arti.getId_event());
        String uploadDir = "Imagess/" + arti.getId_event();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileN, image);
        if (!isExit) {
            new File(context.getRealPath("/Imagess/")).mkdir();
            System.out.println("mk dir Imagess.............");
        }
        System.out.println("Save Article  22222.............");
        Set<MultiPicture> photos = new HashSet<>();
        for (MultipartFile file : files) {
            MultiPicture fileinfo = new MultiPicture();
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
            File serverFile = new File(context.getRealPath("/Imagess/" + File.separator + newFileName));
            try {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

            } catch (Exception e) {
                e.printStackTrace();
            }
            fileinfo.setName(newFileName);
            fileinfo.setImage(arti);
            imageRepository.save(fileinfo);
            photos.add(fileinfo);
        }
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
        arti.setPicture(newFileName);

       /* System.out.println("Save Article 333333.............");
        iEventService.SendSms("+21628736139",
                "We are excited to invite you to an upcoming event that we believe you will enjoy. The event will be taking place on [Date] at [Time] and will be located at [Location].\n" +
                        "\n" +
                        "We have added you to the guest list, and we cannot wait for you to join us. We believe that this event will be a great opportunity for you to meet new people, learn new things, and have a great time.\n" +
                        "\n" +
                        "If you have any questions about the event or need any assistance, please do not hesitate to contact us. We look forward to seeing you at the event.\n" +
                        "\n" +
                        "Best regards,");*/

        return iEventService.addEvent(arti);
    }

    @PutMapping("/update")
    public Evenement update(@RequestBody Evenement e) {
        return iEventService.editEvent(e);
    }

    @GetMapping("/getAll")
    public List<Evenement> getAll() {

        return iEventService.findAllEvent();
    }

    @DeleteMapping("/delete/{id}")
    public void Delete(@PathVariable("id") Long id) {
        iEventService.deleteEvent(id);
    }

    @GetMapping("/get/{id}")
    public Evenement get(@PathVariable("id") Long id) {
        return iEventService.findEventById(id);
    }

    //
    @GetMapping("/getEvent/{cat}")

    public List<Evenement> findEventbyCateg(@PathVariable("cat") Categorie cat) {
        return iEventService.findEventbyCateg(cat);
    }


//    @GetMapping(path = "/Imgarticles/{id}")
//    public List<byte[]> getPhoto(@PathVariable("id") Long id) throws Exception {
//        ArrayList<MultiPicture> files = new ArrayList<MultiPicture>();
//        Evenement event = iEventService.findEventById(id);
//        List<byte[]> fi = new ArrayList<>();
//        files = imageRepository.findByImage(event);
//        System.out.println(files);
//
//        for (MultiPicture file : files) {
//            // fi.add(Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/")+file.getImage())));
//            fi.add(Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + file.getName())));
//        }
//
//        return fi;
//    }

    @GetMapping("/images/{id}")
    public ResponseEntity<List<MultiPicture>> getImagesByProduct(@PathVariable("id") Long id) throws Exception {
        ArrayList<MultiPicture> files = new ArrayList<MultiPicture>();
        Evenement event = iEventService.findEventById(id);
        files = imageRepository.findByImage(event);
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

    @GetMapping(path = "/getimage/{id}")
    public byte[] getPhotoProduct(@PathVariable("id") Long id) throws Exception {
        MultiPicture Article = imageRepository.findById(id).orElseThrow(() -> new Exception("File by id " + id + " was not found"));
        ;
        return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + Article.getName()));
    }

    //    @GetMapping(path = "/Imgarticle/{id}")
//    public byte[] getProductImage(@PathVariable("id") Long id) throws Exception {
//        Evenement Article = iEventService.findEventById(id);
//        return Files.readAllBytes(Paths.get(context.getRealPath("/Imagess/") + Article.getPicture()));
//    }
    @GetMapping(path = "/Imgarticle/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
        Evenement Article = iEventService.findEventById(id);
        return Files.readAllBytes(Paths.get("Imagess/null/" + Article.getPicture()));
    }

    @GetMapping(path = "/findbydate")
    public List<Evenement> findByPrice(@RequestParam("startDate") String startDateString,
                                       @RequestParam("endDate") String endDateString) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(startDateString);
        Date endDate = format.parse(endDateString);

            return iEventService.findByDate(startDate, endDate);

    }

    @GetMapping("/participant-count/{id}")
    public ResponseEntity<Long> getParticipantCountByEvent(@PathVariable("id") Long eventId) {
        Long participantCount = iEventService.getParticipantCountByEvent(eventId);
        return ResponseEntity.ok(participantCount);
    }

    @PostMapping("/addusers/{eventId}/{userId}")
    public ResponseEntity<Void> addUserToEvent(@PathVariable("eventId") Long eventId,
                                               @PathVariable("userId") Long userId) {
        iEventService.addUserToEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/removeusers/{eventId}/{userId}")
    public ResponseEntity<Void> removeUserFromEvent(@PathVariable("eventId") Long eventId,
                                                    @PathVariable("userId") Long userId) {
        iEventService.removeUserFromEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("addWtUser/{id}")
    public Evenement AssignUserToMission(@PathVariable("id") Long idMission, @RequestBody String nameU){
        return iEventService.AssignUserToEvent(idMission,nameU);
    }
}
