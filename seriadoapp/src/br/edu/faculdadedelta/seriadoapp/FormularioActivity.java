package br.edu.faculdadedelta.seriadoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.edu.faculdadedelta.seriadoapp.dao.SeriadoDAO;
import br.edu.faculdadedelta.seriadoapp.modelo.Seriado;

public class FormularioActivity extends Activity {

	private EditText etTitulo;
	private EditText etGenero;
	private EditText etNota;
	private EditText etNumeroTemporada;
	private EditText etAnoLancamento;

	private Seriado seriado = new Seriado();
	private SeriadoDAO dao = new SeriadoDAO();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);

		etTitulo = (EditText) findViewById(R.id.etTitulo);
		etGenero = (EditText) findViewById(R.id.etGenero);
		etNota = (EditText) findViewById(R.id.etNota);
		etNumeroTemporada = (EditText) findViewById(R.id.etNumeroTemporada);
		etAnoLancamento = (EditText) findViewById(R.id.etAnoLancamento);

		Intent intent = getIntent();
		
		if (intent != null) {
			Seriado seriadoSelecionado 
			= (Seriado) intent.getSerializableExtra("seriadoSelecionado");
			if (seriadoSelecionado != null) {
				popularFormulario(seriadoSelecionado);
			}
		}
	}

	private void popularFormulario(Seriado seriadoSelecionado) {
		etTitulo.setText(seriadoSelecionado.getTitulo());
		etGenero.setText(seriadoSelecionado.getGenero());
		etNota.setText(String.valueOf(seriadoSelecionado.getNota()));
		etNumeroTemporada
			.setText(String.valueOf(seriadoSelecionado
					.getNumeroTemporada()));
		etAnoLancamento
			.setText(String
						.valueOf(seriadoSelecionado.getAnoLancamento()));
		seriado.setId(seriadoSelecionado.getId());
	}
	
	private void popularModelo() {
		seriado.setTitulo(etTitulo.getText().toString());
		seriado.setGenero(etGenero.getText().toString());
		seriado.setNota(Integer.parseInt(etNota.getText().toString()));
		seriado.setNumeroTemporada(Integer.parseInt(etNumeroTemporada.getText()
				.toString()));
		seriado.setAnoLancamento(Integer.parseInt(etAnoLancamento.getText()
				.toString()));
	}

	public void salvar(View view) {
		
		String mensagemValidacao = validarCampos();
		
		if (mensagemValidacao.equals("")) {
			popularModelo();
			if (seriado.getId() == null) {
				dao.incluir(seriado);
				Toast.makeText(getBaseContext(), "Inclusão realizada com sucesso!",
						Toast.LENGTH_LONG).show();
			} else {
				dao.alterar(seriado);
				Toast.makeText(getBaseContext(),
						"Alteração realizada com sucesso!", Toast.LENGTH_LONG)
						.show();
			}
			limparCampos(null);
		} else {
			Toast
				.makeText(getBaseContext(), 
						mensagemValidacao, Toast.LENGTH_LONG).show();
		}
		
		
	}

	private String validarCampos() {
		String mensagem = "";
		if (etTitulo.getText().toString().equals("")) {
			mensagem = "O campo titulo é obrigatório!";
		}
		if (etGenero.getText().toString().equals("")) {
			mensagem += "\nO campo genero é oborigatório!"; 
		}
		if (etNota.getText().toString().equals("")) {
			mensagem += "\nO campo nota é obrigatório!";
		}
		if (etNumeroTemporada.getText().toString().equals("")) {
			mensagem += "\nO campo numero de temporada é obrigatório!";
		}
		if (etAnoLancamento.getText().toString().equals("")) {
			mensagem += "\nO campo ano é obrigatório!";
		}
		return mensagem;
	}
	
	public void limparCampos(View view) {
		etTitulo.setText("");
		etGenero.setText("");
		etNota.setText("");
		etNumeroTemporada.setText("");
		etAnoLancamento.setText("");
		seriado = new Seriado();
	}

	public void listar(View view) {
		Intent intent = new Intent(getBaseContext(), ListaSeriadoActivity.class);
		startActivity(intent);
	}

}
