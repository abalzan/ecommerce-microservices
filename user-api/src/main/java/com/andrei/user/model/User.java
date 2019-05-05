package com.andrei.user.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
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
    @Column(name = "GENDER", nullable = false)
    private String gender;

    @NotBlank(message = "Member type should not be null")
    @Column(name = "MEMBER_TYPE", nullable = false)
    private String memberType;

    @NotNull(message = "Registration date should not be null")
    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTRATION_DATE", nullable = false, length = 10)
    private Date registrationDate;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_NUMBER")
    private Account account;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

}
