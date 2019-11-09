package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.exception.UserNotFoundException;
import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.dto.TokenResult;
import br.com.unipac.cpa.model.service.PasswordCrypto;
import br.com.unipac.cpa.model.service.UserService;
import br.com.unipac.cpa.util.JWTUtil;
import br.com.unipac.cpa.web.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

@Component
public class AuthSupport {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordCrypto crypto;

	public TokenResult validate(LoginRequest loginRequest) throws ServletException {
		validateLoginRequestIsNull(loginRequest);

		UserDetails userResult = loadUserByUsername(loginRequest);
		return resultLoginInfo(loginRequest, userResult);
	}

	private UserDetails loadUserByUsername(LoginRequest loginRequest) {
		UserDetails user = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}

		validatePasswordIsValid(loginRequest, user);
		return user;
	}

	private TokenResult resultLoginInfo(LoginRequest userAuthentication, UserDetails userDetails) {
		TokenResult tokenResult = new TokenResult();

		User user = userService.findByUsername(userDetails.getUsername());
		tokenResult.setToken(JWTUtil.createToken(userAuthentication.getEmail()));
		tokenResult.setUserId(user.getId());
		tokenResult.setRoles(user.getRoles());

		return tokenResult;
	}

	private boolean validated(LoginRequest loginRequest, String password) {
		if (password != null)
			return crypto.matches(loginRequest.getPassword(), password);
		return Boolean.FALSE;
	}

	private void validateLoginRequestIsNull(LoginRequest loginRequest) throws ServletException {
		if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}
	}

	private void validatePasswordIsValid(LoginRequest loginRequest, UserDetails user) {
		boolean isValid = validated(loginRequest, user.getPassword());
		if (!isValid) {
			throw new UserNotFoundException("User not found.");
		}
	}
}
