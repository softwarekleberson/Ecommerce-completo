package br.com.engenharia.projeto.ProjetoFinal.services.seguranca;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.engenharia.projeto.ProjetoFinal.entidades.user.UserEntity;
import br.com.engenharia.projeto.ProjetoFinal.persistencia.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public String authenticate(LoginRequest request) {
    	UserEntity user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

    	System.out.println(user.getEmail() + user.getNome());
    	
        if (!passwordEncoder.matches(request.getSenha(), user.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), request.getSenha())
        );

        return jwtService.generateToken(user);
    }
}
