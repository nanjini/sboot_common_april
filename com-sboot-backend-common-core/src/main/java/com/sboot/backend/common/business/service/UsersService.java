package com.sboot.backend.common.business.service;

import com.sboot.backend.common.business.model.Users;

import java.util.List;

public interface UsersService {

    public int insert(Users users);

    public Users getUser(String email);

    public List<Users> getUserAll();

    public int updateByEmail(Users users);

    public int softDeleteByEmail(Users users);

}
