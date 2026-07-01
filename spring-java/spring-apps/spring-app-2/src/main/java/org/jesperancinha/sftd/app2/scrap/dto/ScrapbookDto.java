package org.jesperancinha.sftd.app2.scrap.dto;

import org.jesperancinha.sftd.app2.scrap.model.Scrapbook;
import org.jesperancinha.sftd.app2.scrap.model.ScrapbookType;

public class ScrapbookDto {

    private String name;

    private ScrapbookType scrapbookType;

    private Integer pages;

    private Long id;

    public ScrapbookDto() {

    }

    public ScrapbookDto(final Scrapbook scrapbook) {
        this.name = scrapbook.getName();
        this.scrapbookType = scrapbook.getScrapbookType();
        this.pages = scrapbook.getPages();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScrapbookType getScrapbookType() {
        return scrapbookType;
    }

    public void setScrapbookType(ScrapbookType scrapbookType) {
        this.scrapbookType = scrapbookType;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "ScrapbookDto{" +
                "name='" + name + '\'' +
                ", scrapbookType=" + scrapbookType +
                ", pages=" + pages +
                ", id=" + id +
                '}';
    }
}
