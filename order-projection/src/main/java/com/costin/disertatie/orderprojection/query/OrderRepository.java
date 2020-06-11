package com.costin.disertatie.orderprojection.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,String> {

    public List<OrderEntity> findOrderEntitiesByAccountId(String accountId);
}
