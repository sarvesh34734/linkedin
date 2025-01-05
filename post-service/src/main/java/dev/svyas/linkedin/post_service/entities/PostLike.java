package dev.svyas.linkedin.post_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="post_like")
public class PostLike extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id",nullable = false)
    private Long userId;

    @Column(name="post_id",nullable = false)
    private Long postId;

}
