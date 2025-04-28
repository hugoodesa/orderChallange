package br.com.stapassoli.springboot_order_microservice.repository;

import br.com.stapassoli.springboot_order_microservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
