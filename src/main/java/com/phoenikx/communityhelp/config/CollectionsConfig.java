package com.phoenikx.communityhelp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class CollectionsConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {
//        mongoTemplate.indexOps("collectionName") // collection name string or .class
//                .ensureIndex(
//                        new Index().on("name", Sort.Direction.ASC)
//                );
    }
}
