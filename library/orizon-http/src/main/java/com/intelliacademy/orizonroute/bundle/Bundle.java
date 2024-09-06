package com.intelliacademy.orizonroute.bundle;

import java.util.List;

public class Bundle {
    private String status;
    private String language;
    private String service;
    private List<Content> contents;


    public record Content(String k, String v) {}
}
