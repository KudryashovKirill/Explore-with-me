package com.example.ExploreWithMeMain.bid;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * DTO для создания или полного обновления заявки (POST / PUT).
 * Все обязательные поля должны присутствовать.
 */
@Schema(description = "Запрос на создание либо полное обновление заявки")
public record BidRequest(
        @Schema(description = "Статус заявки", example = "CONFIRMED", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Статус не может быть null")
        BidStatus status,
        @Schema(description = "Уникальный идентификатор пользователя, который отправляет заявку", example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Уникальный идентификатор пользователя не может быть null")
        Long userId,
        @Schema(description = "Уникальный идентификатор события, на которое отправляется заявка", example = "1",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Уникальный идентификатор события не может быть null")
        Long eventId
) {
}
