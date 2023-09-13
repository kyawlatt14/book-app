package net.kkl.bookapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookTitle;
    private String summary;
    private String coverImage;
    @Enumerated(EnumType.STRING)
    private Genres genres;
    private String author;
    private String pdfLink;
    private long createdAt;
    private long updatedAt;
    private boolean isDeleted;
    private int ratings;
//    @OneToMany(fetch =FetchType.EAGER ,mappedBy = "book",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
//    @JsonManagedReference
//    private List<Rating> ratings;
    @OneToMany(fetch =FetchType.EAGER ,mappedBy = "book",cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JsonManagedReference
    private List<Comment> comments;
//    public void add(Rating theRating){
//        if(ratings == null)
//            ratings = new ArrayList<>();
//        ratings.add(theRating);
//        theRating.setBook(this);
//    }
    public void add(Comment theComment){
        if(comments == null)
            comments = new ArrayList<>();
        comments.add(theComment);
        theComment.setBook(this);
    }
}
