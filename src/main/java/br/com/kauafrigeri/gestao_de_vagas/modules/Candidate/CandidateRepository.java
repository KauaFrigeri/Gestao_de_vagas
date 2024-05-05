package br.com.kauafrigeri.gestao_de_vagas.modules.Candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    
}