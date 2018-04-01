package com.globomart.product.controller;

import com.globomart.product.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void get_Products__shouldReturn__AllProducts(){

        given().
                port(port).
        when().
                get("/api/products").
        then().
                statusCode(200).
                contentType(JSON);

    }

    @Test
    public void get_Products_with_TypeMobiles__shouldReturn__AllProducts_having_Type_Mobiles(){

        given().
                port(port).
        when().
                get("/api/products?type=Mobiles").
        then().
                statusCode(200);

    }

    @Test
    public void get_Products_10001__shouldReturn__Product_having_Id_10001(){

        given().
                port(port).
        when().
                get("/api/products/10001").
        then().
                statusCode(200);


    }

    @Test
    public void get_Products_10009__shouldReturn__Product_Not_Found(){

        given().
                port(port).
        when().
                get("/api/products/10009").
        then().
                statusCode(404);

    }

    @Test
    public void post_Products__shouldAdd__NewProduct(){

        Product product = new Product(10003L, "iMac", "PC");

        given().
                port(port).
                contentType("application/json").
                body(product).
        when().
                post("/api/products").
        then().
                statusCode(201);

    }

    @Test
    public void delete_Products_10001__shouldDelete__Product_with_Id_10001(){

        given().
                port(port).
        when().
                delete("/api/products/10001").
        then().
                statusCode(200);

    }

}