package com.example.ExploreWithMeMain.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "admin-events", description = "Административный API для модерации и управления событиями")
public class AdminEventController {
    private final EventService eventService;

    @Operation(
            summary = "Поиск событий администратором",
            description = "Возвращает полную информацию обо всех событиях, подходящих под переданные условия"
    )
    @ApiResponse(responseCode = "200", description = "Список найденных событий")
    @GetMapping
    public ResponseEntity<List<EventResponse>> searchEventsByAdmin(
            @Parameter(description = "Список id пользователей") @RequestParam(required = false) List<Long> users,
            @Parameter(description = "Список статусов событий") @RequestParam(required = false) List<EventStatus> statuses,
            @Parameter(description = "Список id категорий") @RequestParam(required = false) List<Long> categories,
            @Parameter(description = "Дата и время не раньше которых должно произойти событие")
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @Parameter(description = "Дата и время не позже которых должно произойти событие")
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @Parameter(description = "Количество элементов, которые нужно пропустить") @RequestParam(defaultValue = "0") Integer from,
            @Parameter(description = "Количество элементов на странице") @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("Получен GET запрос от админа на поиск событий с фильтрами");
        return new ResponseEntity<>(
                eventService.searchEventsByAdmin(users, statuses, categories, rangeStart, rangeEnd, from, size),
                HttpStatus.OK
        );
    }

    @Operation(
            summary = "Редактирование события и его статуса администратором",
            description = "Администратор может отредактировать любые поля события"
    )
    @ApiResponse(responseCode = "200", description = "Событие обновлено")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Событие не найдено")
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEventByAdmin(
            @Parameter(description = "Идентификатор события", required = true) @PathVariable("eventId") Long eventId,
            @Valid @RequestBody EventAdminUpdateRequest updateRequest
    ) {
        log.info("Получен PUT запрос от админа на обновление события с id = {}", eventId);
        return new ResponseEntity<>(eventService.updateEventByAdmin(eventId, updateRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Публикация события",
            description = "Публикация события администратором. Переводит статус в PUBLISHED"
    )
    @ApiResponse(responseCode = "200", description = "Событие успешно опубликовано")
    @ApiResponse(responseCode = "400", description = "Событие не в состоянии WAIT_FOR_PUBLISH")
    @ApiResponse(responseCode = "404", description = "Событие не найдено")
    @PatchMapping("/{eventId}/publish")
    public ResponseEntity<EventResponse> publishEvent(
            @Parameter(description = "Идентификатор события", required = true) @PathVariable("eventId") Long eventId
    ) {
        log.info("Получен PATCH запрос от админа на публикацию события с id = {}", eventId);
        return new ResponseEntity<>(eventService.publishEvent(eventId), HttpStatus.OK);
    }

    @Operation(
            summary = "Отклонение публикации события",
            description = "Отклонение события администратором. Переводит статус в CANCELED"
    )
    @ApiResponse(responseCode = "200", description = "Публикация события отклонена")
    @ApiResponse(responseCode = "400", description = "Событие уже опубликовано")
    @ApiResponse(responseCode = "404", description = "Событие не найдено")
    @PatchMapping("/{eventId}/reject")
    public ResponseEntity<EventResponse> rejectEvent(
            @Parameter(description = "Идентификатор события", required = true) @PathVariable("eventId") Long eventId
    ) {
        log.info("Получен PATCH запрос от админа на отклонение события с id = {}", eventId);
        return new ResponseEntity<>(eventService.rejectEvent(eventId), HttpStatus.OK);
    }
}