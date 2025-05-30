package com.example.streaming.controller;

import com.example.streaming.dto.ChannelDTO;
import com.example.streaming.service.YoutubeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YoutubeController {

    private final YoutubeService youtubeService;

    public YoutubeController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping("/youtube/top-channels")
    public List<ChannelDTO> getTopChannels() {
        List<String> channelIds = List.of(
                "UC4rlAVgAK0SGk-yTfe48Qpw", // 진용진
                "UCsU-I-vHLiaMfV_ceaYz5rQ", // 피식대학
                "UCekSb5Ik_2vBnKnHwhfF7Rw"  // ENA
        );
        return youtubeService.getTopChannels(channelIds);
    }
}
