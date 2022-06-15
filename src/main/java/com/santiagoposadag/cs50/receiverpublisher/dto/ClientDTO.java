package com.santiagoposadag.cs50.receiverpublisher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private String dni;
    private String phone;
    private String routingKey;
}
