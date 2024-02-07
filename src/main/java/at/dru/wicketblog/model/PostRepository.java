package at.dru.wicketblog.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Nonnull;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByAuthor(@Nonnull Account author);

    Page<Post> findAllByOrderByModifiedDesc(@Nonnull Pageable pageRequest);

}
