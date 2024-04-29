package com.sboot.backend.common.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;

public class Users {

    private int id;

    private String email;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_dt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime delete_dt;

    private String use_flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegister_dt() {
        return register_dt;
    }

    public void setRegister_dt(LocalDateTime register_dt) {
        this.register_dt = register_dt;
    }

    public LocalDateTime getDelete_dt() {
        return delete_dt;
    }

    public void setDelete_dt(LocalDateTime delete_dt) {
        this.delete_dt = delete_dt;
    }

    public String getUse_flag() {
        return use_flag;
    }

    public void setUse_flag(String use_flag) {
        this.use_flag = use_flag;
    }
}
