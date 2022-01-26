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
public class ParcelDTO {
    private int idParcel;
    private String nameParcel;
    private int idClient;

}
