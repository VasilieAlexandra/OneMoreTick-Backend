package com.app.onemoretick.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingListDto {

    @JsonProperty("id")
    private Integer id;
    @NotNull
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("idUserList")
    private Integer idUserList;
}
