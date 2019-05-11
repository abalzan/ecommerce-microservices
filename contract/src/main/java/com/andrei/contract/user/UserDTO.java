package com.andrei.contract.user;

import com.andrei.contract.account.AccountDTO;
import com.andrei.contract.address.AddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonPropertyOrder({
        "userId",
        "username",
        "firstName",
        "lastName",
        "gender",
        "memberType",
        "registrationDate",
        "account",
        "address"
})

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("userId")
    private long id;

    @NotBlank(message = "Username should not be null")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "First name should not be null")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Last name should not be null")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank(message = "Gender should not be null")
    @JsonProperty("gender")
    private String gender;

    @NotBlank(message = "Member type should not be null")
    @JsonProperty("memberType")
    private String memberType;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Registration date should not be null")
    @JsonProperty("registrationDate")
    private Date registrationDate;

    @JsonProperty("account")
    private AccountDTO account;

    @JsonProperty("address")
    private AddressDTO address;

}
