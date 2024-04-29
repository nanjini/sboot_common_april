package com.sboot.backend.common.api.controller;

import com.sboot.backend.common.business.model.Users;
import com.sboot.backend.common.business.service.UsersService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Users> usersList = usersService.getUserAll();
        if (!usersList.isEmpty()) {
            map.put("status", 1);
            map.put("data", usersList);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.clear();
            map.put("status", 0);
            map.put("message", "Data is not foud");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{email}")
    @Parameter(hidden = true)
    public ResponseEntity<?> getUser(@PathVariable String email) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Users users;
        try {
            users = usersService.getUser(email);
            if (users == null) {
                map.clear();
                map.put("status", 0);
                map.put("message", "Data is Not Found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Failed to Such");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        map.put("status", 1);
        map.put("data", users);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> insert(@RequestBody Users users) {
        Map<String,Object> map = new LinkedHashMap<String, Object>();
        try {
            Users checkUser = usersService.getUser(users.getEmail());
            if(checkUser == null) {
                usersService.insert(users);
            } else {
                map.clear();
                map.put("status", 0);
                map.put("message", "Failed to save Data : Duplicated(email:" + users.getEmail()+")");
                return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Failed to save Data");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        map.put("status", 1);
        map.put("message", "Data Saved Successfully");
        map.put("data", usersService.getUser(users.getEmail()));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updateByEmail(@PathVariable("email") String email, @RequestBody Users users) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Users checkUsers = usersService.getUser(email);
            if (checkUsers != null) {
                usersService.updateByEmail(users);
            } else {
                map.clear();
                map.put("status", 0);
                map.put("message", "Failed to Update Data : Data is not Found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Failed to Update Data");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        map.put("status", 1);
        map.put("message", "Data Updated Successfully");
        map.put("data", usersService.getUser(email));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/deleteUser/{email}")
    public ResponseEntity<?> softDeleteByEmail(@PathVariable("email") String email, @RequestBody Users users) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            Users checkUsers = usersService.getUser(email);
            if (checkUsers != null) {
                users.setUse_flag("1");
                users.setDelete_dt(LocalDateTime.now());
                usersService.softDeleteByEmail(users);
            } else {
                map.clear();
                map.put("status", 0);
                map.put("message", "Failed to Delete User : User is not Found");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            map.clear();
            map.put("status", 0);
            map.put("message", "Failed to Delete User");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        map.put("status", 1);
        map.put("message", "User Deleted Successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
