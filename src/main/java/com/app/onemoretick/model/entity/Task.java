package com.app.onemoretick.model.entity;

import com.app.onemoretick.model.validation.ValidEndDate;
import com.app.onemoretick.model.validation.ValidStartDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@ValidEndDate.List({
        @ValidEndDate(
                s = "startDate",
                e = "endDate",
                message = "End date cannot before be start date!"
        )
})
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "start_date", nullable = false)
    @ValidStartDate
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @Column(name = "is_done", nullable = false)
    private Integer isDone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false,updatable = false)
    @ToString.Exclude
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Category idCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "id_user", nullable = false,updatable = false)
    @ToString.Exclude
    private User idUser;

}
