package com.manager.meetingManager.calendar;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Event")
public class Events {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @Column(name="start")
    private Date start;

    @Column(name="end")
    private Date end;

    @Override
    public String toString() {
        return "Event [id=" + id + ", title=" + title + ", description="
                + description + ", start=" + start + ", end=" + end + "]";
    }
}