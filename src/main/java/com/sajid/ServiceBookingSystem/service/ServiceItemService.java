package com.sajid.ServiceBookingSystem.service;

import com.sajid.ServiceBookingSystem.dto.CreateServiceItemRequest;
import com.sajid.ServiceBookingSystem.dto.ServiceItemResponse;

import java.util.List;

public interface ServiceItemService {
    ServiceItemResponse create(CreateServiceItemRequest request);
    List<ServiceItemResponse> list();
}
