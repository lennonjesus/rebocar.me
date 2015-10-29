package com.github.lennonjesus.rebocar.me.repository

import com.mongodb.MongoClient
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoDatabase
import org.bson.Document

import javax.annotation.Resource

/**
 * Created by felipe on 10/29/15.
 */
@Resource
class RestaurantRepositoryImpl implements RestaurantRepository {

    @Override
    List findAll() {
        MongoClient client = new MongoClient()
        MongoDatabase db = client.getDatabase("test")
        FindIterable<Document> iterable = db.getCollection("restaurants").find()

        return iterable.first()
    }
}
