package com.example.streaming.service;

import com.example.streaming.dto.ChannelDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class YoutubeService {

    private static final String API_KEY = "AIzaSyCZ-rP_hCgDRP1Wf8KTAAAsqK61fxeSCz0";
    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/channels";

    public List<ChannelDTO> getTopChannels(List<String> channelIds) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("part", "snippet,statistics")
                .queryParam("id", String.join(",", channelIds))
                .queryParam("key", API_KEY)
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> items = (List<Map<String, Object>>) response.get("items");

        List<ChannelDTO> results = new ArrayList<>();

        for (Map<String, Object> item : items) {
            Map<String, Object> snippet = (Map<String, Object>) item.get("snippet");
            Map<String, Object> statistics = (Map<String, Object>) item.get("statistics");
            Map<String, Object> thumbnails = (Map<String, Object>) ((Map<String, Object>) snippet.get("thumbnails")).get("default");

            ChannelDTO dto = new ChannelDTO();
            dto.setId((String) item.get("id"));
            dto.setTitle((String) snippet.get("title"));
            dto.setThumbnailUrl((String) thumbnails.get("url"));

            if (statistics.get("subscriberCount") != null) {
                dto.setSubscriberCount(Long.parseLong((String) statistics.get("subscriberCount")));
            } else {
                dto.setSubscriberCount(0L);
            }

            results.add(dto);
        }

        // 구독자 수 내림차순 정렬
        results.sort(Comparator.comparingLong(ChannelDTO::getSubscriberCount).reversed());
        return results;
    }
}
