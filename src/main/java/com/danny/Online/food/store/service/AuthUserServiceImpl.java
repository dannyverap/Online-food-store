package com.danny.Online.food.store.service;

import com.danny.Online.food.store.config.JwtProvider;
import com.danny.Online.food.store.model.Cart;
import com.danny.Online.food.store.model.USER_ROLE;
import com.danny.Online.food.store.model.User;
import com.danny.Online.food.store.repository.CartRepository;
import com.danny.Online.food.store.repository.UserRepository;
import com.danny.Online.food.store.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthUserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CartRepository cartRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    @Transactional
    public AuthResponse SignUp(User user) throws Exception {
        validateEmail(user.getEmail());

        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            throw new BadCredentialsException("Email already exists");
        }

        User newUser = createUser(user);
        createCart(newUser);

        String jwt = generateJwtToken(user);

        return buildAuthResponse(newUser.getRole(), jwt, "Sign-up successful");
    }

    @Override
    public AuthResponse SignIn(UserDetails userDetails, String password) throws Exception {
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        String jwt = jwtProvider.generateToken(authentication);
         String role = userDetails.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .findFirst()
                                .orElse(null);

        return buildAuthResponse(USER_ROLE.valueOf(role), jwt, "Sign-in successful");
    }


    private void validateEmail(String email) throws Exception {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new Exception("Invalid email format");
        }
    }

    private User createUser(User user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setRole(user.getRole());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    private void createCart(User user) {
        Cart cart = new Cart();
        cart.setCustomer(user);
        cartRepository.save(cart);
    }

    private String generateJwtToken(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        return jwtProvider.generateToken(authentication);
    }

    private AuthResponse buildAuthResponse(USER_ROLE role, String jwt, String message) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage(message);
        authResponse.setRole(role);
        return authResponse;
    }
}
