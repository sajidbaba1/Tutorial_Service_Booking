package com.sajid.ServiceBookingSystem.service.impl;

import com.sajid.ServiceBookingSystem.dto.CreateServiceItemRequest;
import com.sajid.ServiceBookingSystem.dto.ServiceItemResponse;
import com.sajid.ServiceBookingSystem.entity.ServiceItem;
import com.sajid.ServiceBookingSystem.repository.ServiceItemRepository;
import com.sajid.ServiceBookingSystem.service.ServiceItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceItemServiceImpl implements ServiceItemService {

    private final ServiceItemRepository repository;

    public ServiceItemServiceImpl(ServiceItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ServiceItemResponse create(CreateServiceItemRequest request) {
        ServiceItem entity = new ServiceItem();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setActive(true);

        ServiceItem saved = repository.save(entity);
        return toResponse(saved);
    }

    @Override
    public List<ServiceItemResponse> list() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ServiceItemResponse toResponse(ServiceItem entity) {
        ServiceItemResponse dto = new ServiceItemResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
