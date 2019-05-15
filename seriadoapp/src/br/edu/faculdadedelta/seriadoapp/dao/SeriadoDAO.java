package br.edu.faculdadedelta.seriadoapp.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.seriadoapp.modelo.Seriado;

public class SeriadoDAO {

	private static List<Seriado> listaSeriado = new ArrayList<Seriado>();
	private static Long idGerador = 1L;

	public void incluir(Seriado seriado) {
		seriado.setId(idGerador++);
		listaSeriado.add(seriado);
	}

	public void excluir(Seriado seriado) {
		listaSeriado.remove(seriado);
	}

	public List<Seriado> listar() {
		return listaSeriado;
	}

	public void alterar(Seriado seriado) {
		for (Seriado seriadoAux : listaSeriado) {
			long idSeriado = seriado.getId();
			long idSeriadoAux = seriadoAux.getId();
			if (idSeriado == idSeriadoAux) {
				listaSeriado.remove(seriadoAux);
				listaSeriado.add(seriado);
				break;
			}

		}

	}
}
