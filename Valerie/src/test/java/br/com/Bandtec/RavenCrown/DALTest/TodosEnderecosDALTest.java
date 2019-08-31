package br.com.Bandtec.RavenCrown.DALTest;

import br.com.Bandtec.RavenCrown.Entity.EnderecoEntity;
import br.com.Bandtec.RavenCrown.Entity.UsuarioEntity;
import br.com.Bandtec.RavenCrown.Infra.DAL.TodosEnderecosDAL;
import br.com.Bandtec.RavenCrown.Infra.DAL.TodosUsuariosDAL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class TodosEnderecosDALTest {

    @Autowired
    private TodosEnderecosDAL todosEnderecosDAL;
    private EnderecoEntity endereco;
    private UsuarioEntity usuario;

    @Autowired
    private TodosUsuariosDAL todosUsuarios;

    @Test
    public void obterEndereco(){
        List<EnderecoEntity> endereco  = todosEnderecosDAL.findAll();

        assertFalse(endereco.isEmpty());
    }

    @Test
    public void persitirEndereco(){
        endereco = new EnderecoEntity();

        endereco.setBairro("Lapa");
        endereco.setCep("00000-000");
        endereco.setLougradouro("Logradouro da Lapa");
        endereco.setCidade("São Paulo");
        endereco.setComplemento("Predio Azul");
        endereco.setNumero("302");
        endereco.setPais("Brazil");
        endereco.setEstado("SP");
        endereco.setUsuario(todosUsuarios.getOne(10));

        todosEnderecosDAL.save(endereco);
        EnderecoEntity enderecoSalvo = todosEnderecosDAL.getOne(endereco.getId());

        assertEquals(enderecoSalvo, endereco);

    }

}
