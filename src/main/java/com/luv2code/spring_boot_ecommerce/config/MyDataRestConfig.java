package com.luv2code.spring_boot_ecommerce.config;

import com.luv2code.spring_boot_ecommerce.entity.Country;
import com.luv2code.spring_boot_ecommerce.entity.Product;
import com.luv2code.spring_boot_ecommerce.entity.ProductCategory;
import com.luv2code.spring_boot_ecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{


    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportActions ={HttpMethod.DELETE,HttpMethod.POST,HttpMethod.PUT};
        //disable HTTP methods for Product: PUT,POST and DELETE
        disableHttpMethods ( Product.class ,config, theUnsupportActions );
        disableHttpMethods ( ProductCategory.class,config, theUnsupportActions );
        disableHttpMethods ( Country.class,config, theUnsupportActions );
        disableHttpMethods ( State.class,config, theUnsupportActions );
        //disable HTTP methods for ProductCategory: PUT,POST and DELETE
        //call an internal helper method
        exposeIds(config);
    }

    private static void disableHttpMethods (Class<?> clas, RepositoryRestConfiguration config, HttpMethod[] theUnsupportActions) {
        config.getExposureConfiguration ()
                .forDomainType ( clas)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable ( theUnsupportActions ))
                .withCollectionExposure ( (metdata, httpMethods) -> httpMethods.disable ( theUnsupportActions ) );
    }

    private void exposeIds (RepositoryRestConfiguration config) {
        //expose entity ids

        //get list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel ().getEntities ();

        //create an array of entity types
        List<Class> entityClasses = new ArrayList<> ();
        for ( EntityType entityTypes: entities) {
            entityClasses.add ( entityTypes.getJavaType ());
        }
        Class [] domainTypes = entityClasses.toArray (new Class[0]);

        config.exposeIdsFor ( domainTypes );

    }
}
