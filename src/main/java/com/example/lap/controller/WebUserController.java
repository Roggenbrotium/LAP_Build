package com.example.lap.controller;

import com.example.lap.dao.Basket;
import com.example.lap.dao.WebUser;
import com.example.lap.dao.WebUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

/**
 * Api endpoint for user interaction
 */
@RestController
@RequestMapping(path="/user")
public class WebUserController {
    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(path="/get")
    public @ResponseBody WebUser getUserById(@RequestParam Long id) {
        // This returns a JSON or XML with the users
        return webUserRepository.findUserById(id);
    }

    /**
     * Registers a user with the given credentials
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String email, @RequestParam(required = false) String telephone,
                                  @RequestParam(required = false) String billingAddress, @RequestParam(required = false) String deliveryAddress,
                                  @RequestParam String password) {
        Basket basket = new Basket();
        WebUser user = new WebUser(email, telephone, billingAddress, deliveryAddress, password, basket);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        if (webUserRepository.findUserByEmail(email) != null) {
            return new ResponseEntity<>("User already exists!", HttpStatus.OK);
        } else  {
            webUserRepository.saveAndFlush(user);
            return new ResponseEntity<>("Registration successfull!", HttpStatus.OK);
        }
    }

    /**
     * Check given credentials and create a new session if correct
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(HttpServletRequest req, @RequestParam String email, @RequestParam String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

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
}