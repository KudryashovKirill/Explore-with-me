package com.example.ExploreWithMeMain.event;

import com.example.ExploreWithMeMain.bid.Bid;
import com.example.ExploreWithMeMain.category.Category;
import com.example.ExploreWithMeMain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * Данные события в ответе API.
 */
@Schema(description = "Информация о событии")
@Getter
@Builder
public class EventResponse {
    @Schema(description = "Уникальный идентификатор события", example = "1")
    private final Long id;

    @Schema(description = "Название события")
    private final String nameOfEvent;

    @Schema(description = "Статус события", example = "WAIT_FOR_PUBLISH")
    private final EventStatus status;

    @Schema(description = "Дата начала проведения события", example = "2026-08-01")
    private final LocalDate dateStart;

    @Schema(description = "Дата конца проведения события", example = "2026-08-02")
    private final User organizer;

    @Schema(description = "Заявки относящиеся к данному событию")
    private final List<Bid> bids;

    @Schema(description = "Пользователи, зарегистрированные на событие")
    private final List<User> users;

    @Schema(description = "Категории, под которые попадает событие")
    private final List<Category> categories;
}
