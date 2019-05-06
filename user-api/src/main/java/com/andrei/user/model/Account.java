package com.andrei.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ACCOUNT")
public class Account {

    @Id
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;


    @Column(name = "ACCOUNT_NAME", nullable = false)
    private String accountName;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_PROFILE_ID")
    private User user;
}
