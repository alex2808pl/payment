package de.telran.payment.controller;

import de.telran.payment.dto.PurchaseOrderDto;
import de.telran.payment.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/purchase_order")
public class PurchaseOrderController {
    private final PurchaseOrderService purchaseOrderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseOrderDto> getPurchaseOrder() {
        return purchaseOrderService.getPurchaseOrder();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseOrderDto getPurchaseOrderById(@PathVariable Long id) {
        return purchaseOrderService.getPurchaseOrderById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchaseOrderById(@PathVariable Long id) {
        purchaseOrderService.deletePurchaseOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PurchaseOrderDto insertPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return purchaseOrderService.insertPurchaseOrder(purchaseOrderDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PurchaseOrderDto updatePurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return purchaseOrderService.updatePurchaseOrder(purchaseOrderDto);
    }
}


