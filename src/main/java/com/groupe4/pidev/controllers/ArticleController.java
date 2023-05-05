package com.groupe4.pidev.controllers;

import com.groupe4.pidev.entities.*;
import com.groupe4.pidev.repositories.ArticleRepo;
import com.groupe4.pidev.repositories.UserRepo;
import com.groupe4.pidev.services.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private IArticleService articleService;
    private ArticleRepo articleRepo;
    private UserRepo userRepo;
   // private VoteRepo voteRepo;

    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam(value = "q") String searchTerm){
        return articleRepo.findByTitleContaining(searchTerm);
    }
 /*   @PostMapping("/{id}/vote")
    @Transactional
    public ResponseEntity<Void> voteArticle(@PathVariable Long id){
        Optional<Article> article = articleRepo.findById(id);
        if(article.isPresent()){
            articleRepo.addVote(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }*/
  /*  @DeleteMapping("/{id}/vote")
    @Transactional
    public ResponseEntity<Void> removeVoteArticle(@PathVariable Long id){
        Optional<Article> article = articleRepo.findById(id);
        if (article.isPresent()) {
            articleRepo.removeVote(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/
   /* @PostMapping("/{id}/votes")
    @Transactional
    public ResponseEntity<Article> vote(@PathVariable Long id, @RequestParam(value = "note") int note, @RequestParam(value = "userId") Long userId) {
        Article article = articleRepo.findArticleById(id);
        User user = userRepo.getById(userId);

        // Vérifier si l'utilisateur a déjà voté pour cet article
        List<Vote> existingVotes = article.getVotes().stream()
                .filter(vote -> vote.getUser().getID_user().equals(userId))
                .collect(Collectors.toList());
        if (!existingVotes.isEmpty()) {
            return ResponseEntity.badRequest().body(article);
        }
        // Enregistrer le vote
        Vote vote = new Vote(user, article, note);
        voteRepo.save(vote);

        // Recalculer le score de l'article
        List<Vote> votes = article.getVotes();
        int totalScore = votes.stream().mapToInt(Vote::getNote).sum();
        double averageScore = (double) totalScore / votes.size();
        article.getScore(averageScore);
        articleRepo.save(article);

        return ResponseEntity.ok(article);
    }*/





    @GetMapping("/getAll")
    public List<ArticleResponseBody> findArticles() { return articleService.findAllArticle();}
    @GetMapping("/get/{id}")
    public ArticleDetailsResponseBody findArticleById(@PathVariable Long id){return articleService.findArticleById(id);}
    @PostMapping(value = "/add" ,  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addArticle(@ModelAttribute ArticleRequestBody article, @RequestParam MultipartFile image) throws IOException {
        articleService.addArticle(article, image);
    }
    @PutMapping("/update/{id}")
    public void editArticle(@PathVariable Long id, @ModelAttribute ArticleRequestBody article, @RequestParam MultipartFile image)throws IOException{
        articleService.editArticle(id ,article, image);
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
