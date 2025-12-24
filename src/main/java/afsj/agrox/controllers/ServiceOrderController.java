package afsj.agrox.controllers;

import afsj.agrox.dtos.ServiceOrderCreateDto;
import afsj.agrox.dtos.ServiceOrderItemUpdateQuantityDto;
import afsj.agrox.dtos.ServiceOrderResponseDto;
import afsj.agrox.services.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class ServiceOrderController {

   private final ServiceOrderService serviceOrderService;

   public ServiceOrderController(ServiceOrderService serviceOrderService) {
      this.serviceOrderService = serviceOrderService;
   }

   @GetMapping
   public ResponseEntity<Page<ServiceOrderResponseDto>> getAll(Pageable pageable) {
      return ResponseEntity.ok().body(serviceOrderService.findAllOrders(pageable));
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<ServiceOrderResponseDto> getById(@PathVariable Long id) {
      return ResponseEntity.ok().body(serviceOrderService.findOrderById(id));
   }

   @PostMapping
   public ResponseEntity<ServiceOrderResponseDto> create(@RequestBody @Valid ServiceOrderCreateDto dto) {
      ServiceOrderResponseDto so = serviceOrderService.createServiceOrder(dto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(so.getId()).toUri();
      return ResponseEntity.created(uri).body(so);
   }

   @PatchMapping("/{orderId}/items/{productId}/increase")
   public ResponseEntity<ServiceOrderResponseDto> increaseItemQuantity(@PathVariable Long orderId,
                                                                       @PathVariable Long productId,
                                                                       @RequestBody @Valid ServiceOrderItemUpdateQuantityDto dto){

      ServiceOrderResponseDto response = serviceOrderService.increaseItemQuantity(orderId, productId, dto.getAmount());
      return ResponseEntity.ok().body(response);
   }

   @PatchMapping("/{orderId}/items/{productId}/decrease")
   public ResponseEntity<ServiceOrderResponseDto> decreaseItemQuantity(@PathVariable Long orderId,
                                                                       @PathVariable Long productId,
                                                                       @RequestBody ServiceOrderItemUpdateQuantityDto dto){

      ServiceOrderResponseDto response = serviceOrderService.decreaseItemQuantity(orderId, productId, dto.getAmount());
      return ResponseEntity.ok().body(response);
   }

   @PatchMapping("/{orderId}/finish")
   public  ResponseEntity<ServiceOrderResponseDto> finishOrder(@PathVariable Long orderId){
      return ResponseEntity.ok().body(serviceOrderService.finishOrder(orderId));
   }

   @PatchMapping("/{orderId}/cancel")
   public  ResponseEntity<ServiceOrderResponseDto> cancelOrder(@PathVariable Long orderId){
      return ResponseEntity.ok().body(serviceOrderService.cancelOrder(orderId));
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable Long id) {
      serviceOrderService.deleteServiceOrder(id);
      return ResponseEntity.noContent().build();
   }
}
