package com.example.ExploreWithMeMain.bid;

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

@Slf4j
@RestController
@RequestMapping("/api/bids")
@Tag(name = "bids", description = "Управление заявками на события")
public class BidController {
    private final BidService bidService;

    @Operation(
            summary = "Список заявок",
            description = "Возвращает список заявок на события"
    )
    @ApiResponse(responseCode = "200", description = "Список заявок")
    @GetMapping
    public ResponseEntity<List<BidResponse>> getAllBids() {
        log.info("Получен GET запрос на получение всех заявок");
        return new ResponseEntity<>(bidService.getAllBids(), HttpStatus.OK);
    }

    @Operation(
            summary = "Получить детали заявки",
            description = "Возвращает детальную информацию о конкретной заявке на событие"
    )
    @ApiResponse(responseCode = "200", description = "Заявка найдена")
    @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    @GetMapping("/{bidId}")
    public ResponseEntity<BidResponse> getBidById(
            @Parameter(description = "Уникальный идентификатор заявки") @PathVariable Long bidId
    ) {
        log.info("Получен GET запрос на получение заявки с id = {}", bidId);
        return new ResponseEntity<>(bidService.getBidById(bidId), HttpStatus.OK);
    }

    @Operation(
            summary = "Создать заявку на событие",
            description = "Пользователь создает заявку на событие. Статус по умолчанию — NEW")
    @ApiResponse(responseCode = "201", description = "Заявка создана")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации или нет свободных мест")
    @PostMapping
    public ResponseEntity<BidResponse> createBid(@Valid @RequestBody BidRequest bidRequest) {
        log.info("Получен POST запрос на создание заявки");
        return new ResponseEntity<>(bidService.createBid(bidRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Полное обновление заявки",
            description = "Заменяет все поля заявки."
    )
    @ApiResponse(responseCode = "200", description = "Заявка обновлёна")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    @PutMapping(value = "/{bidId}")
    public ResponseEntity<BidResponse> updateBid(
            @Parameter(description = "Уникальный идентификатор заявки", example = "1", required = true)
            @PathVariable Long bidId,
            @Valid @RequestBody BidRequest bidRequest
    ) {
        log.info("Получен PUT запрос на обновление заявки");
        return new ResponseEntity<>(bidService.updateBid(bidId, bidRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Удалить заявку"
    )
    @ApiResponse(responseCode = "204", description = "Заявка отменена")
    @ApiResponse(responseCode = "404", description = "Заявка не найдена")
    @DeleteMapping("/{bidId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBid(
            @Parameter(description = "Уникальный идентификатор заявки", example = "1", required = true)
            @PathVariable Long bidId
    ) {
        log.info("Получен DELETE запрос на удаление заявки с id = {}", bidId);
        bidService.deleteBid(bidId);
    }

    @Operation(
            summary = "Частичное обновление заявки(только статус) (PATCH)",
            description = """
                    Обновляет только переданный статус заявки.
                    """
    )
    @ApiResponse(responseCode = "200", description = "Статус заявки обновлен")
    @ApiResponse(responseCode = "400", description = "Ошибка валидации")
    @ApiResponse(responseCode = "404", description = "заявки не найдена")
    @PatchMapping(value = "/{bidId}/status")
    public ResponseEntity<BidResponse> updateBidStatus(
            @Parameter(description = "Уникальный идентификатор заявки", example = "1", required = true)
            @PathVariable Long bidId,
            @Parameter(description = "Обновленный статус заявки", required = true)
            @RequestParam BidStatus status
    ) {
        log.info("Получен PATCH запрос на обновление заявки с id = {}", bidId);
        return new ResponseEntity<>(bidService.updateBidStatus(bidId, status));
    }
}
