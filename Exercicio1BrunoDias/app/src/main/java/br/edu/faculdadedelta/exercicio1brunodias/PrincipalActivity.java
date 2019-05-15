package br.edu.faculdadedelta.exercicio1brunodias;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.Calendar;

public class PrincipalActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    //Variaveis
    private EditText etNome;
    private EditText etDataNascimento;
    private EditText etPeso;
    private EditText etAltura;
    private TextView tvData;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        etNome = (EditText) findViewById(R.id.etNome);
        etDataNascimento = (EditText) findViewById(R.id.etDataNascimento);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etAltura = (EditText) findViewById(R.id.etAltura);

        // Criando a mascara no campo Data de Nascimento
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(etDataNascimento, smf);
        etDataNascimento.addTextChangedListener(mtw); //Referenciando o campo dizendo que o mesmo possui mascara

    }

    private void dataNascimentoPicker(){
        DatePickerDialog dataNascimentoPicker = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        dataNascimentoPicker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String data = dayOfMonth + "/" + month + "/" + year;
        etDataNascimento.setText(data);

    }

    public void enviar(View view) {

            Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

            intent.putExtra("nomeParam", etNome.getText().toString());
            intent.putExtra("dataNascimentoParam", etDataNascimento.getText().toString());
            intent.putExtra("pesoParam", etPeso.getText().toString());
            intent.putExtra("alturaParam", etAltura.getText().toString());

            startActivityForResult(intent, REQUEST_CODE);
    }

    public void limpar(View view) {
        etNome.setText("");
        etDataNascimento.setText("");
        etPeso.setText("");
        etAltura.setText("");
    }

    public void destroi(View view) {
        finish();
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
