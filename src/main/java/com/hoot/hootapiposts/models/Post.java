package com.hoot.hootapiposts.models;

public class Post extends PostData {

    public int Id;

    public Post(int id, String name)
    {
        this.Id = id;
        this.Name = name;    
    }
    
}
