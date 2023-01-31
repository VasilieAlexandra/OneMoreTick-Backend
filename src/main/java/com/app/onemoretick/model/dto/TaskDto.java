package com.app.onemoretick.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    @JsonProperty("id")
    private Integer id;
    @NotNull
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;
    @NotNull
    @JsonProperty("startDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @NotNull
    @JsonProperty("endDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull
    @JsonProperty("isDone")
    private Integer isDone;
    @NotNull
    @JsonProperty("idCategory")
    private Integer idCategory;
    @NotNull
    @JsonProperty("idUser")
    private Integer idUser;

}
