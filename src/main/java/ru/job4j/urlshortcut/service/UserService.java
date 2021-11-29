package ru.job4j.urlshortcut.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Role;
import ru.job4j.urlshortcut.model.User;
import ru.job4j.urlshortcut.repository.RoleRepository;
import ru.job4j.urlshortcut.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public Role findByRole(String role) {
        return roleRepository.findByAuthority(role);
    }
}
