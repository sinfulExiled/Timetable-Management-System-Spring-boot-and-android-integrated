package com.cb007727.EEATimeTableManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cb007727.EEATimeTableManager.models.Rooms;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

}
