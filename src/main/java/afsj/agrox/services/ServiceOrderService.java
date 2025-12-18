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
   public List<ServiceOrderResponseDto> findAllOrders() {
      return ServiceOrderMapper.toDtoList(serviceOrderRepository.findAll());
   }

   @Transactional(readOnly = true)
   public ServiceOrderResponseDto findOrderById(Long id) {
      ServiceOrder so = serviceOrderRepository.findById(id)
              .orElseThrow(ResourceNotFoundException::new);
      return ServiceOrderMapper.toDto(so);
   }

   @Transactional
   public ServiceOrderResponseDto createServiceOrder(ServiceOrderCreateDto dto) {
      Employee emp = employeeRepository.findById(dto.getEmployeeId())
              .orElseThrow(ResourceNotFoundException::new);

      ServiceOrder so = ServiceOrderMapper.toEntity(dto, emp);

      var items = dto.getItems();
      if (items != null && !items.isEmpty()) {
         for (ServiceOrderItemCreateDto itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(ResourceNotFoundException::new);
            so.addItem(product, itemDto.getQuantity());
         }
      }

      ServiceOrder saved = serviceOrderRepository.save(so);

      return ServiceOrderMapper.toDto(saved);

   }

   @Transactional
   public void deleteServiceOrder(Long id) {
      if (!serviceOrderRepository.existsById(id)) {
         throw new ResourceNotFoundException();
      }
      serviceOrderRepository.deleteById(id);
   }

}
