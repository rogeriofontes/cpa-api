package br.com.unipac.cpa.web.convert.request;

import br.com.unipac.cpa.model.domain.Role;
import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRoleRepository;
import br.com.unipac.cpa.model.service.PasswordCrypto;
import br.com.unipac.cpa.web.dto.request.AccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class AccountRequestConverter implements Converter<AccountRequest, User> {

    @Autowired
    private PasswordCrypto passwordCrypto;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User convert(AccountRequest accountRequest) {
        String cryptoPassword = passwordCrypto.encrypt(accountRequest.getPassword());

        User user = User.builder()
                .email(accountRequest.getEmail())
                .password(cryptoPassword)
                .build();

        Set<Role> roles = getRoles(accountRequest.getUserType());
        user.setRoles(roles);
        return user;
    }

    private Set<Role> getRoles(String userTypeRole) {
        Set<Role> roles = new HashSet<>();

        Role role = userRoleRepository.findByRoleName(userTypeRole);
        roles.add(role);

        return roles;
    }
}
