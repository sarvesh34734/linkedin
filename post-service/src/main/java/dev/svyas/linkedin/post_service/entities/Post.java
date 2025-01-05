package dev.svyas.linkedin.post_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="posts")
public class Post extends Auditable{


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

}
