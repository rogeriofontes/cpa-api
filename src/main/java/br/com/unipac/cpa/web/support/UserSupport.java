package br.com.unipac.cpa.web.support;

import br.com.unipac.cpa.model.domain.User;
import br.com.unipac.cpa.model.repository.UserRepository;
import br.com.unipac.cpa.web.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class UserSupport {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversionService userConvert;

	public UserResponse findUserByUsername(@PathVariable("username") String username) {
		User user = userRepository.findByEmail(username);
		return userConvert.convert(user, UserResponse.class);
	}

	public List<UserResponse> list() {
		List<UserResponse> dtos = new ArrayList<>();
		userRepository.findAll().forEach(user -> {
			UserResponse result = userConvert.convert(user, UserResponse.class);
			dtos.add(result);
		});
		return dtos;
	}
}
