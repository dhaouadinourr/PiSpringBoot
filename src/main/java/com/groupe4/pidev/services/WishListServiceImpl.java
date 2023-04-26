package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.entities.Produit;
import com.groupe4.pidev.repositories.ProduitRepo;
import com.groupe4.pidev.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class WishListServiceImpl {

    @Autowired
    private IUserService userService;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProduitRepo produitRepo;

    private User findClientById(Long id) throws Exception {
        return userService.findById(id);
    }

    private Produit findProduitById(Long id) throws Exception {
        return produitService.findProduitById(id);
    }

    public Set<Produit> findAll() throws Exception {
        //  UserPrincipal user = UserService.clientAuthenticated();
        //User cli = findClientById(user.getId());

        //return cli.getProductsWished();
        return  null;
    }

    public void markProduitAsWished(Long produitId) throws Exception {
        Produit produit = findProduitById(produitId);
        //  UserPrincipal user = UserService.clientAuthenticated();
        // User client = findClientById(user.getId());

        //   if (client.getProductsWished().contains(product)) {
        throw new Exception();
    }

    // client.getProductsWished().add(product);
    // product.getWhoWhishesThisProduct().add(client);

    // clientRepo.save(client);
    // productRepo.save(product);
    //}

    @Transactional
    public void delete(Long produitId) {
        //  UserPrincipal user = UserService.clientAuthenticated();

        //productRepo.removeFromClientWishlist(productId, user.getId());
    }

    //@Transactional
    // public void removeProductFromWishlistWhenIsSold(Long productId) {
    //   productRepo.removeFromWishListWhenIsSold(productId);
    //}
}
