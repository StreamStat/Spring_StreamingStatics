package com.example.streaming.dto;

import lombok.Data;

@Data
public class ChannelDTO {
    private String id;
    private String title;
    private String thumbnailUrl;
    private long subscriberCount;


}
