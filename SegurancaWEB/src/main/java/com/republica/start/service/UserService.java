package com.republica.start.service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.republica.start.entity.Simetric_private_key_entity;
import com.republica.start.entity.UserEntity;
import com.republica.start.repository.ChaveRepository;
import com.republica.start.repository.UserRepository;
import com.republica.start.security.AESEncryption;
import com.republica.start.security.Enconder;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChaveRepository chaveRepository;

	public List<UserEntity> findAllUsers() {

		return userRepository.findAll();
	}

	public UserEntity update(Long id, UserEntity user) {
		UserEntity userSaved = findUserById(id);
		BeanUtils.copyProperties(user, userSaved, "id");
		return userRepository.save(userSaved);
	}

	public UserEntity findUserById(Long id) {
		UserEntity userSaved = userRepository.findById(id).orElseThrow((() -> new EmptyResultDataAccessException(1)));
		return userSaved;
	}

	public UserEntity save(UserEntity user) throws Exception {

		Enconder password = new Enconder();
		
		user.setPassword(password.encode(user.getPassword()));
		
		return userRepository.save(user);

	}

	public UserEntity logar(String login, String password) throws Exception {
		    UserEntity user = userRepository.findByEmail(login);

		    if (user != null) {
				Enconder cripto = new Enconder();

		        String key2 = cripto.decode(user.getPassword());
		        
		        if(password.equals(key2)) {
		        	return user;
		        }
		    }
		       
		    
		    return null; // Usuário não encontrado ou senha incorreta

	}

}
