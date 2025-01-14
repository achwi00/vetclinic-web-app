package org.vetclinic.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDto
{
    private String email;
    private String password;
    private String name;
    private String surname;
}
