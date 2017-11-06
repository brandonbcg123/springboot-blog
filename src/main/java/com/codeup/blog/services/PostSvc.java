package com.codeup.blog.services;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postSvc")
public class PostSvc {
    private List<Post> posts = new ArrayList<>();

    public PostSvc() {
        createDummyPosts();
    }

    public List<Post> findAllPosts(){
        return posts;
    }

    public Post findOnePost(long id){
        return posts.get((int) (id - 1));
    }

    public Post savePost(Post post){
        post.setId((long) (posts.size() + 1));
        posts.add(post);
        return post;
    }


private void createDummyPosts() {

    this.savePost(new Post("My Coding Experience", "Learning 8 different languages is tough regardless if they are languages that we speak or languages that we code. Learning code is something beautiful, but it can be frustrating at the same time. Overall there is nothing more gratifying than solving a problem that took hours, days, or even weeks to solve! Once I solve a problem I feel the world is mine!!!"));

    Post postTwo = new Post("Kawhi Leonard's Injury", "I miss Kawhi Leonard!!! Please get healthy and come back as soon as possible amigo!!! The city of San Antonio need you!!!");

    Post postThree = new Post("Music Is Awesome", "I love all sorts of music, whether it is Rock, Rap, R&B, etc. The way the sounds of the instruments come together to make one beautiful sound amazes me! That's is why I decided to try to play an instrument my self: Drums!!! I'am now the best drummer in the world!");

    Post postFour = new Post("New Dancing Moves", "I was watching an old Michael Jackson music video the other day, and I saw him bust out the moonwalk! All of a sudden I'm in awe wondering how is it possible that this man can walk like he is on the moon without actually being on the moon?!?!?! Is gravity real? Am I just a part of the matrix?!?!?! Sorry got off topic...The point of the matter is you 'Can't Touch This!'");

//    postSvc.savePost(new Post("Music Is Awesome", "I love all sorts of music, whether it is Rock, Rap, R&B, etc. The way the sounds of the instruments come together to make one beautiful sound amazes me! That's is why I decided to try to play an instrument my self: Drums!!! I'am now the best drummer in the world!"));


    this.savePost(postTwo);
    this.savePost(postThree);
    this.savePost(postFour);
    }

}
