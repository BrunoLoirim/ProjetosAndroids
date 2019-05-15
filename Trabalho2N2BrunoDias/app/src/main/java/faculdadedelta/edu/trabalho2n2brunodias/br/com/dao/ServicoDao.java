package faculdadedelta.edu.trabalho2n2brunodias.br.com.dao;

import java.util.ArrayList;
import java.util.List;

import faculdadedelta.edu.trabalho2n2brunodias.br.com.modelo.Servico;

public class ServicoDao {

    private static List<Servico> listaServico = new ArrayList<Servico>();
    private static Long idGerator = 1L;

    public void incluir(Servico servico) {
        servico.setId(idGerator++);
        listaServico.add(servico);
    }

    public void excluir(Servico servico) {
        listaServico.remove(servico);
    }

    public List<Servico> listar() {
        return listaServico;
    }

    public void alterar(Servico servico) {
        for (Servico servicoAux : listaServico) {
            long idServico = servico.getId();
            long idServicoAux = servicoAux.getId();

            if(idServico == idServicoAux) {
                listaServico.remove(servicoAux);
                listaServico.add(servico);
                break;
            }
        }
    }
}
