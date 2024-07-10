package com.example.musicgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track {
    private String id;
    private String name;
    private String uri;
    private Album album;
    private Artist[] artists;
    private int disc_number;
    private int duration_ms;
    private boolean explicit;
    private ExternalIds external_ids;
    private ExternalUrls external_urls;
    private String href;
    private boolean is_playable;
    private Restrictions restrictions;
    private int popularity;
    private String preview_url;
    private int track_number;
    private String type;
    private boolean is_local;

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Album {

        private String album_type;
        private int total_tracks;
        private String[] available_markets;
        private ExternalUrls external_urls;
        private String href;
        private String id;
        private Image[] images;
        private String name;
        private String release_date;
        private String release_date_precision;
        private Restrictions restrictions;
        private String type;
        private String uri;
        private Artist[] artists;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Artist {
        private ExternalUrls external_urls;
        private Followers followers;
        private String[] genres;
        private String href;
        private String id;
        private Image[] images;
        private String name;
        private int popularity;
        private String type;
        private String uri;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExternalIds {
        private String isrc;
        private String ean;
        private String upc;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExternalUrls {
        private String spotify;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {
        private String url;
        private int height;
        private int width;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Restrictions {
        private String reason;
    }

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Followers {
        private String href;
        private int total;
    }
}
