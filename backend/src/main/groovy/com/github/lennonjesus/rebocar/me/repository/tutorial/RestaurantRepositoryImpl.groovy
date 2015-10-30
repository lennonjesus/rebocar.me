package com.github.lennonjesus.rebocar.me.repository.tutorial

import javax.annotation.Resource

//import com.mongodb.client.FindIterable
//import com.mongodb.client.MongoDatabase
//import org.bson.Document
/**
 * Created by felipe on 10/29/15.
 */
@Resource
class RestaurantRepositoryImpl implements RestaurantRepository {

    @Override
    List findAll() {
//        MongoClient client = new MongoClient()
//        MongoDatabase db = client.getDatabase("test")
//        FindIterable<Document> iterable = db.getCollection("restaurants").find()
//
//        return iterable.first()

//        OBS.: as classes MongoClient, MongoDatabase e Document estao contidas em org.mongodb:mongo-java-driver,
//          o qual nao eh necessario quando se usa spring-boot-starter-data-mongodb
    }
}
