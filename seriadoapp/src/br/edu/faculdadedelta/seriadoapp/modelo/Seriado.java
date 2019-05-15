package br.edu.faculdadedelta.seriadoapp.modelo;

import java.io.Serializable;

public class Seriado implements Serializable {

	private Long id;
	private String titulo;
	private String genero;
	private int numeroTemporada;
	private int nota;
	private int anoLancamento;

	public Seriado() {
	}

	public Seriado(Long id, String titulo, String genero, int numeroTemporada,
			int nota, int anoLancamento) {
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.numeroTemporada = numeroTemporada;
		this.nota = nota;
		this.anoLancamento = anoLancamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getNumeroTemporada() {
		return numeroTemporada;
	}

	public void setNumeroTemporada(int numeroTemporada) {
		this.numeroTemporada = numeroTemporada;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seriado other = (Seriado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
