package com.auth.security.Repository;

import com.auth.security.Entity.CommandeClient;
import com.auth.security.Entity.CommandeFour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmdFourDAO extends JpaRepository <CommandeFour,Integer> {
    CommandeFour save(CommandeFour commande);
    Optional<CommandeFour> findById(Integer integer);
    @Query(value = "SELECT * FROM CommandeFour WHERE idEntreprise = ?", nativeQuery = true)
    List<CommandeFour> findCmdBySocietee (@Param("idEntreprise") Integer id);

}
