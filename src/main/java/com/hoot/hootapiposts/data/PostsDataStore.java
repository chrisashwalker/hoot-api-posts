package com.hoot.hootapiposts.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Collections;
import com.hoot.hootapiposts.models.Post;

@Configuration
public class PostsDataStore {
    
    public static PostRepository Posts;

    PostsDataStore(PostRepository repository) {
        Posts = repository;
    }

    @Bean
    CommandLineRunner initDatabase(PostRepository repository) {
        if (Posts.count() < 1) {
            return (args) -> {
                var posts = new ArrayList<Post>();
                Collections.addAll(posts,
                    new Post("Secretary"),
                    new Post("Physicist"),
                    new Post("Plumber"),
                    new Post("Architect"),
                    new Post("Chef"),
                    new Post("Historian"),
                    new Post("Developer"),
                    new Post("Mechanic"),
                    new Post("Librarian"),
                    new Post("Scientist"),
                    new Post("Carpenter"),
                    new Post("Zoologist"),
                    new Post("Statistician"),
                    new Post("Director"),
                    new Post("Referee"),
                    new Post("Mathematician"),
                    new Post("Epidemiologist"),
                    new Post("Chemist"),
                    new Post("Photographer"),
                    new Post("Psychologist")
                    );
                Posts.saveAll(posts);
            };
        }
        return (args) -> {
            System.out.println("Post data found.");
        };
    }

}
