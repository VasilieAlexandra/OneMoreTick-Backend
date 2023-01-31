package com.app.onemoretick.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    @JsonProperty("id")
    private Integer id;
    @NotNull
    @JsonProperty("name")
    private String name;
}
