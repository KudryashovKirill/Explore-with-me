package com.example.ExploreWithMeMain.bid;

import com.example.ExploreWithMeMain.event.Event;
import com.example.ExploreWithMeMain.user.User;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "bids")
@Builder
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private BidStatus status;

    @Column(name = "count_of_places", nullable = false)
    private Integer countOfPlaces;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Event event;
}
