package az.developia.CommerceApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import az.developia.CommerceApp.dto.LoginRequest;
import az.developia.CommerceApp.dto.UserMeDto;
import az.developia.CommerceApp.entity.AuthorityEntity;
import az.developia.CommerceApp.entity.UserEntity;
import az.developia.CommerceApp.jwt.JwtUtil;
import az.developia.CommerceApp.repository.AuthorityRepository;
import az.developia.CommerceApp.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	public UserEntity register(UserEntity user) {

		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new RuntimeException("This username exists");
		}

		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.setName(user.getName());
		userEntity.setUsername(user.getUsername());
		userEntity.setSurname(user.getSurname());
		userEntity.setEmail(user.getEmail());
		userEntity.setEnabled(true);
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(userEntity);

		AuthorityEntity authority = new AuthorityEntity();

		authority.setUsername(user.getUsername());
		authority.setAuthority("ROLE_USER");

		authorityRepository.save(authority);
		return user;

	}

	public String login(LoginRequest loginRequest) {
		UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));
		if (!passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())) {
			throw new RuntimeException("Wrong password");
		}
		return jwtUtil.generateToken(loginRequest.getUsername());
	}

	public UserMeDto me() {
		UserEntity user = getCurrentUser();
		UserMeDto dto = new UserMeDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		return dto;
	}

	private UserEntity getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
