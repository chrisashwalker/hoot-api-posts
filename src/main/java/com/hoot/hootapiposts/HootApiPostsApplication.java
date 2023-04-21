package com.hoot.hootapiposts;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.hoot.hootapiposts.data.PostsDataStore;
import com.hoot.hootapiposts.models.Post;

@SpringBootApplication
@RestController
public class HootApiPostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HootApiPostsApplication.class, args);
	}

	@GetMapping("/posts")
    public List<Post> getPosts() {
		return PostsDataStore.Posts.findAll();
    }

	@GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id) {
		return PostsDataStore.Posts.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post newPost) {
        try {
            if (newPost.getId() != 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        catch (ResponseStatusException r) {
            throw r;
        }
        catch(Exception e){
            if (newPost.getName().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
    
            Long maxId = PostsDataStore.Posts.getMaxPostId();
            newPost.setId(++maxId);
            return PostsDataStore.Posts.save(newPost);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/posts")
    public ResponseEntity<String> replacePost(@RequestBody Post newPost) {
        try {
            PostsDataStore.Posts.findById(newPost.getId())
            .ifPresentOrElse((post) -> {
                post.setId(newPost.getId());
                post.setName(newPost.getName());
                PostsDataStore.Posts.save(post);
            }, () -> { throw new RuntimeException(); });
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Replaced post.", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        try {
		    PostsDataStore.Posts.findById(id)
            .ifPresentOrElse(
                (post) -> { PostsDataStore.Posts.delete(post); },
                () -> { throw new RuntimeException(); }
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted post.", HttpStatus.OK);
    }

/*

@app.route("/teams/<int:id>", methods=['PATCH'])
def update_team(id):
    patch = JsonPatch.from_string(request.get_data(True,True,False))
    
    for t in teams:
        if t.id == id:
            patched_dict = patch.apply(t.__dict__)
            t.rebuild(patched_dict)
            return jsonify({}), 204
          
    return jsonify({"message": "Bad request"}), 400    

*/

}
