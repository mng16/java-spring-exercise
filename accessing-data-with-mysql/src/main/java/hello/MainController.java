package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import hello.User;
import hello.UserRepository;

import java.util.Optional;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@PostMapping("/add")
	public ResponseEntity addNewUser(@RequestBody User userJson) {
		userRepository.save(userJson);
		return new ResponseEntity(userJson, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity updateUserInfo(@PathVariable Integer id, @RequestBody User userJson) {

		User user = userRepository.findById(id).orElse(null);

		user.setName(userJson.getName());
		user.setEmail(userJson.getEmail());
		userRepository.save(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PutMapping("/updatename/{id}")
	public ResponseEntity updateUserName(@PathVariable Integer id,
										 @RequestParam String newName) {

		User user = userRepository.findById(id).orElse(null);

		user.setName(newName);

		userRepository.save(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PutMapping("/updateemail/{id}")
	public ResponseEntity updateUserEmail(@PathVariable Integer id,
										  @RequestParam String newEmail) {

		User user = userRepository.findById(id).orElse(null);
		user.setEmail(newEmail);

        userRepository.save(user);

		return new ResponseEntity(user, HttpStatus.OK);
	}
}
