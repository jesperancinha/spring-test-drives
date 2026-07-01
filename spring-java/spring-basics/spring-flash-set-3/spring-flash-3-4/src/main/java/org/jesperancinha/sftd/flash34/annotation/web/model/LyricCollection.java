package org.jesperancinha.sftd.flash34.annotation.web.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LyricCollection {

    public String band;

    public List<String> lyrics = new ArrayList<>();
}
