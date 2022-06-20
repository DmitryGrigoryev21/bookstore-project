package com.bookstore.bookrecommendation.controllers;


import com.bookstore.bookrecommendation.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> listTags(){
        return ResponseEntity.status(HttpStatus.OK).body(tagService.findAllTags());
    }

    @GetMapping("/tags/{Id}")
    public ResponseEntity<List<TagDTO>> listTagsByBookUUID(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(tagService.findByBookUUID(Id));
    }

    @PostMapping("/tag")
    public ResponseEntity<TagDTO> newTag(@RequestBody TagDTO tagDTO)
    {
        try {
            TagDTO newTag = tagService.saveTag(tagDTO);
            return ResponseEntity.status(HttpStatus.OK).body(newTag);
        }
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/tags/unique")
    public ResponseEntity<List<String>> listUniqueTags(){
        return ResponseEntity.status(HttpStatus.OK).body(tagService.uniqueTags());
    }

    @GetMapping("/tags/{Id}/recommended")
    public ResponseEntity<List<String>> listRecommendedIds(@PathVariable String Id){
        return ResponseEntity.status(HttpStatus.OK).body(tagService.recommendedIds(Id));
    }

    @DeleteMapping("/tag/{Id}")
    public ResponseEntity<String> deleteTagByBookUUID(@PathVariable String Id) {
        boolean success = tagService.deleteTagByBookUUID(Id);
        String response;
        if (success)
            response = "Success";
        else
            response = "Aborted";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/tag/{Id}")
    public ResponseEntity<TagDTO> updateTagByBookUUID(@RequestBody TagDTO tagDTO, @PathVariable String Id) {
        return ResponseEntity.status(HttpStatus.OK).body(tagService.updateTagByBookUUID(Id,tagDTO));
    }
}
