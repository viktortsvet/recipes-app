package com.viktor.recipebackend.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.viktor.recipebackend.serializers.Serializers;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    public Message() {}

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "text_message")
    private String textMessage;

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    private Date cDate = new Date();
    private Date uDate = new Date();

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }
    @Column(name = "cdate")
    @JsonSerialize(using = Serializers.DateTimeSerializer.class)
    @JsonDeserialize(using = Serializers.DateTimeDeserializer.class)
    public Date getcDate() {
        return cDate;
    }

    public void setuDate(Date cDate) {
        this.cDate = cDate;
    }
    @Column(name = "udate")
    @JsonSerialize(using = Serializers.DateTimeSerializer.class)
    @JsonDeserialize(using = Serializers.DateTimeDeserializer.class)
    public Date getuDate() {
        return uDate;
    }

    public UUID getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}