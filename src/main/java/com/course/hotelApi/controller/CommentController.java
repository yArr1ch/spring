package com.course.hotelApi.controller;

import com.course.hotelApi.entity.Comment;
import com.course.hotelApi.dto.CommentDto;
import com.course.hotelApi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{hotelId}/comments")
    public List<CommentDto> getAllCommentsForHotel(@PathVariable Integer hotelId) {
        return commentService.getAllCommentsForHotel(hotelId);
    }

    @PostMapping("/{hotelId}/comment")
    public CommentDto addComment(@PathVariable Integer hotelId, @Valid @RequestBody Comment comment) {
        return commentService.addComment(hotelId, comment);
    }

    @GetMapping("{hotelId}/comment/{id}")
    public CommentDto getById(@PathVariable Integer hotelId, @PathVariable Integer id) {
        return commentService.getById(hotelId, id);
    }

    @DeleteMapping("{hotelId}/comment/{id}")
    public void delete(@PathVariable Integer hotelId, @PathVariable Integer id) {
        commentService.delete(hotelId, id);
    }

    @PutMapping("{hotelId}/comment/{id}")
    public CommentDto update(@PathVariable Integer hotelId, @PathVariable Integer id,
                             @Valid @RequestBody Comment comment) {
        return commentService.update(hotelId, id, comment);
    }
}
