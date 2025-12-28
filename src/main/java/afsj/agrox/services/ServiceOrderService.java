package afsj.agrox.services;

import afsj.agrox.dtos.ServiceOrderCreateDto;
import afsj.agrox.dtos.ServiceOrderItemCreateDto;
import afsj.agrox.dtos.ServiceOrderResponseDto;
import afsj.agrox.entities.Employee;
import afsj.agrox.entities.Product;
import afsj.agrox.entities.ServiceOrder;
import afsj.agrox.exceptions.ResourceNotFoundException;
import afsj.agrox.mapper.ServiceOrderMapper;
import afsj.agrox.repositories.EmployeeRepository;
import afsj.agrox.repositories.ProductRepository;
import afsj.agrox.repositories.ServiceOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOrderService {
   private final ServiceOrderRepository serviceOrderRepository;
   private final EmployeeRepository employeeRepository;
   private final ProductRepository productRepository;

   public ServiceOrderService(ServiceOrderRepository serviceOrderRepository,
                              EmployeeRepository employeeRepository,
                              ProductRepository productRepository) {
      this.serviceOrderRepository = serviceOrderRepository;
      this.employeeRepository = employeeRepository;
      this.productRepository = productRepository;
   }

   @Transactional(readOnly = true)
   public Page<ServiceOrderResponseDto> findAllOrders(Pageable pageable) {
      return serviceOrderRepository.findAll(pageable).map(so -> ServiceOrderMapper.toDto(so));
   }

   @Transactional(readOnly = true)
   public ServiceOrderResponseDto findOrderById(Long id) {
      ServiceOrder so = serviceOrderRepository.findById(id)
              .orElseThrow(ResourceNotFoundException::new);
      return ServiceOrderMapper.toDto(so);
   }

   @Transactional
   public ServiceOrderResponseDto createServiceOrder(ServiceOrderCreateDto dto) {
      Employee emp = employeeRepository.findById(dto.employeeId())
              .orElseThrow(ResourceNotFoundException::new);

      ServiceOrder so = ServiceOrderMapper.toEntity(dto, emp);

      var items = dto.items();
      if (items != null && !items.isEmpty()) {
         for (ServiceOrderItemCreateDto itemDto : dto.items()) {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(ResourceNotFoundException::new);
            so.addItem(product, itemDto.quantity());
         }
      }

      ServiceOrder saved = serviceOrderRepository.save(so);

      return ServiceOrderMapper.toDto(saved);
   }

   @Transactional
   public ServiceOrderResponseDto increaseItemQuantity(Long serviceOrderId, Long productId, int amount) {
      ServiceOrder so = serviceOrderRepository.findById(serviceOrderId)
              .orElseThrow(() -> new ResourceNotFoundException());

      Product product = productRepository.findById(productId)
              .orElseThrow(() -> new ResourceNotFoundException());

      so.increaseItemQuantity(product, amount);
      return ServiceOrderMapper.toDto(so);
   }

   @Transactional
   public ServiceOrderResponseDto decreaseItemQuantity(Long serviceOrderId, Long productId, int amount) {
      ServiceOrder so = serviceOrderRepository.findById(serviceOrderId)
              .orElseThrow(() -> new ResourceNotFoundException());

      Product product = productRepository.findById(productId)
              .orElseThrow(() -> new ResourceNotFoundException());

      so.decreaseItemQuantity(product, amount);
      return ServiceOrderMapper.toDto(so);
   }

   @Transactional
   public ServiceOrderResponseDto finishOrder(Long serviceOrderId) {
      ServiceOrder so = serviceOrderRepository.findById(serviceOrderId)
              .orElseThrow(() -> new ResourceNotFoundException());
      so.finish();

      for (var item : so.getServiceOrderItems()) {
         Product product = item.getProduct();
         product.decreaseStock(item.getQuantity());
      }

      return ServiceOrderMapper.toDto(so);
   }

   @Transactional
   public ServiceOrderResponseDto cancelOrder(Long serviceOrderId) {
      ServiceOrder so = serviceOrderRepository.findById(serviceOrderId)
              .orElseThrow(() -> new ResourceNotFoundException());

      so.cancel();
      return ServiceOrderMapper.toDto(so);
   }

   @Transactional
   public void deleteServiceOrder(Long id) {
      if (!serviceOrderRepository.existsById(id)) {
         throw new ResourceNotFoundException();
      }
      serviceOrderRepository.deleteById(id);
   }

}
