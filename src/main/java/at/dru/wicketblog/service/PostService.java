package at.dru.wicketblog.service;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class PostService extends AbstractEntityService<Post> {

    @Autowired
    private PostRepository postRepository;

    @Nonnull
    @Override
    public Class<Post> getEntityType() {
        return Post.class;
    }

    @Override
    public void saveEntity(@Nonnull Post entity) {
        postRepository.save(entity);
    }

    @Nullable
    @Override
    public Post findByEntityId(@Nonnull Long entityId) {
        return postRepository.findById(entityId).orElse(null);
    }

    @Nonnull
    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

}
