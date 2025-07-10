/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 10/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package com.me.practise.otel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest implements Serializable {

    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private Double balance;
    private String currency;
    private String status;
}
