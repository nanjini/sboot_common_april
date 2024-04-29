package com.sboot.backend.common.business.mapper;

import com.sboot.backend.common.business.model.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {

    int insert(Users users);

    Users getUser(String email);

    List<Users> getUserAll();

    public int updateByEmail(Users users);

    public int softDeleteByEmail(Users users);

}
