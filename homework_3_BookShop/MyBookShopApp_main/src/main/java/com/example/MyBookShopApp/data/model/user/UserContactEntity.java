package com.example.MyBookShopApp.data.model.user;


import com.example.MyBookShopApp.data.model.enums.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_contact")
public class UserContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id",columnDefinition = "INT NOT NULL")
    private int userId;

    private ContactType type;

    @Column(columnDefinition = "SMALLINT NOT NULL")
    private short approved;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String code;

    @Column(columnDefinition = "INT")
    private int codeTrails;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime codeTime;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String contact;

    @OneToOne(mappedBy = "userContact")
    @JsonIgnore
    private UserEntity user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public short getApproved() {
        return approved;
    }

    public void setApproved(short approved) {
        this.approved = approved;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCodeTrails() {
        return codeTrails;
    }

    public void setCodeTrails(int codeTrails) {
        this.codeTrails = codeTrails;
    }

    public LocalDateTime getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(LocalDateTime codeTime) {
        this.codeTime = codeTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
