package br.com.stapassoli.springboot_order_microservice.mapper;

import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.entity.OrderItem;
import br.com.stapassoli.springboot_order_microservice.dto.OrderDTO;
import br.com.stapassoli.springboot_order_microservice.dto.OrderItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDTO order);
    OrderDTO toDTO(Order order);
    OrderItemDTO toDTO(OrderItem orderItem);

    @Mapping(target = "order", ignore = true)
    OrderItem toEntityWithoutOrder(OrderItemDTO orderItemDTO);
}
