package faculdadedelta.edu.exercicio2brunodias;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
 public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etValor;
    private EditText etCor;
    private EditText etModelo;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.etNome);
        etValor = findViewById(R.id.etValor);
        etCor = findViewById(R.id.etCor);
        etModelo = findViewById(R.id.etModelo);
    }

    public void enviar(View view) {
        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        intent.putExtra("nomeParam", etNome.getText().toString());
        intent.putExtra("valorParam", etValor.getText().toString());
        intent.putExtra("corParam", etCor.getText().toString());
        intent.putExtra("modeloParam", etModelo.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);

    }

    public void limpar(View view){
        etNome.setText("");
        etValor.setText("");
        etCor.setText("");
        etModelo.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      if(requestCode == REQUEST_CODE) {
            if(requestCode == ValidacaoActivity.RESULT_CODE_SUCESS) {
                String mensagemValidacao =  data.getStringExtra("mensagemValidacao");
                Toast.makeText(getBaseContext(), mensagemValidacao, Toast.LENGTH_LONG).show();
                limpar(null);
            }
        }
    }
}
