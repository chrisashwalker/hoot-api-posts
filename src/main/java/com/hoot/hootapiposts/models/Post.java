package com.hoot.hootapiposts.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Post {

    private @Id Long id;
    private String name = "";   

    public Post() {}

    public Post(Long id, String name)
    {
        this.setId(id);
        this.setName(name);    
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
}
