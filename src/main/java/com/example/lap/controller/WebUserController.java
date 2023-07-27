package com.example.lap.controller;

import com.example.lap.dao.Basket;
import com.example.lap.dao.WebUser;
import com.example.lap.dao.WebUserRepository;
import com.example.lap.dto.AuthenticateDTO;
import com.example.lap.dto.ChangePasswordDTO;
import com.example.lap.dto.RegisterWebUserDTO;
import com.example.lap.dto.WebUserDTO;
import com.example.lap.security.CustomUserDetails;
import com.example.lap.service.WebUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * Api endpoint for user interaction
 */
@RestController
@RequestMapping(path="/api/user")
public class WebUserController {
    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private WebUserService webUserService;

    @GetMapping(path="/get")
    public @ResponseBody WebUserDTO getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        return webUserService.mapWebUserToWebUserDTO(user);
    }

    /**
     * Registers a user with the given credentials
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterWebUserDTO registerWebUserDTO) {
        if (webUserRepository.findUserByEmail(registerWebUserDTO.getEmail()) != null) {
            return new ResponseEntity<>("User already exists!", HttpStatus.OK);
        }

        if (registerWebUserDTO.getEmail() == null || registerWebUserDTO.getPassword() == null) {
            return new ResponseEntity<>("Missing Credentials!", HttpStatus.OK);
        }

        WebUser user = webUserService.mapRegisterWebUserDTOToWebUser(registerWebUserDTO);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        webUserRepository.saveAndFlush(user);
        return new ResponseEntity<>("Registration successfull!", HttpStatus.OK);
    }

    /**
     * Check given credentials and create a new session if correct
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest req, @RequestBody AuthenticateDTO authenticateDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticateDTO.getEmail(), authenticateDTO.getPassword()));

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        //make the session available for all subsequent requests and create one if not already there
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return new ResponseEntity<>("User signed-in successfully!", HttpStatus.OK);
    }

    /**
     * Invalidates session
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logOutUser(HttpServletRequest req, HttpServletResponse res) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(req, res, authentication);
        }

        return new ResponseEntity<>("User signed-out successfully!", HttpStatus.OK);
    }

    /**
     * Invalidates session and deletes user
     */
    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails, HttpServletRequest req, HttpServletResponse res) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(req, res, authentication);
        }

        user.setEmail("deleted" + user.getId());
        user.setBillingAddress(null);
        user.setDeliveryAddress(null);
        user.setTelephone(null);
        user.setDeleted(true);

        webUserRepository.saveAndFlush(user);

        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody RegisterWebUserDTO registerWebUserDTO) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());

        user.setBillingAddress(registerWebUserDTO.getBillingAddress());
        user.setDeliveryAddress(registerWebUserDTO.getDeliveryAddress());
        user.setTelephone(registerWebUserDTO.getTelephone());

        webUserRepository.saveAndFlush(user);

        return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
    }

    @PostMapping("/update/password")
    public ResponseEntity<?> updateUserPassword(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody ChangePasswordDTO changePasswordDTO) {
        WebUser user = webUserRepository.findUserByEmail(userDetails.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            String newEncodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
            user.setPassword(newEncodedPassword);
            webUserRepository.saveAndFlush(user);

            return new ResponseEntity<>("User password updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Incorrect password!", HttpStatus.OK);
        }
    }
}