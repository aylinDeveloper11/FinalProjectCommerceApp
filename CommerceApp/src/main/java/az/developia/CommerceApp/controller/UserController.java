package az.developia.CommerceApp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.CommerceApp.dto.LoginRequest;
import az.developia.CommerceApp.dto.UserMeDto;
import az.developia.CommerceApp.entity.UserEntity;
import az.developia.CommerceApp.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public UserEntity register(@Valid @RequestBody UserEntity user) {
		return userService.register(user);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest loginrequest) {
		String token = userService.login(loginrequest);
		return ResponseEntity.ok(Map.of("token", token));
	}

	@PreAuthorize("isAuthenticated()") // или "hasAnyRole('USER','ADMIN')"
	@GetMapping("/me")
	public UserMeDto me() {
		return userService.me();
	}

}