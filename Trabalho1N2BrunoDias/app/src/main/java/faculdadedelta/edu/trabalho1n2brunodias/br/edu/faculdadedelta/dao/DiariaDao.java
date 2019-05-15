package faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.dao;

import java.util.ArrayList;
import java.util.List;

import faculdadedelta.edu.trabalho1n2brunodias.br.edu.faculdadedelta.modelo.Diaria;

public class DiariaDao {

    private static List<Diaria> listaDiaria = new ArrayList<Diaria>();
    private static Long idGerador = 1L;

    public void incluir(Diaria diaria) {
        diaria.setId(idGerador++);
        listaDiaria.add(diaria);
    }

    public void excluir(Diaria diaria) {
        listaDiaria.remove(diaria);
    }

    public List<Diaria> listar() {
        return listaDiaria;
    }

    public void alterar(Diaria diaria) {
        for (Diaria diariaAux : listaDiaria) {
            long idDiaria = diaria.getId();
            long idDiariaAux = diariaAux.getId();

            if(idDiaria == idDiariaAux) {
                listaDiaria.remove(diariaAux);
                listaDiaria.add(diaria);
                break;
            }
        }
    }
}
