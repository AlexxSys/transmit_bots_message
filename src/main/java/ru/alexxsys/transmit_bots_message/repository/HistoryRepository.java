package ru.alexxsys.transmit_bots_message.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexxsys.transmit_bots_message.entity.History;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryRepository extends JpaRepository<History, UUID> {

}
