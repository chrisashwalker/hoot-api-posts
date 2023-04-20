package com.hoot.hootapiposts.models;

public class PostBuilder extends PostData {

    public Post Build(int id)
    {
        return new Post(id, this.Name);
    }
    
}
