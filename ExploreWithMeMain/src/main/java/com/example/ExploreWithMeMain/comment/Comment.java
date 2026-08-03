package com.example.ExploreWithMeMain.comment;

import com.example.ExploreWithMeMain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "comments")
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false, length = 255)
    private String text;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
