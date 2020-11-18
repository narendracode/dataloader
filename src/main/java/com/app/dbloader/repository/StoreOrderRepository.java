package com.app.dbloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dbloader.entity.StoreOrder;

@Repository
public interface StoreOrderRepository extends JpaRepository<StoreOrder, Integer>{

}
