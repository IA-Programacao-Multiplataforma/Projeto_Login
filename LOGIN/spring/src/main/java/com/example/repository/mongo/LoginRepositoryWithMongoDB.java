package com.example.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.repository.orm.LoginOrmMongo;

public interface LoginRepositoryWithMongoDB extends MongoRepository<LoginOrmMongo, String> {
    LoginOrmMongo findByUsername(String username);
}