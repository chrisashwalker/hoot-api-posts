package com.hoot.hootapiposts.data;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import com.hoot.hootapiposts.models.Post;

public class PostsDataStore {
    
    public static PostsDataStore Current = new PostsDataStore();
    public List<Post> Posts = Collections.emptyList();

    public PostsDataStore()
    {
        IntStream.range(1, 5).boxed().forEach(i -> Posts.add(new Post(i, "Post" + i)));
    }

}
