package br.com.unipac.cpa.web.resources;

import java.util.List;

import br.com.unipac.cpa.web.support.UserSupport;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import br.com.unipac.cpa.web.dto.response.UserResponse;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserSupport userSupport;

	@GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username) {
		UserResponse user = userSupport.findUserByUsername(username);

		if (user != null) {
			log.info("Total Retornado: " + user.toString());
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping()
	@ResponseBody
	@Timed
	public ResponseEntity<List<UserResponse>> list() {
		List<UserResponse> users = userSupport.list();

		if (users != null) {
			log.info("Total Retornado: " + users.size());
			return ResponseEntity.ok(users);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
