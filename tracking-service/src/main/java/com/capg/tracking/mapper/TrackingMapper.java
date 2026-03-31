package com.capg.tracking.mapper;

import com.capg.tracking.dto.Response;
import com.capg.tracking.entity.Tracking;

public class TrackingMapper {

    public static Response toDTO(Tracking t) {
        Response dto = new Response();
        dto.setStatus(t.getStatus());
        dto.setLocation(t.getLocation());
        dto.setTimestamp(t.getTimestamp());
        return dto;
    }
}