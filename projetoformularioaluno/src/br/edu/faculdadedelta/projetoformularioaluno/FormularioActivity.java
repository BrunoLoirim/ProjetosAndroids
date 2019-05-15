package br.edu.faculdadedelta.projetoformularioaluno;

import br.edu.faculdadedelta.projetoformularioaluno.dao.AlunoDAO;
import br.edu.faculdadedelta.projetoformularioaluno.modelo.Aluno;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioActivity extends Activity {

	private EditText etNome;
	private EditText etMatricula;
	private EditText etCpf;
	private Aluno aluno = new Aluno();
	private AlunoDAO dao = new AlunoDAO();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);
		
		etNome = (EditText) findViewById(R.id.etNome);
		etMatricula = (EditText) findViewById(R.id.etMatricula);
		etCpf = (EditText) findViewById(R.id.etCpf);
		
		Intent intent = getIntent();
		
		if (intent != null) {
			Aluno alunoSelecionado 
			= (Aluno) intent.getSerializableExtra("alunoSelecionado");
			if (alunoSelecionado != null) {
				popularFormulario(alunoSelecionado);
			}
		}
	}
	
	private void popularFormulario(Aluno alunoSelecionado) {
		etNome.setText(alunoSelecionado.getNome());
		etMatricula.setText(alunoSelecionado.getMatricula());
		etCpf.setText(alunoSelecionado.getCpf());
		
		aluno.setId(alunoSelecionado.getId());
	}
	
	private void popularModelo() {
		aluno.setNome(etNome.getText().toString());
		aluno.setMatricula(etMatricula.getText().toString());
		aluno.setCpf(etCpf.getText().toString());
	}
	
	public void salvar(View view) {
		popularModelo();
		if (aluno.getId() == null) {
			// incluir
			dao.incluir(aluno);
			Toast.makeText(getBaseContext(), 
					"Inclusão realizada com sucesso!", 
					Toast.LENGTH_LONG).show();
			Log.i("Log", "Lista: " + dao.listar());
		} else {
			// alterar
			dao.alterar(aluno);
			Toast.makeText(getBaseContext(), 
					"Alteração realizada com sucesso!", 
					Toast.LENGTH_LONG).show();
		}
		limparCampos(null);
	}
	
	public void limparCampos(View view) {
		etNome.setText("");
		etMatricula.setText("");
		etCpf.setText("");
		aluno = new Aluno();
	}
	
	public void listar(View view) {
		Intent intent 
			= new Intent(getBaseContext(), ListaActivity.class);
		startActivity(intent);
	}

}
