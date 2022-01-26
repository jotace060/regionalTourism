package com.regionalTourism.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Josorio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private static final long serialVersionUID = 15154861L;
    private int idClient;
    private String documentClient;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private int status;

}
