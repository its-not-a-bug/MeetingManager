package com.manager.meetingManager.calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {


    @Query("select b from Events b " +
            "where b.start between ?1 and ?2 and b.end between ?1 and ?2")
    List<Events> findByDatesBetween(Date start, Date end);

}
