package vn.iotstar.baitap18_11.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.iotstar.baitap18_11.config.UserInfoUserDetails;
import vn.iotstar.baitap18_11.entity.UserInfo;
import vn.iotstar.baitap18_11.repository.UserInfoRepository;

import java.util.Optional;

public class UserInfoService implements UserDetailsService {
    @Autowired
    UserInfoRepository repository;

    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.repository = userInfoRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails:: new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " +  username));
    }
}
