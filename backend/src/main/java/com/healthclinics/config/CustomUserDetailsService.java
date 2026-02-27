package com.healthclinics.config;

import com.healthclinics.entity.User;
import com.healthclinics.entity.NhanVien;
import com.healthclinics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailWithNhanVien(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        // Add role from user
        if (user.getRole() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));
        }
        
        // Add group role from NhanVien
        NhanVien nhanVien = user.getNhanVien();
        if (nhanVien != null && nhanVien.getNhomNguoiDung() != null) {
            String maNhom = nhanVien.getNhomNguoiDung().getMaNhom();
            if (maNhom != null) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + maNhom.toUpperCase()));
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
