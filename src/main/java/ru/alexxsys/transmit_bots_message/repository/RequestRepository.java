package ru.alexxsys.transmit_bots_message.repository;

import ru.alexxsys.transmit_bots_message.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {

    Request findTop1ByTimeStampNotNullOrderByTimeStamp();
    Request findTop1ByPatchEndsWithOrderByTimeStamp(String PatchEndsWith);

//    @Query("select b from requests b where b.name = :name")
//    Request dfgdfg(@Param("name") String name);

}
