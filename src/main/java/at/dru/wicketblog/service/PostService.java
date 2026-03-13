package at.dru.wicketblog.service;

import at.dru.wicketblog.model.Post;
import at.dru.wicketblog.model.PostRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService extends AbstractEntityService<Post> {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Class<Post> getEntityType() {
        return Post.class;
    }

    @Override
    public void saveEntity(Post entity) {
        postRepository.save(entity);
    }

    @Override
    public @Nullable Post findByEntityId(Long entityId) {
        return postRepository.findById(entityId).orElse(null);
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

}
