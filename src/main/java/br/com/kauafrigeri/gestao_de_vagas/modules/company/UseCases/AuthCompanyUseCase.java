package br.com.kauafrigeri.gestao_de_vagas.modules.company.UseCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.kauafrigeri.gestao_de_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.kauafrigeri.gestao_de_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Company not found");
            }
        );

        // Se a company existir a gente precisa "Verificar a Senha" se a senha que o usuario está passasndo ela está cadastrada no meu banco de dados
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        // Se não for igual retorna -> erro
        if(!passwordMatches) {
            throw new AuthenticationException();
        }
        
        // Se for igual -> Gerar token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
         var token = JWT.create().withIssuer("javagas")
         .withSubject(company.getId().toString())
         .sign(algorithm);
         return token;
    }
}
