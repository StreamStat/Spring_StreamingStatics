package com.example.streaming.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ChannelDTO {
    private String id; // 채널 ID
    private String title; // 채널 제목
    private String description; // 채널 설명
    private OffsetDateTime publishedAt; // 채널 생성일
    private String thumbnailUrl; // 채널 썸네일
    private long viewCount; // 영상 총 조회 수
    private long subscriberCount; // 총 구독자 수
    private long videoCount; // 총 비디오 업로드 수
    private List<String> topicCategories; // 주제 카테고리 URL (위키백과?)
}
