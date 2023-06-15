package com.canse.discord.dto;

import com.canse.discord.models.File;
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
public class FileDto {

    private Integer id;
    @NotNull(message = "Le firstname ne peut pas etre null")
    @NotEmpty(message = "Le firstname ne peut pas etre vide")
    @NotBlank(message = "Le firstname ne peut pas contenir d'éspace")
    private String name;
    @NotNull(message = "Le firstname ne peut pas etre null")
    @NotEmpty(message = "Le firstname ne peut pas etre vide")
    @NotBlank(message = "Le firstname ne peut pas contenir d'éspace")
    private String path;

    public static FileDto fromEntity(File file){
        return FileDto.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }

    public static File toEntity(FileDto file){
        return File.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }

}
