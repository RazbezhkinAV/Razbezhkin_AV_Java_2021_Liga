package com.razbejkin.lesson10.repo;

import com.razbejkin.lesson10.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PostRepo extends JpaRepository<Posts, UUID> {
}
