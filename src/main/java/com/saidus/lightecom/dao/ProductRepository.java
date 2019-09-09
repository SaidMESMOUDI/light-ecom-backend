package com.saidus.lightecom.dao;

import com.saidus.lightecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProductRepository extends JpaRepository<Product, Long> {

    @RestResource(path = "/selectedProducts")
    public List<Product> findBySelectedIsTrue();

    @RestResource(path = "/productsByKeyword")
    public List<Product> findByNameContains(String kw);
    // public List<Product> findByNameContains(@Param("kw") String kw);

// on peut aussi utilisé des requetes HQL :
    /*
    @RestResource(path = "/productsByKeyword")
    @Query("select p from Product p where p.name like :x")
    public List<Product> chercherParMotCle(@Param("x") String mc);
    */

    @RestResource(path = "/promotionalProducts")
    public List<Product> findByPromotionIsTrue();

    @RestResource(path = "/availableProducts")
    public List<Product> findByAvailableIsTrue();


}
