package com.bookstore.bookrecommendation.services;

import com.bookstore.bookrecommendation.controllers.TagDTO;
import com.bookstore.bookrecommendation.entities.Tag;
import com.bookstore.bookrecommendation.entities.TagRepo;
import com.bookstore.bookrecommendation.mappers.TagMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
class TagServiceImplTest {
    @Autowired
    TagService tagService;

    @MockBean
    TagRepo repo;

    @SpyBean
    TagMapper mapper;

    @AfterEach
    void tearDown() {

        repo.deleteAll();
    }

    @Test
    void findAll() {

        List<TagDTO> tagDTOS;

        tagDTOS = tagService.findAllTags();

        assertNotNull(tagDTOS);
    }

    @Test
    void findTag() {

        Tag tag = new Tag();
        TagDTO model;
        tag.setBookUUID("3425");
        tag.setTag("dsge");

        repo.save(tag);

        when(repo.save(any(Tag.class))).thenAnswer(i -> i.getArguments()[0]);
        when(repo.existsByBookUUID(tag.getBookUUID())).thenReturn(true);
        when(repo.findByBookUUID(tag.getBookUUID())).thenReturn(tag);

        model = tagService.findByUUID(tag.getBookUUID());

        assertNotNull(model);
    }
    @Test
    void newTag() {

        TagDTO tagDTO = new TagDTO();
        TagDTO model;

        tagDTO.setBookUUID("352");
        tagDTO.setTag("gergherh");

        model = tagService.saveTag(tagDTO);

        assertNotNull(model);


    }
}