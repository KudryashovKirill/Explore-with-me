package com.example.ExploreWithMeMain.event;

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
@RequestMapping("/api/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "user-events", description = "Закрытый API для работы с событиями (для создателей событий)")
public class UserEventController {
    private final EventService eventService;

    @Operation(
            summary = "Получение событий, добавленных текущим пользователем",
            description = "Возвращает список всех событий, созданных пользователем"
    )
    @ApiResponse(responseCode = "200", description = "Список событий пользователя")
    @GetMapping
    public ResponseEntity<List<EventShortResponse>> getUserEvents(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable("userId") Long userId,
            @Parameter(description = "Количество элементов, которые нужно пропустить")
            @RequestParam(defaultValue = "0") Integer from,
            @Parameter(description = "Количество элементов на странице")
            @RequestParam(defaultValue = "10") Integer size
    ) {
        log.info("Получен GET запрос на получение событий пользователя с userId = {}, from = {}, size = {}", userId, from, size);
        return new ResponseEntity<>(eventService.getUserEvents(userId, from, size), HttpStatus.OK);
    }

    @Operation(
            summary = "Добавление нового события",
            description = "Пользователь создает новое событие. Статус события устанавливается в WAIT_FOR_PUBLISH"
    )
    @ApiResponse(responseCode = "201", description = "Событие создано")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Parameter(description = "Идентификатор пользователя-создателя", required = true)
            @RequestHeader("X-User-Id") Long userIdHeader,
            @Valid @RequestBody EventRequest eventRequest
    ) {
        log.info("Получен POST запрос на создание события пользователем с id = {}", userIdHeader);
        return new ResponseEntity<>(eventService.createEvent(userIdHeader, eventRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Получение полной информации о событии, добавленном текущим пользователем",
            description = "Возвращает детальную информацию о конкретном событии пользователя"
    )
    @ApiResponse(responseCode = "200", description = "Событие найдено")
    @ApiResponse(responseCode = "404", description = "Событие или пользователь не найдены")
    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponse> getUserEventById(
            @Parameter(description = "Идентификатор пользователя", required = true)
            @PathVariable("userId") Long userId,
            @Parameter(description = "Идентификатор события", required = true)
            @PathVariable("eventId") Long eventId
    ) {
        log.info("Получен GET запрос на получение события with id = {} пользователем с userId = {}", eventId, userId);
        return new ResponseEntity<>(eventService.getUserEventById(userId, eventId), HttpStatus.OK);
    }

    @Operation(
            summary = "Изменение события, добавленного текущим пользователем",
            description = "Пользователь может изменить событие, если оно находится в статусе ожидания модерации или отменено"
    )
    @ApiResponse(responseCode = "200", description = "Событие обновлено")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации или конфликта статусов")
    @ApiResponse(responseCode = "404", description = "Событие не найдено")
    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEventByUser(
            @Parameter(description = "Идентификатор пользователя-создателя", required = true)
            @RequestHeader("X-User-Id") Long userIdHeader,
            @Parameter(description = "Идентификатор события", required = true)
            @PathVariable("eventId") Long eventId,
            @Valid @RequestBody EventUserUpdateRequest updateRequest
    ) {
        log.info("Получен PUT запрос на обновление события с id = {} пользователем с id = {}", eventId, userIdHeader);
        return new ResponseEntity<>(eventService.updateEventByUser(userIdHeader, eventId, updateRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Отмена события текущим пользователем",
            description = "Переводит событие в статус CANCELED"
    )
    @ApiResponse(responseCode = "200", description = "Событие отменено")
    @ApiResponse(responseCode = "400", description = "Событие нельзя отменить")
    @ApiResponse(responseCode = "404", description = "Событие не найдено")
    @PatchMapping("/{eventId}/cancel")
    public ResponseEntity<EventResponse> cancelEventByUser(
            @Parameter(description = "Идентификатор пользователя-создателя", required = true)
            @RequestHeader("X-User-Id") Long userIdHeader,
            @Parameter(description = "Идентификатор события", required = true)
            @PathVariable("eventId") Long eventId
    ) {
        log.info("Получен PATCH запрос на отмену события с id = {} пользователем с id = {}", eventId, userIdHeader);
        return new ResponseEntity<>(eventService.cancelEventByUser(userIdHeader, eventId), HttpStatus.OK);
    }
}