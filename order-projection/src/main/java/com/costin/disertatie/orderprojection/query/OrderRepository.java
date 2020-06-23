package com.costin.disertatie.orderprojection.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,String> {

    public List<OrderEntity> findOrderEntitiesByAccountId(String accountId);

    @Query(value = "SELECT oe FROM OrderEntity oe  WHERE oe.accountId = ?1 and oe.status <> 5")
    public List<OrderEntity> findOrderEntitiesByAccountNotClosed(String accountId);

    @Query(value = "SELECT count(oe) FROM OrderEntity oe WHERE oe.accountId = ?1 and oe.status <> 5")
    public Integer countAllByAccountIdNotClosed(String accountId);
}
