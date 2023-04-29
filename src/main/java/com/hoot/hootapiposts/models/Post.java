package com.hoot.hootapiposts.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posts")
public class Post {

    private @Id @GeneratedValue Long id;
    private @Column(name = "Name") String name = "";   

    public Post() {}

    public Post(String name)
    {
        this.setName(name);    
    }

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
