package com.example.streaming.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public class YoutubeConfig {

    @Getter
    @Value("${youtube.API}")
    private String apiKey;

    @Getter
    @Value("#{'${youtube.channel.ids}'.split(',')}")
    private List<String> channelIds;

}
