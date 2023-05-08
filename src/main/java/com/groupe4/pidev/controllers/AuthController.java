package com.groupe4.pidev.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.groupe4.pidev.entities.ERole;
import com.groupe4.pidev.entities.Role;
import com.groupe4.pidev.entities.User;
import com.groupe4.pidev.payload.request.LoginRequest;
import com.groupe4.pidev.payload.request.SignupRequest;
import com.groupe4.pidev.payload.response.MessageResponse;
import com.groupe4.pidev.payload.response.UserInfoResponse;
import com.groupe4.pidev.repositories.RoleRepository;
import com.groupe4.pidev.repositories.UserRepo;
import com.groupe4.pidev.security.jwt.JwtUtils;
import com.groupe4.pidev.security.services.UserDetailsImpl;
import com.groupe4.pidev.security.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //log.info("logged in");

        return ResponseEntity.ok(new UserInfoResponse(jwt,

                userDetails.getId(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getUsername(),
                userDetails.getDateOfBirth(),
                userDetails.getEmail(),
                userDetails.getContactNumber(),
                roles));
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        System.out.println(signUpRequest.getUsername());
        // Create new user's account
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getEmail(),
                signUpRequest.getContactNumber(),
                signUpRequest.getCreatedAt(),
                encoder.encode(signUpRequest.getPassword()));


        //    Set<Role> strRoles = signUpRequest.getRole();

      /*  Set<Role> roles = new HashSet<>();
        for (Role role :strRoles){
            if(role.getName().equals("ROLE_USER")){
                Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
                roles.add(userRole);

            }
            else if (role.getName().equals("ROLE_ADMIN")){
                Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN).get();


                roles.add(userRole);
            }
            else {
                Role userRole = roleRepository.findByName(ERole.ROLE_CLUB).get();


                roles.add(userRole);

            }


        }

*/

        Set<Role> RoleList = signUpRequest.getRole();
        System.out.println(signUpRequest);
        for (Role role:RoleList){
            // roleRepository.save(role);
            if(role.getName().equals(ERole.ROLE_USER)){
                Set<Role>  userRole = roleRepository.getBynaame(ERole.ROLE_USER);
                user.setRole(userRole);

            }
            else if (role.getName().equals(ERole.ROLE_ADMIN)){
                Set<Role>  userRole = roleRepository.getBynaame(ERole.ROLE_ADMIN);
                user.setRole(userRole);
            }
            else {
                Set<Role>  userRole = roleRepository.getBynaame(ERole.ROLE_CLUB);
                user.setRole(userRole);
            }


        }
        System.out.println(signUpRequest.getRole());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        // log.info("logged out");
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @PostMapping("/forgotpassword")
    public String processForgotPassword(@RequestParam String email) {
        String token = RandomString.make(45);
        try {
            userService.updateResetPasswordToken(token,email);

            String resetPasswordlink="http://localhost:4200/admin/resetpassword?token="+token;
            sendEmail(email, resetPasswordlink);
            return "Email sent";
        } catch (UsernameNotFoundException | UnsupportedEncodingException |
                 javax.mail.MessagingException ex){

        }
        return "dfghj";

    }
    private void sendEmail(String email, String resetPasswordlink) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("jasser.boukraya@esprit.tn","Admin Jasser");
        helper.setTo(email);
        String subject="Heres the link to reset your password";
        String content="<p>Hello,</p>" +
                "<p> You have requested to reset your password.</p>" +
                "<p> Click the link below to change the password:</p>"  +
                "<p><b><a href=\""+resetPasswordlink +"\">Change my password</a><b></p>";
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);

    }

    @GetMapping("/resetPassword")
    public String showResetPasswordForm(@Param(value="token") String token, Model model) {

        User user = userService.getByToken(token);
        if (user == null){
            model.addAttribute("title","Reset password");
            model.addAttribute("message","Invalid token");
            return "reset_password_form";
        }
        model.addAttribute("token",token);
        model.addAttribute("pageTitle","Reset your password");

        return "reset_password_form";
    }
    @GetMapping("/getUserByToken")
    public User get(@RequestParam String token){
        return userService.getByToken(token);
    }

    @PostMapping("/resetPassword")
    public String processResetPassword(@RequestBody Map<String, String> request, Model model) {

        String token = request.get("token");
        String password = request.get("password");
        System.out.println(token);
        System.out.println(password);

        User user = userService.getByToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "reset_password_formm";
        } else {
            userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
            return "hh";
        }


        //  return "reset_password_form";

    }
    @GetMapping("/users")
    public List<User> getusers(){

        return  userRepository.findAll();



    }

    @GetMapping("getUserById/{id}")
    public User getById(@PathVariable("id") Long id){
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteuser(@PathVariable("id") Long id){
        User userr = userRepository.findById(id).get();
        if(userr != null){
            userRepository.delete(userr);
        }
    }

    @PutMapping("edit")
    public User editUser(@RequestBody User user){
        return userRepository.save(user);
    }
}