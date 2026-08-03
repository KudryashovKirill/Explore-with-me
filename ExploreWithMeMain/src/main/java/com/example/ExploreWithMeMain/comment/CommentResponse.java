package com.example.ExploreWithMeMain.comment;

import com.example.ExploreWithMeMain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Данные комментария в ответе API.
 */
@Schema(description = "Информация о комментарии к событию")
public class CommentResponse {
    @Schema(description = "Уникальный идентификатор комментария под событием", example = "1")
    private Long id;
    @Schema(description = "Текст комментария под событием")
    private String text;
    @Schema(description = "Дата и время когда был оставлен комментарий")
    private LocalDateTime date;
    @Schema(description = "Пользователь, оставивший комментарий под событием")
    private User user;
}
