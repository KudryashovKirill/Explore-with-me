package com.example.ExploreWithMeMain.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "comments", description = "Управление комментариями к событиям")
public class CommentController {
    private final CommentService commentService;

    @Operation(
            summary = "Список комментариев",
            description = "Возвращает список всех комментариев"
    )
    @ApiResponse(responseCode = "200", description = "Список комментариев")
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        log.info("Получен GET запрос на получение всех комментариев");
        return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
    }

    @Operation(
            summary = "Получить детали комментария",
            description = "Возвращает детальную информацию о конкретном комментарии"
    )
    @ApiResponse(responseCode = "200", description = "Комментарий найден")
    @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getCommentById(
            @Parameter(description = "Уникальный идентификатор комментария") @PathVariable("id") Long commentId
    ) {
        log.info("Получен GET запрос на получение комментария с id = {}", commentId);
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @Operation(
            summary = "Создать комментарий к событию",
            description = "Пользователь создает комментарий к событию"
    )
    @ApiResponse(responseCode = "201", description = "Комментарий создан")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        log.info("Получен POST запрос на создание комментария");
        return new ResponseEntity<>(commentService.createComment(commentRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Полное обновление комментария",
            description = "Заменяет все поля комментария"
    )
    @ApiResponse(responseCode = "200", description = "Комментарий обновлен")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @Parameter(description = "Уникальный идентификатор комментария", example = "1", required = true)
            @PathVariable("id") Long commentId,
            @Valid @RequestBody CommentRequest commentRequest
    ) {
        log.info("Получен PUT запрос на обновление комментария");
        return new ResponseEntity<>(commentService.updateComment(commentId, commentRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Удалить комментарий"
    )
    @ApiResponse(responseCode = "204", description = "Комментарий удален")
    @ApiResponse(responseCode = "404", description = "Комментарий не найден")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @Parameter(description = "Уникальный идентификатор комментария", example = "1", required = true)
            @PathVariable("id") Long commentId
    ) {
        log.info("Получен DELETE запрос на удаление комментария с id = {}", commentId);
        commentService.deleteComment(commentId);
    }
}
