package at.dru.wicketblog.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by dru on 3/1/15.
 */
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    List<Post> findByAuthor(@Nonnull Account author);

    Page<Post> findAllByOrderByModifiedDesc(@Nonnull Pageable pageRequest);
}
