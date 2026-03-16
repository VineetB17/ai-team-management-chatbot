package com.teamchatbot.backend.repository;

import com.teamchatbot.backend.dto.TopTeamDTO;
import com.teamchatbot.backend.dto.TopUserDTO;
import com.teamchatbot.backend.entity.TransactionData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionDataRepository extends JpaRepository<TransactionData, Long> {

    List<TransactionData> findByUserUserId(Long userId);

    List<TransactionData> findByTeamTeamId(Long teamId);

    @Query("SELECT SUM(t.dataUsedMb) FROM TransactionData t WHERE t.user.userId = :userId")
    Double getTotalDataByUser(@Param("userId") Long userId);

    @Query("SELECT SUM(t.dataUsedMb) FROM TransactionData t WHERE t.team.teamId = :teamId")
    Double getTotalDataByTeam(@Param("teamId") Long teamId);


    @Query("""
SELECT new com.teamchatbot.backend.dto.TopUserDTO(
    u.userId,
    u.username,
    (SUM(t.dataUsedMb) * 1.0)
                     
)
FROM TransactionData t
JOIN t.user u
GROUP BY u.userId, u.username
ORDER BY SUM(t.dataUsedMb) DESC
""")
    List<TopUserDTO> findTopUsers(Pageable pageable);

    @Query("""
SELECT new com.teamchatbot.backend.dto.TopTeamDTO(
    t.team.teamId,
    t.team.teamName,
    (SUM(t.dataUsedMb)*1.0)
)
FROM TransactionData t
WHERE t.team IS NOT NULL
GROUP BY t.team.teamId, t.team.teamName
ORDER BY SUM(t.dataUsedMb) DESC
""")
    List<TopTeamDTO> findTopTeams(Pageable pageable);


}
