package com.capg.tracking.controller;

import com.capg.tracking.dto.Response;
import com.capg.tracking.entity.Tracking;
import com.capg.tracking.service.TrackingService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final TrackingService service;

    public TrackingController(TrackingService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{deliveryId}")
    public List<Response> getTracking(@PathVariable("deliveryId") Long deliveryId) {
        return service.getTracking(deliveryId);
    }

    @PostMapping("/{deliveryId}/upload")
    public String upload(@PathVariable("deliveryId") Long deliveryId,
                         @RequestParam("file") MultipartFile file) throws Exception {

        service.uploadDocument(deliveryId, file);
        return "Uploaded successfully";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{trackingId}/download")
    public ResponseEntity<byte[]> download(@PathVariable("trackingId") Long trackingId) {

        Tracking t = service.getById(trackingId);

        String fileType = t.getFileType() != null && !t.getFileType().isEmpty() 
            ? t.getFileType() 
            : "application/octet-stream";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + t.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(fileType))
                .body(t.getDocument());
    }
}