package com.course.hotelApi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Integer commentId;

    private UserDto sentBy;

    private String message;

    private LocalDateTime sentAt;

    private HotelDto hotel;

    public CommentDto(Integer commentId, String message, LocalDateTime sentAt) {
        this.commentId = commentId;
        this.message = message;
        this.sentAt = sentAt;
    }
}
