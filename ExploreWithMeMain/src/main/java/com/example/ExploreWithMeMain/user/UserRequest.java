package com.example.ExploreWithMeMain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO для создания или полного обновления пользователя (POST / PUT).
 * Все обязательные поля должны присутствовать.
 */
@Schema(description = "Запрос на создание либо полное обновление пользователя")
public record UserRequest(
        @Schema(description = "Имя пользователя", example = "Иван", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Имя пользователя не может быть пустым или null")
        String firstName,

        @Schema(description = "Фамилия пользователя", example = "Иванов", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank(message = "Фамилия пользователя не может быть пустым или null")
        String lastname,

        @Schema(description = "Дата рождения пользователя", example = "2005-01-01",
                requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "Дата рождения пользователя не может быть null")
        LocalDate birthdate,

        @Schema(description = "Дата регистрации на ресурсе", example = "2026-01-01")
        @NotNull(message = "Дата регистрации на ресурсе не может быть null")
        LocalDate dateOfRegistration,

        @Schema(description = "Флаг является лм пользователь администратором", example = "true",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Boolean idAdmin,

        @Schema(description = "Статус пользователя", example = "ACTIVATED",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        UserStatus status,

        @Schema(description = "Уникальные идентификаторы событий, созданных пользователем",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        List<Long> eventsId// события созданные пользователем
) {
}
