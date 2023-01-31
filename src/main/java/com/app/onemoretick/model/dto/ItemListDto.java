package com.app.onemoretick.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemListDto {

    @JsonProperty("id")
    private Integer id;
    @NotNull
    @JsonProperty("name")
    private String name;
    @NotNull
    @JsonProperty("idShoppingList")
    private Integer idShoppingList;
}
