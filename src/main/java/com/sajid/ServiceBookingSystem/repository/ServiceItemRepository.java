package com.sajid.ServiceBookingSystem.repository;

import com.sajid.ServiceBookingSystem.entity.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
}
