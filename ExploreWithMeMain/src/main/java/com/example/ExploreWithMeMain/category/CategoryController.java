package com.example.ExploreWithMeMain.category;

import com.example.ExploreWithMeMain.bid.BidResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
@Tag(name = "categories", description = "Управление категориями событий")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(
            summary = "Список категорий",
            description = "Возвращает список всех категорий на события"
    )
    @ApiResponse(responseCode = "200", description = "Список категорий")
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        log.info("Получен GET запрос на получение всех категорий событий");
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @Operation(
            summary = "Получить детали категории",
            description = "Возвращает детальную информацию о конкретной категории на событие"
    )
    @ApiResponse(responseCode = "200", description = "Категория найдена")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Parameter(description = "Уникальный идентификатор категории") @PathVariable Long categoryId
    ) {
        log.info("Получен GET запрос на получение категории с id = {}", categoryId);
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @Operation(
            summary = "Создать категорию к событию",
            description = "Пользователь создает категорию на событие")
    @ApiResponse(responseCode = "201", description = "Категория создана")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @PostMapping
    public ResponseEntity<BidResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        log.info("Получен POST запрос на создание категории");
        return new ResponseEntity<>(categoryService.createBid(categoryRequest));
    }

    @Operation(
            summary = "Полное обновление категории",
            description = "Заменяет все поля категории."
    )
    @ApiResponse(responseCode = "200", description = "Категория обновлёна")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    @PutMapping(value = "/{categoryId}")
    public ResponseEntity<BidResponse> updateCategory(
            @Parameter(description = "Уникальный идентификатор категории", example = "1", required = true)
            @PathVariable Long categoryId,
            @Valid @RequestBody CategoryRequest categoryRequest
    ) {
        log.info("Получен PUT запрос на обновление заявки");
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, categoryRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Удалить категорию"
    )
    @ApiResponse(responseCode = "204", description = "Категория удалена")
    @ApiResponse(responseCode = "404", description = "Категория не найдена")
    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @Parameter(description = "Уникальный идентификатор категории", example = "1", required = true)
            @PathVariable Long categoryId
    ) {
        log.info("Получен DELETE запрос на удаление заявки с id = {}", categoryId);
        categoryService.deleteCategory(categoryId);
    }
}
