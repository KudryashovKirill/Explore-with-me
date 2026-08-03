package com.example.ExploreWithMeMain.event;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для создания или полного обновления события (POST / PUT).
 * Все обязательные поля должны присутствовать.
 */
@Schema(description = "Запрос на создание либо полное обновление события")
public record EventRequest(
        @Schema(description = "Название события", example = "Поход в кино на колобка",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Название события не может быть пустым или null")
        String nameOfEvent,

        @Schema(description = "Статус события", example = "PUBLISHED", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Статус события не может быть null")
        EventStatus status,

        @Schema(description = "Дата начала проведения события", example = "2026-08-01",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Дата начала проведения события не может быть null")
        LocalDate dateStart,

        @Schema(description = "Дата конца проведения события", example = "2026-08-02",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Дата конца проведения события не может быть null")
        LocalDate dateEnd,

        @Schema(description = "Уникальный идентификатор пользователя, создавшего событие", example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Уникальный идентификатор пользователя, создавшего событие не может быть null")
        Long organizerId,

        @Schema(description = "Список уникальных идентификаторов заявок на данное событие",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        List<Long> bidsId,

        @Schema(description = "Список уникальных идентификаторов пользователей записанных на событие",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        List<Long> usersId,

        @Schema(description = "Список уникальных идентификаторов категорий под которые попадает событие",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        List<Long> categoriesId
) {
}
