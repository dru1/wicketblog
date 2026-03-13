package at.dru.wicketblog.service;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends AbstractEntityService<Account> {

    @Autowired
    private AccountRepository accountRepository;

    public @Nullable Account login(String login, String password) {
        for (Account account : accountRepository.findByLogin(login)) {
            if (account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }

    public void setupAdmin(String login, String password, boolean isAdmin) {
        Account acc = new Account();

        acc.setLogin(login);
        acc.setPassword(password);
        acc.setAdminUser(isAdmin);

        accountRepository.save(acc);
    }

    @Override
    public Class<Account> getEntityType() {
        return Account.class;
    }

    @Override
    public void saveEntity(Account entity) {
        accountRepository.save(entity);
    }

    @Override
    public @Nullable Account findByEntityId(Long entityId) {
        return accountRepository.findById(entityId).orElse(null);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

}
