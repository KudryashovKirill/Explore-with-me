package com.example.ExploreWithMeMain.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO для создания или полного обновления комментария (POST / PUT).
 * Все обязательные поля должны присутствовать.
 */
@Schema(description = "Запрос на создание либо полное обновление комментария к событию")
public record CommentRequest(
        @Schema(description = "Текст комментария к событию", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Текст комментария не может быть пустым или null")
        String text,
        @Schema(description = "Уникальный идентификатор пользователя, оставившего комментарий",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Уникальный идентификатор пользователя, оставившего комментарий не может быть null")
        Long userId
) {
}
