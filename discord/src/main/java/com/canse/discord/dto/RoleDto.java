package com.canse.discord.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoleDto {

    private Integer id;

    @NotNull(message = "Le roleName ne peut pas etre null")
    @NotEmpty(message = "Le roleName ne peut pas etre vide")
    @NotBlank(message = "Le roleName ne peut pas contenir d'éspace")
    private String roleName;


}
