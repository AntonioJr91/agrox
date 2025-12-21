package afsj.agrox.controllers;

import afsj.agrox.dtos.ServiceOrderCreateDto;
import afsj.agrox.dtos.ServiceOrderItemUpdateQuantityDto;
import afsj.agrox.dtos.ServiceOrderResponseDto;
import afsj.agrox.services.ServiceOrderService;
import jakarta.validation.Valid;
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
   public ResponseEntity<List<ServiceOrderResponseDto>> getAll() {
      return ResponseEntity.ok().body(serviceOrderService.findAllOrders());
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
                                                                       @RequestBody ServiceOrderItemUpdateQuantityDto dto){

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

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable Long id) {
      serviceOrderService.deleteServiceOrder(id);
      return ResponseEntity.noContent().build();
   }
}
