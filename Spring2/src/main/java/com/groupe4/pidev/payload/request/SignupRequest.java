package com.groupe4.pidev.payload.request;

import com.groupe4.pidev.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignupRequest {
    private String firstName;
    private String lastName;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    private Date dateOfBirth;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String contactNumber;
    private Date createdAt;
    @NotBlank
    private String password;

    private Set<Role> role;

    public SignupRequest(String firstName, String lastName, String username, Date dateOfBirth, String email, String contactNumber, Date createdAt, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
        this.password = password;
    }
}
