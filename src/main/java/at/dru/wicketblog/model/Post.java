package at.dru.wicketblog.model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
public class Post extends DefaultEntity {

    private String title;

    private String content;

    private Account author;

    private PostCategory postCategory;

    @Nonnull
    @Basic(optional = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nonnull
    @Lob
    @Column(nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(@Nonnull String content) {
        this.content = content;
    }

    @Nonnull
    @ManyToOne(optional = false)
    public Account getAuthor() {
        return author;
    }

    public void setAuthor(@Nonnull Account author) {
        this.author = author;
    }

    @Nonnull
    @ManyToOne(optional = false)
    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(@Nonnull PostCategory postCategory) {
        this.postCategory = postCategory;
    }
}
