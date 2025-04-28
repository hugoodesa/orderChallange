package br.com.stapassoli.springboot_order_microservice.mapper;

import br.com.stapassoli.springboot_order_microservice.dto.OrderRequestDTO;
import br.com.stapassoli.springboot_order_microservice.entity.Order;
import br.com.stapassoli.springboot_order_microservice.entity.OrderItem;
import br.com.stapassoli.springboot_order_microservice.dto.OrderDTO;
import br.com.stapassoli.springboot_order_microservice.dto.OrderItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDTO order);
    OrderDTO toDTO(Order order);
    OrderItemDTO toDTO(OrderItem orderItem);

}
