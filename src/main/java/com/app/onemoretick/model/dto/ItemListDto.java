package com.app.onemoretick.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemListDto {
    private Integer id;
    private String name;
    private Integer idShoppingList;
}
