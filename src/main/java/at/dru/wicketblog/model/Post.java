package at.dru.wicketblog.model;

import at.dru.wicketblog.service.EntityPropertyDesc;
import at.dru.wicketblog.service.EntityPropertyType;
import jakarta.persistence.*;

@Entity
public class Post extends AbstractEntity {

    private String title;

    private String content;

    private Account author;

    private PostCategory postCategory;

    @Basic(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.SHORT_TEXT, optional = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(nullable = false, length = 100000)
    @EntityPropertyDesc(type = EntityPropertyType.LONG_TEXT, optional = true)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.MANY_TO_ONE, optional = false)
    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    @ManyToOne(optional = false)
    @EntityPropertyDesc(type = EntityPropertyType.MANY_TO_ONE, optional = false)
    public PostCategory getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(PostCategory postCategory) {
        this.postCategory = postCategory;
    }
}
