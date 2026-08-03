package com.example.ExploreWithMeMain.bid;

import com.example.ExploreWithMeMain.event.Event;
import com.example.ExploreWithMeMain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

/**
 * Данные заявки в ответе API.
 */
@Schema(description = "Информация о заявке")
@Builder
@Getter
public class BidResponse {
    @Schema(description = "Уникальный идентификатор заявки на событие")
    private Long id;
    @Schema(description = "Статус заявки", example = "CONFIRMED")
    private final BidStatus status;
    @Schema(description = "Пользователь, отправивший заявку")
    private final User user;
    @Schema(description = "Событие, на которое отправлена заявка")
    private final Event event;

}
