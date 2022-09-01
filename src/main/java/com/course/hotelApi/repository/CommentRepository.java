package com.course.hotelApi.repository;

import com.course.hotelApi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.hotelId = :id")
    List<Comment> comments(Integer id);

    @Query("SELECT c FROM Comment c WHERE c.hotelId = :hotelId AND c.id = :id")
    Comment getByIdAndHotelId(int hotelId, int id);
}
