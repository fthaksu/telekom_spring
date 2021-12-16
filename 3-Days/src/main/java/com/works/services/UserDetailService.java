package com.works.services;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {

    final UserRepository uRepo;
    public UserDetailService(UserRepository uRepo) {
        this.uRepo = uRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = null;
        Optional<User> oUser = uRepo.findByEmailEqualsIgnoreCase(username);
        if (oUser.isPresent() ) {
            User u = oUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPassword(), u.isEnabled(), u.isTokenExpired(), true, true, fncRoles(u.getRoles()) );
        }else {
            throw new UsernameNotFoundException("Kullanıcı adı yada şifre hatalı");
        }

        return userDetails;
    }

    public List<GrantedAuthority> fncRoles(List<Role> roles) {
        List<GrantedAuthority> cls = new ArrayList<>();
        roles.forEach( item -> {
            cls.add( new SimpleGrantedAuthority( item.getName() ) );
        });
        return cls;
    }


    public User register( User user) {
        user.setPassword( encoder().encode(user.getPassword()) );
        return uRepo.save(user);
    }


    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
