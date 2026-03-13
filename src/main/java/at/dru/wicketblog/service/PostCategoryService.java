package at.dru.wicketblog.service;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.model.PostCategoryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCategoryService extends AbstractEntityService<PostCategory> {

    @Autowired
    private PostCategoryRepository postCategoryRepository;

    @Override
    public Class<PostCategory> getEntityType() {
        return PostCategory.class;
    }

    @Override
    public void saveEntity(PostCategory entity) {
        postCategoryRepository.save(entity);
    }

    @Override
    public @Nullable PostCategory findByEntityId(Long entityId) {
        return postCategoryRepository.findById(entityId).orElse(null);
    }

    @Override
    public Iterable<PostCategory> findAll() {
        return postCategoryRepository.findAll();
    }

}
