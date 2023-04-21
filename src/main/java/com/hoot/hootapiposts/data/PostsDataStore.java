package com.hoot.hootapiposts.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.stream.LongStream;
import com.hoot.hootapiposts.models.Post;

@Configuration
public class PostsDataStore {
    
    public static PostRepository Posts;

    PostsDataStore(PostRepository repository) {
        Posts = repository;
    }

    @Bean
    CommandLineRunner initDatabase(PostRepository repository) {
        return (args) -> {
            LongStream.range(1, 5).boxed().forEach(i -> repository.save(new Post(i, "Post" + Long.toString(i))));
        };
    }

}
