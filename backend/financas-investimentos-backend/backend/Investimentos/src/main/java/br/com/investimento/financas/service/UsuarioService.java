package br.com.investimento.financas.service;

import br.com.investimento.financas.dto.TokenDto;
import br.com.investimento.financas.dto.UsuarioDto;
import br.com.investimento.financas.exception.NegocioException;
import br.com.investimento.financas.exception.NotFoundException;
import br.com.investimento.financas.model.Usuario;
import br.com.investimento.financas.repository.UsuarioRepository;
import br.com.investimento.financas.security.jwt.JwtService;
import br.com.investimento.financas.utils.constants.Constants;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    /*
    * Código de criptografia comentado para inserção
    * de dados na inicialização da aplicação.
    * */

    public void cadastrar(UsuarioDto dto) {
        this.validar(dto.getEmail(), dto.getUsername());

        Usuario entidade = new Usuario(dto);
//        if(entidade.getId() != null) {
//            Usuario usuarioDB = usuarioRepository
//                    .findById(entidade.getId())
//                    .orElseThrow(() -> new NotFoundException(Constants.USUARIO_NAO_ENCONTRADO));
//            entidade.setPassword(usuarioDB.getPassword());
//        } else {
//            this.criptografarSenha(entidade);
//        }

        this.usuarioRepository.save(entidade);
    }

    private void validar(String email, String username) {
        if (this.usuarioRepository.findByEmail(email) != null)
            throw new NegocioException(Constants.EMAIL_CADASTRADO);

        if (this.usuarioRepository.findByUsername(username) != null)
            throw new NegocioException(Constants.USERNAME_CADASTRADO);
    }

    /*
     * Código de criptografia comentado para inserção
     * de dados na inicialização da aplicação.
     * */

    public UsuarioDto autenticar(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if(usuario == null)
            throw new NotFoundException(Constants.USUARIO_INVALIDO);
        UsuarioDto usuarioDto = new UsuarioDto(usuario);
//        boolean validarSenha = encoder.matches(password, usuario.getPassword());

//        if (!usuarioDto.getUsername().equals(username) || !validarSenha) {
//            throw new NegocioException(Constants.DADOS_INVALIDO);
//        }

        if(!usuarioDto.getUsername().equals(username) || !usuarioDto.getPassword().equals(password)) {
            throw new NegocioException(Constants.DADOS_INVALIDO);
        }

        return usuarioDto;
    }

    public TokenDto autenticarToken(UsuarioDto dto) {
        UsuarioDto usuario = this.autenticar(dto.getUsername(), dto.getPassword());
        String token = jwtService.gerarToken(usuario);
        return new TokenDto(usuario.getNome(), usuario.getId(), token);
    }

    public List<UsuarioDto> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDto::new)
                .collect(Collectors.toList());
    }

    public Usuario buscarPorId(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.USUARIO_NAO_ENCONTRADO));
    }

    private void criptografarSenha(Usuario usuario) {
        String senhaCripto = encoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCripto);
    }
}
