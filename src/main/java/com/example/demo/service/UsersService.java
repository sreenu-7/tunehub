package com.example.demo.service;

import com.example.demo.entities.Users;

public interface UsersService {

	public String addUsers(Users user);
	public boolean emailExits(String email);
	public boolean validateUser(String email, String password);
	public String getRole(String email);
	public void updateUsers(Users user);
	public Users getUser(String email);
}
