package com.example.ExploreWithMeMain.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для создания или полного обновления категории (POST / PUT).
 * Все обязательные поля должны присутствовать.
 */
@Schema(description = "Запрос на создание либо полное обновление категории события")
public record CategoryRequest(
        @Schema(description = "Название категории события", example = "искусство", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Название категории события не может быть пустым или null")
        String name
) {
}
