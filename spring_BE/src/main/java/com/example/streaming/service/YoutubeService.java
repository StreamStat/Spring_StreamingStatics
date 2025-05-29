package com.example.streaming.service;

import com.example.streaming.config.YoutubeConfig;
import com.example.streaming.dto.ChannelDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.OffsetDateTime;
import java.util.*;

@Service
public class YoutubeService {

    private final YoutubeConfig config;

    //생성자 주입으로 config 객체 받기
    //application.properties 에서 설정값을 읽기위함
    public YoutubeService(YoutubeConfig config) {
        this.config = config;
    }
    
    public List<ChannelDTO> getTopChannels() {
        RestTemplate restTemplate = new RestTemplate(); // spring HTTP요청 도구

        //youtube Data API 요청 URL을 동적으로 생성
        String url = UriComponentsBuilder.fromHttpUrl("https://www.googleapis.com/youtube/v3/channels")
                .queryParam("part", "snippet,statistics,topicDetails") //어떤 정보를 포함할지
                .queryParam("id", String.join(",", config.getChannelIds())) // 조회할 채널 ID들
                .queryParam("key", config.getApiKey()) // API키
                .toUriString(); // StringURL 완성

        //API 호출 (GET 요청으로 JSON 데이터를 받아 Map으로 파싱)
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        
        //응답 객체에서 "items" 배열 꺼내기
        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

        //최종 결과 저장을 위한 리스트
        List<ChannelDTO> results = new ArrayList<>();

        // 지정한 채널 정보를 순회하면서 DTO에 담기
        for (Map<String, Object> item : items) {
            //공식문서에 적혀있는 필요한 정보 꺼내기
            Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
            Map<String, Object> statistics = (Map<String, Object>) item.get("statistics");
            Map<String, Object> thumbnails = (Map<String, Object>) ((Map<String, Object>) snippet.get("thumbnails")).get("default");
            Map<String, Object> topicDetails = (Map<String, Object>) item.get("topicDetails");

            //채널 하나의 정보를 담을 DTO 객체 생성
            ChannelDTO dto = new ChannelDTO();

            //기본 정보 세팅
            dto.setId((String) item.get("id"));
            dto.setTitle((String) snippet.get("title"));
            dto.setDescription((String) snippet.get("description"));

            //공식문서에 날짜 ISO 8601 형식이니 offsetDateTime 으로 파싱
            if (snippet.get("publishedAt") != null) {
                dto.setPublishedAt(OffsetDateTime.parse((String) snippet.get("publishedAt")));
            }
            dto.setThumbnailUrl((String) thumbnails.get("url"));

            //통계 정보는 문자열로 오기 때문에 미리 long 으로 변환
            dto.setSubscriberCount(parseLongSafe(statistics.get("subscriberCount")));
            dto.setViewCount(parseLongSafe(statistics.get("viewCount")));
            dto.setVideoCount(parseLongSafe(statistics.get("videoCount")));

            //topicDetails(위키백과?) 항목이 존재할 경우 카테고리 정보 세팅
            if (topicDetails != null && topicDetails.get("topicCategories") instanceof List) {
                dto.setTopicCategories((List<String>) topicDetails.get("topicCategories"));
            }

            //하나의 채널 정보를 리스트에 추가함
            results.add(dto);
        }

        //구독자 수 시준으로 내림차순 정렬
        results.sort(Comparator.comparingLong(ChannelDTO::getSubscriberCount).reversed());

        return results;
    }

    // 문자열 형태로 온 숫자들을 long 타입으로 파싱하는 함수
    private long parseLongSafe(Object value) {
        try {
            return value != null ? Long.parseLong(value.toString()) : 0L;
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
