package at.dru.wicketblog.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findByLogin(String login);

}
