package com.auth.security.Repository;

import com.auth.security.Entity.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CmdClientDAO extends JpaRepository<CommandeClient,Integer> {
    CommandeClient save(CommandeClient commande);
    Optional<CommandeClient> findById(Integer integer);
    @Query(value = "SELECT * FROM CommandeClient  WHERE idEntreprise = ?", nativeQuery = true)
    List<CommandeClient> findCmdBySociete(@Param("idEntreprise") Integer id);
}
