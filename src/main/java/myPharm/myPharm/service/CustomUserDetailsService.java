package myPharm.myPharm.service;

import myPharm.myPharm.domain.entity.CustomUserDetails;
import myPharm.myPharm.domain.entity.UserEntity;
import myPharm.myPharm.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("This method is not supported. Use loadUserByOuthId instead.");
    }

    public UserDetails loadUserByOuthId(Long outhId) throws UsernameNotFoundException {
        UserEntity optionalUser = userRepository.findByOuthId(outhId);
        UserEntity member = optionalUser;
        return new CustomUserDetails(member);
    }
}