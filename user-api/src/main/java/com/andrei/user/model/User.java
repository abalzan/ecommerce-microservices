package com.andrei.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username should not be null")
    @Column(name = "USER_NAME", nullable = false)
    private String username;

    @NotBlank(message = "First name should not be null")
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name should not be null")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotBlank(message = "Sex should not be null")
    @Column(name = "SEX", nullable = false)
    private String sex;

    @NotBlank(message = "Member type should not be null")
    @Column(name = "MEMBER_TYPE", nullable = false)
    private String memberType;

    @NotBlank(message = "Account number should not be null")
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @NotNull(message = "Registration date should not be null")
    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE", nullable = false, length = 10)
    private Date registrationDate;

}
