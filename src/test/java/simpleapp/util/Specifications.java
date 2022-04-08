package simpleapp.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder()
        .setBaseUri(url)
        .setContentType(ContentType.JSON)
        .build();
    }

    public static ResponseSpecification responseSpecificationOk200(){
        return new  ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
    }

    public static ResponseSpecification responseSpecUnicue(Integer status){
        return new  ResponseSpecBuilder()
            .expectStatusCode(status)
            .build();
    }

    public static ResponseSpecification responseSpecification400(){
        return new  ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
    
}
