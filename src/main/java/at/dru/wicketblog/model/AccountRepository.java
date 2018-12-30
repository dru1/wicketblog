package at.dru.wicketblog.model;

import org.springframework.data.repository.CrudRepository;

import javax.annotation.Nonnull;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByLogin(@Nonnull String login);

}
