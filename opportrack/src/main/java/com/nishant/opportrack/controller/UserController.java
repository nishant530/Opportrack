package com.nishant.opportrack.controller;
import com.nishant.opportrack.dto.LoginRequest;
import com.nishant.opportrack.dto.LoginResponse;
import com.nishant.opportrack.security.JwtUtil;
import com.nishant.opportrack.model.User;
import com.nishant.opportrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nishant.opportrack.model.Company;
import com.nishant.opportrack.repository.CompanyRepository;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
private CompanyRepository companyRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
public LoginResponse login(@RequestBody LoginRequest request) {
    var user = userService.findByEmail(request.getEmail());
    if (user == null || !userService.checkPassword(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid email or password");
    }
    String token = jwtUtil.generateToken(user.getEmail());
    return new LoginResponse(token);
}
@PostMapping("/select-companies")
public User selectCompanies(@RequestHeader("Authorization") String token, @RequestBody List<Long> companyIds) {
    String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
    User user = userService.findByEmail(email);

    Set<Company> companies = new HashSet<>(companyRepository.findAllById(companyIds));
    user.setSelectedCompanies(companies);

    return userService.registerUser(user); // reuse kar rahe hain save karne ke liye (thoda hacky, but abhi kaam chalega)
}
}