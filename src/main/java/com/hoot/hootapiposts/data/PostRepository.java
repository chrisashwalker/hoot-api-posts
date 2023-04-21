package com.hoot.hootapiposts.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hoot.hootapiposts.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    @Query("Select MAX(id) FROM Post")
    public Long getMaxPostId();
    
}
