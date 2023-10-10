package com.republica.start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.republica.start.entity.UserEntity;
import com.republica.start.repository.UserRepository;
import com.republica.start.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/ronaldo")
public class Ronaldo {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@GetMapping
	public ResponseEntity<List<UserEntity>> list(){	
	return ResponseEntity.ok(userService.findAllUsers());
	}
	
	//Listagem dos valores por id
		@GetMapping("/{id}")
		public ResponseEntity<UserEntity> findById(@PathVariable Long id){
			UserEntity user = userService.findUserById(id);
			if(user != null) {
				return ResponseEntity.ok(user);
			}
			return ResponseEntity.notFound().build();
		}
			
		@PostMapping
		@ResponseStatus(HttpStatus.CREATED)
		public ResponseEntity<UserEntity> create( @RequestBody UserEntity user, HttpServletResponse response) throws Exception {
			UserEntity createdUser = userService.save(user);

		    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		}
			
		@DeleteMapping("/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remove(@PathVariable Long id) {
			userRepository.deleteById(id);
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody UserEntity user) {
			UserEntity userSaved = userService.update(id, user);
		    return ResponseEntity.ok(userSaved);
		}
	
		 @GetMapping("/login")
		 public ResponseEntity<String> login(
		            @RequestParam(name = "username") String username,
		            @RequestParam(name = "password") String password) throws Exception {

		        // Aqui você pode verificar o login e a senha.
		        // Em um ambiente de produção, você deve usar HTTPS para maior segurança.

		        if (userService.logar(username, password) != null) {
		            // Retorne uma resposta JSON com a mensagem de sucesso
		            return ResponseEntity.ok().body("{\"message\": \"Login bem-sucedido!\"}");
		        } else {
		            // Retorne uma resposta JSON com a mensagem de erro
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Login mal-sucedido!\"}");
		        }
		    }
}
