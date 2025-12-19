package afsj.agrox.controllers;

import afsj.agrox.dtos.ProductCreateDto;
import afsj.agrox.dtos.ProductResponseDto;
import afsj.agrox.dtos.ProductUpdateDto;
import afsj.agrox.dtos.StockAmountDto;
import afsj.agrox.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

   private final ProductService productService;

   public ProductController(ProductService productService) {
      this.productService = productService;
   }

   @GetMapping
   public ResponseEntity<List<ProductResponseDto>> findAll() {
      return ResponseEntity.ok().body(productService.findAllProducts());
   }

   @GetMapping(value = "/{id}")
   public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
      return ResponseEntity.ok().body(productService.findProductById(id));
   }

   @PostMapping
   public ResponseEntity<ProductResponseDto> create(@RequestBody @Valid ProductCreateDto dto) {
      ProductResponseDto product = productService.createProduct(dto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
      return ResponseEntity.created(uri).body(product);
   }

   @PatchMapping(value = "/{id}")
   public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody @Valid ProductUpdateDto dto) {
      return ResponseEntity.ok().body(productService.updateDetails(id, dto));
   }

   @PatchMapping(value = "/{id}/stock/increase")
   public ResponseEntity<ProductResponseDto> increaseStock(@PathVariable Long id, @RequestBody @Valid StockAmountDto dto){
      return ResponseEntity.ok().body(productService.increaseProductAmount(id, dto.getAmount()));
   }

   @PatchMapping(value = "/{id}/stock/decrease")
   public ResponseEntity<ProductResponseDto> decreaseStock(@PathVariable Long id, @RequestBody @Valid StockAmountDto dto){
      return ResponseEntity.ok().body(productService.decreaseProductAmount(id, dto.getAmount()));
   }

   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {
      productService.deleteProduct(id);
      return ResponseEntity.noContent().build();
   }
}
