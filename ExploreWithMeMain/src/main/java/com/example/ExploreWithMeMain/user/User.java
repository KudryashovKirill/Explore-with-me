package com.example.ExploreWithMeMain.user;

import com.example.ExploreWithMeMain.comment.Comment;
import com.example.ExploreWithMeMain.event.Event;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Table(name = "users")
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false, length = 250)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 250)
    private String lastname;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;
    @Column(name = "is_admin", nullable = false)
    private Boolean idAdmin;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<Event> events; // события созданные пользователем

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Boolean getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Boolean idAdmin) {
        this.idAdmin = idAdmin;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}

