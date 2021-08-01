package at.dru.wicketblog.service;

import at.dru.wicketblog.model.PostCategory;
import at.dru.wicketblog.model.PostCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class PostCategoryService extends AbstractEntityService<PostCategory> {

    @Autowired
    private PostCategoryRepository postCategoryRepository;

    @Nonnull
    @Override
    public Class<PostCategory> getEntityType() {
        return PostCategory.class;
    }

    @Override
    public void saveEntity(@Nonnull PostCategory entity) {
        postCategoryRepository.save(entity);
    }

    @Nullable
    @Override
    public PostCategory findByEntityId(@Nonnull Long entityId) {
        return postCategoryRepository.findById(entityId).orElse(null);
    }

    @Nonnull
    @Override
    public Iterable<PostCategory> findAll() {
        return postCategoryRepository.findAll();
    }

}
