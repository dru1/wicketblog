package at.dru.wicketblog.model;

import org.springframework.data.repository.CrudRepository;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by dru on 3/1/15.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByLogin(@Nonnull String login);

}
