package com.andrei.contract.account;

import com.andrei.contract.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({
        "accountNumber",
        "accountName",
        "user"
})

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @NotBlank(message = "Account Number should not be null")
    @JsonProperty("accountNumber")
    private String accountNumber;

    @NotBlank(message = "Account Name should not be null")
    @JsonProperty("accountName")
    private String accountName;

    @JsonProperty("user")
    private UserDTO user;

}