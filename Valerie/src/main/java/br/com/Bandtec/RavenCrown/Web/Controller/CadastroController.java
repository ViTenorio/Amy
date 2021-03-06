package br.com.Bandtec.RavenCrown.Web.Controller;

import br.com.Bandtec.RavenCrown.Entity.EnderecoEntity;
import br.com.Bandtec.RavenCrown.Entity.UsuarioEntity;
import br.com.Bandtec.RavenCrown.Infra.Business.ImagemUsuarioBusiness;
import br.com.Bandtec.RavenCrown.Infra.Business.UsuarioBusiness;
import br.com.Bandtec.RavenCrown.Web.Model.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class CadastroController {

    @Autowired
    private UsuarioBusiness userBusiness;

    @Autowired
    private ImagemUsuarioBusiness imagemUsuarioBusiness;

    private final ModelMapper mapper;

    public CadastroController(){
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<UsuarioModel, UsuarioEntity>() {
            protected void configure(){
                map().setImagem(null);
                map().getImagem().setUsuario(map());
                map().setEmail(source.getEmail());
                map().setEstadoCivil(source.getEstadoCivil());
                map().setNome(source.getNome());
                map().setCpfCnpj(source.getCpfCnpj());
                map().setTelefone(source.getTelefone());
                map().getEndereco().setUsuario(map());
            }
        });
    }

    @CrossOrigin
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioModel> Cadastro (@RequestBody UsuarioModel user){
        UsuarioEntity entity = mapper.map(user,UsuarioEntity.class);
        if(userBusiness.Cadastro(entity) != null){
            user.setId(entity.getId());
            user.getEndereco().setId(entity.getEndereco().getId());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(user,HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PutMapping("/cadastro")
    public ResponseEntity<UsuarioModel> Atualizar (@RequestBody UsuarioModel user){
        UsuarioEntity entity = mapper.map(user,UsuarioEntity.class);
        if(userBusiness.Update(entity)){
            imagemUsuarioBusiness.InsertUserImage(user.imagem);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(user,HttpStatus.CONFLICT);
        }
    }
}
