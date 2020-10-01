package com.cybertek.lab3_orm.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account_user",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
public class AppUser extends Model {

    @Email(message = "Please add a valid email")
    @NotEmpty(message = "Email shouldn't be empty")
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    @Size(min = 6, message = "Password should contain minimum 6 characters")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
