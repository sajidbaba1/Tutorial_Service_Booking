package com.sajid.ServiceBookingSystem.controller;

import com.sajid.ServiceBookingSystem.dto.CreateServiceItemRequest;
import com.sajid.ServiceBookingSystem.dto.ServiceItemResponse;
import com.sajid.ServiceBookingSystem.service.ServiceItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/service-items")
public class ServiceItemController {

    private final ServiceItemService service;

    public ServiceItemController(ServiceItemService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServiceItemResponse> create(@RequestBody @Valid CreateServiceItemRequest request) {
        ServiceItemResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/service-items/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ServiceItemResponse>> list() {
        return ResponseEntity.ok(service.list());
    }
}
