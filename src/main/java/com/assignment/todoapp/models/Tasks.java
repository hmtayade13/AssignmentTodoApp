package com.assignment.todoapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String user;

    private String title;

    private String content;

    private Date updated;

    private boolean status;

    public Tasks() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Tasks(String title, String content, Date updated) {
        super();
        this.title = title;
        this.content = content;
        this.updated = updated;
    }

    public Tasks(String user, String title, String content, Date updated) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.updated = updated;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", updated=" + updated +
                ", status='" + status + '\'' +
                '}';
    }
}
