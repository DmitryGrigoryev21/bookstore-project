package com.bookstore.bookrecommendation.services;


import com.bookstore.bookrecommendation.controllers.TagDTO;

import java.util.List;

public interface TagService {
    public List<TagDTO> findAllTags();
    public TagDTO findByUUID(String Id);
    public List<TagDTO> findByBookUUID(String Id);
    public TagDTO saveTag(TagDTO tagDTO);
    public List<String> uniqueTags();
    public List<String> recommendedIds(String Id);
    public boolean deleteTagByBookUUID(String Id);
    public TagDTO updateTagByBookUUID(String Id, TagDTO tagDTO);
}
