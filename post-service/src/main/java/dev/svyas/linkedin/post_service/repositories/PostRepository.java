package dev.svyas.linkedin.post_service.repositories;

import dev.svyas.linkedin.post_service.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUserId(Long userId);
}
