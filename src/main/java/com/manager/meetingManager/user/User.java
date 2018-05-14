package com.manager.meetingManager.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @NotNull
    @Size(min=2, max=50)
    private String lastName;

    @Size(min=9, max=20)
    private String phoneNumber;

    @Email
    @Size(min=5, max=50)
    private String email;

    @Size(min=5, max=30)
    private String login;

    @Size(min=5, max=30)
    private String password;
}
