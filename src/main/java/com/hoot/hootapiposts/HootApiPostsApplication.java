package com.hoot.hootapiposts;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Post addPost(@RequestBody Post newPost) {
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

/*

@app.route("/teams", methods=['PUT'])
@expects_json(replace_schema)
def replace_team():
    replacement = request.get_json()
    
    for t in teams:
        if t.id == replacement.get("id", None):
            t.name = replacement.get("name", None)
            t.parent = replacement.get("parent", None)
            return jsonify({}), 204
          
    return jsonify({"message": "Bad request"}), 400

@app.route("/teams/<int:id>", methods=['PATCH'])
def update_team(id):
    patch = JsonPatch.from_string(request.get_data(True,True,False))
    
    for t in teams:
        if t.id == id:
            patched_dict = patch.apply(t.__dict__)
            t.rebuild(patched_dict)
            return jsonify({}), 204
          
    return jsonify({"message": "Bad request"}), 400    

@app.route("/teams/<int:id>", methods=['DELETE'])
def delete_team(id):
    for t in teams:
        if t.id == id:
            teams.remove(t)
            return jsonify({}), 204
          
    return jsonify({"message": "Team not found"}), 404
*/

}
