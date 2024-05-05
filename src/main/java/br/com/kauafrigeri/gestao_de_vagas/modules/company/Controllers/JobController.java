package br.com.kauafrigeri.gestao_de_vagas.modules.company.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kauafrigeri.gestao_de_vagas.modules.company.UseCases.CreateJobUseCase;
import br.com.kauafrigeri.gestao_de_vagas.modules.company.entities.JobEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity) {
        return this.createJobUseCase.execute(jobEntity);
    }
}
