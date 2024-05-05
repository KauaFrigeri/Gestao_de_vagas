package br.com.kauafrigeri.gestao_de_vagas.modules.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kauafrigeri.gestao_de_vagas.modules.UserFoundException;
import br.com.kauafrigeri.gestao_de_vagas.modules.Candidate.CandidateEntity;
import br.com.kauafrigeri.gestao_de_vagas.modules.Candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;


    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });
        
        return this.candidateRepository.save(candidateEntity);
        

        // System.out.println("Candidato");
        // System.out.println(candidateEntity.getEmail());
    }
        
    }
