package com.andrei.address.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Account Number should not be null")
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @OneToOne
    @JoinColumn(name = "USER_PROFILE_ID")
    private User user;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Address> addresses = new HashSet<>();

}
