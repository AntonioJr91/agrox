package afsj.agrox.services;

import afsj.agrox.dtos.ProductCreateDto;
import afsj.agrox.dtos.ProductResponseDto;
import afsj.agrox.dtos.ProductUpdateDto;
import afsj.agrox.entities.Category;
import afsj.agrox.entities.Product;
import afsj.agrox.exceptions.ResourceNotFoundException;
import afsj.agrox.mapper.ProductMapper;
import afsj.agrox.repositories.CategoryRepository;
import afsj.agrox.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;

   public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
      this.productRepository = productRepository;
      this.categoryRepository = categoryRepository;
   }

   @Transactional(readOnly = true)
   public Page<ProductResponseDto> findAllProducts(Pageable pageable) {
      return productRepository.findAll(pageable).map(product -> ProductMapper.toDto(product));
   }

   @Transactional(readOnly = true)
   public ProductResponseDto findProductById(Long id) {
      Product product = productRepository.findById(id).orElseThrow((() -> new ResourceNotFoundException()));
      return ProductMapper.toDto(product);
   }

   @Transactional
   public ProductResponseDto createProduct(ProductCreateDto dto) {
      Category category = categoryRepository.findById(dto.getCategoryId())
              .orElseThrow(() -> new ResourceNotFoundException());
      Product product = ProductMapper.toEntity(dto, category);

      Product saved = productRepository.save(product);
      return ProductMapper.toDto(saved);
   }

   @Transactional
   public ProductResponseDto updateDetails(Long id, ProductUpdateDto dto) {
      Product product = productRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException());

      Category category = categoryRepository.findById(dto.getCategoryId())
              .orElseThrow(() -> new ResourceNotFoundException());

      product.updateDetails(
              dto.getName(),
              dto.getUnitOfMeasure(),
              category
      );
      return ProductMapper.toDto(product);
   }

   @Transactional
   public ProductResponseDto increaseProductAmount(Long productId, int amount) {
      Product product = productRepository.findById(productId)
              .orElseThrow(() -> new ResourceNotFoundException());

      product.increaseStock(amount);
      return ProductMapper.toDto(product);

   }

   @Transactional
   public ProductResponseDto decreaseProductAmount(Long productId, int amount) {
      Product product = productRepository.findById(productId)
              .orElseThrow(() -> new ResourceNotFoundException());

      product.decreaseStock(amount);
      return ProductMapper.toDto(product);
   }

   @Transactional
   public void deleteProduct(Long id) {
      if (!productRepository.existsById(id)) {
         throw new ResourceNotFoundException();
      }
      productRepository.deleteById(id);
   }

}
