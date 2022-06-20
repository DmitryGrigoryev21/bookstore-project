package com.bookstore.bookrecommendation.services;


import com.bookstore.bookrecommendation.controllers.TagDTO;
import com.bookstore.bookrecommendation.entities.Tag;
import com.bookstore.bookrecommendation.entities.TagRepo;
import com.bookstore.bookrecommendation.mappers.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService{
    private final TagRepo tagRepo;

    @Autowired
    private TagMapper tagMapper;

    TagServiceImpl(TagRepo tagRepo){
        this.tagRepo=tagRepo;
    }

    @Override
    public List<TagDTO> findAllTags() {

        List<Tag> tags = (List<Tag>) tagRepo.findAll();
        List<TagDTO> tagDTOS = tagMapper.entityListToResponseModelList(tags);
        return tagDTOS;
    }

    @Override
    public TagDTO findByUUID(String Id) {
        Tag tag = tagRepo.findByBookUUID(Id);
        return tagMapper.entityToModel(tag);
    }

    @Override
    public List<TagDTO> findByBookUUID(String Id) {
        List<Tag> tag = (List<Tag>) tagRepo.findAllByBookUUID(Id);
        return tagMapper.entityListToResponseModelList(tag);
    }

    @Override
    public TagDTO saveTag(TagDTO tagDTO) {
        Tag tag = tagMapper.modelToEntity(tagDTO);
        tagRepo.save(tag);
        return tagMapper.entityToModel(tag);
    }

    @Override
    public List<String> uniqueTags(){
        List<Tag> tag = (List<Tag>) tagRepo.findAll();
        List<String> temp = new ArrayList<>();
        for (Tag x: tag){
            temp.add(x.getTag());
        }
        List<String> uniqueTags = temp.stream().distinct().collect(Collectors.toList());
        return uniqueTags;
    }

    @Override
    public List<String> recommendedIds(String Id){
        List<Tag> tag1 = (List<Tag>) tagRepo.findAllByBookUUID(Id);
        List<String> temp = new ArrayList<>();
        for (Tag x: tag1){
            List<Tag> tag2 = (List<Tag>) tagRepo.findAllByTag(x.getTag());
            for (Tag y: tag2){
                if (!y.getBookUUID().equals(x.getBookUUID()))
                temp.add(y.getBookUUID());
            }
        }
        List<String> recommended = temp.stream().distinct().collect(Collectors.toList());
        return recommended;
    }

    @Transactional
    @Override
    public boolean deleteTagByBookUUID(String Id) {
        if (tagRepo.existsByBookUUID(Id)) {
            tagRepo.deleteByBookUUID(Id);
            return true;
        }
        else{
            throw new EntityNotFoundException("Invalid bookUUID was provided.");
        }
    }

    @Override
    public TagDTO updateTagByBookUUID(String Id, TagDTO tagDTO) {
        if (tagRepo.existsByBookUUID(Id)) {
            Tag tag = tagRepo.findByBookUUID(Id);
            Tag tagTemp = tagMapper.modelToEntity(tagDTO);
            tag.setTag(tagTemp.getTag());
            tagRepo.save(tag);
            return tagMapper.entityToModel(tag);
        }
        else{
            throw new EntityNotFoundException("Invalid bookUUID was provided.");
        }
    }
}
