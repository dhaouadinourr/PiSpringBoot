package com.groupe4.pidev.repositories;

import com.groupe4.pidev.entities.Produit;
import com.groupe4.pidev.entities.ProduitCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit,Long> {
    public List<Produit> findByProduitCategory(ProduitCategory category);


    // @Modifying
    // @Query(value="delete from wishlist where product_id = :id",nativeQuery = true)
    //void removeFromWishListWhenIsSold(@Param("id") Long id);

    //@Modifying
    //@Query(value="delete from wishlist where product_id = :productId and client_id = :clientId",nativeQuery = true)
    //void removeFromClientWishlist(@Param("productId") Long productId, @Param("clientId") Long clientId);
}
