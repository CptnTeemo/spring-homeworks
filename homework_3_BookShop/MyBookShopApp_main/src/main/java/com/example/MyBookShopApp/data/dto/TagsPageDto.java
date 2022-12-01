package com.example.MyBookShopApp.data.dto;

import com.example.MyBookShopApp.data.model.book.TagEntity;

import java.util.List;

public class TagsPageDto {

    private Integer count;
    private List<TagEntity> tags;

    public TagsPageDto(List<TagEntity> tags) {
        this.tags = tags;
        this.count = tags.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
}
