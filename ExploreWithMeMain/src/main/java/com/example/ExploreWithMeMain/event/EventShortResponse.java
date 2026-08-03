package com.example.ExploreWithMeMain.event;

import com.example.ExploreWithMeMain.category.CategoryResponse;
import com.example.ExploreWithMeMain.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(description = "Краткая информация о событии")
public class EventShortResponse {

    @Schema(description = "Идентификатор события", example = "1")
    private Long id;

    @Schema(description = "Название события", example = "Концерт классической музыки")
    private String nameOfEvent;

    @Schema(description = "Дата начала события", example = "2026-09-15")
    private LocalDate dateStart;

    @Schema(description = "Дата окончания события", example = "2026-09-15")
    private LocalDate dateEnd;

    @Schema(description = "Категории события")
    private CategoryResponse category;

    @Schema(description = "Организатор события")
    private UserResponse initiator;

    @Schema(description = "Количество свободных мест", example = "10")
    private Integer freePlaces;

    @Schema(description = "Количество просмотров события", example = "125")
    private Long views;

    @Schema(description = "Количество подтвержденных заявок на участие", example = "5")
    private Long confirmedRequests;
}