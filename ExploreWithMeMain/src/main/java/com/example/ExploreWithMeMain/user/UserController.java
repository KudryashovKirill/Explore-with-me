package com.example.ExploreWithMeMain.user;

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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "users", description = "Управление пользователями")
public class UserController {
    private final UserService userService;

    @Operation(
            summary = "Список пользователей",
            description = "Возвращает список всех пользователей"
    )
    @ApiResponse(responseCode = "200", description = "Список пользователей")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Получен GET запрос на получение всех пользователей");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(
            summary = "Получить детали пользователя",
            description = "Возвращает детальную информацию о конкретном пользователе"
    )
    @ApiResponse(responseCode = "200", description = "Пользователь найден")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @Parameter(description = "Уникальный идентификатор пользователя") @PathVariable("id") Long userId
    ) {
        log.info("Получен GET запрос на получение пользователя с id = {}", userId);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @Operation(
            summary = "Создать пользователя",
            description = "Администратор или система создает нового пользователя"
    )
    @ApiResponse(responseCode = "201", description = "Пользователь создан")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        log.info("Получен POST запрос на создание пользователя");
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Полное обновление пользователя",
            description = "Заменяет все поля пользователя."
    )
    @ApiResponse(responseCode = "200", description = "Пользователь обновлён")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @Parameter(description = "Уникальный идентификатор пользователя", example = "1", required = true)
            @PathVariable("id") Long userId,
            @Valid @RequestBody UserRequest userRequest
    ) {
        log.info("Получен PUT запрос на обновление пользователя с id = {}", userId);
        return new ResponseEntity<>(userService.updateUser(userId, userRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Удалить пользователя"
    )
    @ApiResponse(responseCode = "204", description = "Пользователь удален")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @Parameter(description = "Уникальный идентификатор пользователя", example = "1", required = true)
            @PathVariable("id") Long userId
    ) {
        log.info("Получен DELETE запрос на удаление пользователя с id = {}", userId);
        userService.deleteUser(userId);
    }
}
