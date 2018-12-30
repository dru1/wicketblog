package at.dru.wicketblog.model;

import at.dru.wicketblog.service.EntityPropertyDesc;
import at.dru.wicketblog.service.EntityPropertyType;

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
    @EntityPropertyDesc(type = EntityPropertyType.SHORT_TEXT, optional = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nonnull
    @Lob
    @Column(nullable = false, length = 100000)
    @EntityPropertyDesc(type = EntityPropertyType.LONG_TEXT, optional = true)
    public String getContent() {
        return content;
    }

    public void setContent(@Nonnull String content) {
        this.content = content;
    }

    @Nonnull
    @ManyToOne(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.MANY_TO_ONE, optional = false)
    public Account getAuthor() {
        return author;
    }

    public void setAuthor(@Nonnull Account author) {
        this.author = author;
    }

    @Nonnull
    @ManyToOne(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.MANY_TO_ONE, optional = false)
    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(@Nonnull PostCategory postCategory) {
        this.postCategory = postCategory;
    }
}
