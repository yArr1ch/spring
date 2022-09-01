package com.course.hotelApi.service;

import com.course.hotelApi.dto.CommentDto;
import com.course.hotelApi.dto.HotelDto;
import com.course.hotelApi.dto.UserDto;
import com.course.hotelApi.entity.*;
import com.course.hotelApi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final HotelService hotelService;

    public List<CommentDto> getAllCommentsForHotel(Integer hotelId) {
        List<CommentDto> commentDtoList = map(commentRepository.comments(hotelId));

        commentDtoList.forEach(c -> {
            Hotel hotel = hotelService.getById(hotelId);
            HotelDto h = hotelService.map(hotel);
            c.setHotel(h);

            Integer commentId = commentDtoList.stream().mapToInt(CommentDto::getCommentId).sum();
            User user = userService.findById(commentId);
            UserDto u = userService.map(user);
            c.setSentBy(u);
        });
        return commentDtoList;
    }

    public CommentDto addComment(Integer hotelId, Comment comment) {
        hotelService.getById(hotelId);
        comment.setHotelId(hotelId);

        commentRepository.save(comment);

        CommentDto commentDto = map(comment);

        Hotel hotel = hotelService.getById(hotelId);
        HotelDto h = hotelService.map(hotel);
        commentDto.setHotel(h);

        return commentDto;
    }

    public CommentDto getById(Integer hotelId, Integer id) {
        hotelService.getById(hotelId);
        Comment comment = commentRepository.getByIdAndHotelId(hotelId, id);

        CommentDto commentDto = map(comment);

        Hotel hotel = hotelService.getById(hotelId);
        HotelDto h = hotelService.map(hotel);
        commentDto.setHotel(h);

        return commentDto;
    }

    private CommentDto map(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setMessage(comment.getMessage());
        commentDto.setCommentId(comment.getId());
        commentDto.setSentAt(comment.getSentAt());
        User user = userService.findById(comment.getUserId());
        UserDto u = userService.map(user);
        commentDto.setSentBy(u);
        return commentDto;
    }

    private List<CommentDto> map(List<Comment> comments) {
        return comments.stream().map(c -> new CommentDto(c.getId(), c.getMessage(), c.getSentAt()))
                .collect(Collectors.toList());
    }

    public void delete(Integer hotelId, Integer id) {
        Comment c = commentRepository.getByIdAndHotelId(hotelId, id);
        commentRepository.delete(c);
    }

    public CommentDto update(Integer hotelId, Integer id, Comment comment) {
        commentRepository.getByIdAndHotelId(hotelId, id);
        comment.setId(id);
        comment.setHotelId(hotelId);

        return addComment(hotelId, comment);
    }
}
