package br.edu.faculdadedelta.projetoformularioaluno.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetoformularioaluno.modelo.Aluno;

public class AlunoDAO {

	private static Long idGerador = 1L;
	private static List<Aluno> listaAluno = new ArrayList<Aluno>();

	public void incluir(Aluno aluno) {
		aluno.setId(idGerador++);
		listaAluno.add(aluno);
	}

	public void excluir(Aluno aluno) {
		listaAluno.remove(aluno);
	}

	public List<Aluno> listar() {
		return listaAluno;
	}

	public void alterar(Aluno aluno) {

		for (Aluno alunoAux : listaAluno) {
			long idAluno = aluno.getId();
			long idAlunoAux = alunoAux.getId();
			if (idAluno == idAlunoAux) {
				listaAluno.remove(alunoAux);
				listaAluno.add(aluno);
				break;
			}
		}
	}
}
