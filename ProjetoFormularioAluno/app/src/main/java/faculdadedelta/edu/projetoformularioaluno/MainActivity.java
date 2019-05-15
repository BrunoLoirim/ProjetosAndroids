package faculdadedelta.edu.projetoformularioaluno;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import faculdadedelta.edu.projetoformularioaluno.dao.AlunoDAO;
import faculdadedelta.edu.projetoformularioaluno.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etMatricula;
    private EditText etCpf;
    private Aluno aluno = new Aluno();
    private AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etMatricula = findViewById(R.id.etMatricula);
        etCpf = findViewById(R.id.etCpf);
    }

    private void popularModelo() {
        aluno.setNome(etNome.getText().toString().trim());
        aluno.setMatricula(etMatricula.getText().toString().trim());
        aluno.setCpf(etCpf.getText().toString().trim());
    }

    public void salvar(View v) {
        //Popular modelo
        popularModelo();

        if(aluno.getId() == null) {
            dao.incluir(aluno);
            Toast.makeText(getBaseContext(), "Inclusão Realizada com Sucesso!", Toast.LENGTH_SHORT).show();
            Log.i("Log", "Lista: " + dao.listar());
        } else {
            dao.alterar(aluno);
            Toast.makeText(getBaseContext(), "Alteração Realizada com Sucesso!", Toast.LENGTH_SHORT).show();
        }
        limparCampos(null);
    }

    public void limparCampos(View v) {
        etNome.setText("");
        etMatricula.setText("");
        etCpf.setText("");
        aluno = new Aluno();
        Toast.makeText(getBaseContext(), "Campos Limpos, por favor incluir registro!", Toast.LENGTH_SHORT).show();
    }

    public void listar(View v) {
        Intent intent = new Intent(getBaseContext(), ListaActivity.class);

        startActivity(intent);
    }
}
