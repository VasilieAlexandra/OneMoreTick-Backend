package com.app.onemoretick.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingListDto {
    private Integer id;
    private String name;
    private Integer idUserList;
}
