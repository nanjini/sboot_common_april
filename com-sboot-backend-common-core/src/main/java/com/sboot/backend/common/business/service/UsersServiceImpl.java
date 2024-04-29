package com.sboot.backend.common.business.service;

import com.sboot.backend.common.business.mapper.UsersMapper;
import com.sboot.backend.common.business.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService{

    private final UsersMapper usersMapper;

    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public int insert(Users users) {
        return usersMapper.insert(users);
    }

    @Override
    public Users getUser(String email) {
        return usersMapper.getUser(email);
    }

    @Override
    public List<Users> getUserAll() {
        return usersMapper.getUserAll();
    }

    @Override
    public int updateByEmail(Users users) {
        return usersMapper.updateByEmail(users);
    }

    @Override
    public int softDeleteByEmail(Users users) {
        return usersMapper.softDeleteByEmail(users);
    }


}
