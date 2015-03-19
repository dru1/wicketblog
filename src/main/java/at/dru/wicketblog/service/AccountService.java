package at.dru.wicketblog.service;

import at.dru.wicketblog.model.Account;
import at.dru.wicketblog.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class AccountService extends AbstractEntityService<Account> {

    @Autowired
    private AccountRepository accountRepository;

    @Nullable
    public Account login(@Nonnull String login, @Nonnull String password) {
        for (Account account : accountRepository.findByLogin(login)) {
            if (account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }


    public void setupAdmin(@Nonnull String login, @Nonnull String password, boolean isAdmin) {
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
    public void saveEntity(@Nonnull Account entity) {
        accountRepository.save(entity);
    }

    @Override
    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }
}
