package com.costin.disertatie.orderprojection.query;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrdersProjection,String> {
}
