/**
 * Author   : Prabakaran Ramu
 * User     : ramup
 * Date     : 08/07/2025
 * Usage    :
 * Since    : Version 1.0
 */
package com.me.learning.consul.personservice.dto;

import lombok.Data;

@Data
public class PersonRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private Integer age;
}
