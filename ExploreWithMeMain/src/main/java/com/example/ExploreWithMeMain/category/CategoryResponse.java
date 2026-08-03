package com.example.ExploreWithMeMain.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * Данные категории в ответе API
 */
@Builder
@Getter
@Schema(description = "Информация о категории события")
public class CategoryResponse {
    @Schema(description = "Уникальный идентификатор категории события")
    private final Long id;
    @Schema(description = "Название категории события")
    private final String name;
}
