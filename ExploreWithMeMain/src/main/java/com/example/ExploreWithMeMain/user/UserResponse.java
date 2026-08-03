package com.example.ExploreWithMeMain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * Данные пользователя в ответе API.
 */
@Schema(description = "Информация о пользователе")
@Getter
@Builder
public class UserResponse {
    @Schema(description = "Уникальный идентификатор пользователя", example = "1")
    private final Long id;

    @Schema(description = "Имя пользователя", example = "Иван")
    private final String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")

    private final String lastname;

    @Schema(description = "Дата рождения пользователя", example = "2005-01-01")
    private final LocalDate birthdate;

    @Schema(description = "Дата регистрации на ресурсе", example = "2026-01-01")
    private final LocalDate dateOfRegistration;

    @Schema(description = "Флаг является лм пользователь администратором", example = "true")
    private final Boolean idAdmin;

    @Schema(description = "Статус пользователя", example = "ACTIVATED")
    private final UserStatus status;

    @Schema(description = "Уникальные идентификаторы событий, созданных пользователем")
    private final List<Long> eventsId;
}
