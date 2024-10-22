package com.resellerapp.model.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDto {
    @Size(min = 2, max = 30)
    @NotNull(message = "Mandatory field")
    private String username;
    @Email(message = "Enter valid email address")
    private String email;
    @Size(min = 2, max = 30)
    private String password;
    private String confirmPassword;

    public @Size(min = 2, max = 30) String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 2, max = 30) String username) {
        this.username = username;
    }

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
        this.email = email;
    }

    public @Size(min = 2, max = 30) String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 2, max = 30) String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
