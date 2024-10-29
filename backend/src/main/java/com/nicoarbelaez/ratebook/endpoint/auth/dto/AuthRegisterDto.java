package com.nicoarbelaez.ratebook.endpoint.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterDto {

    private String email;
    private String password;

    private String firstName;
    private String lastName;
}
