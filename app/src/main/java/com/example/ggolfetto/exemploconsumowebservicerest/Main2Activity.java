package com.example.ggolfetto.exemploconsumowebservicerest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ggolfetto.exemploconsumowebservicerest.model.Resposta;
import com.example.ggolfetto.exemploconsumowebservicerest.network.APIClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Main2Activity extends AppCompatActivity {

    private TextView txtNomeUsuario;
    private TextView txtLoginUsuario;
    private TextView txtSenhaUsuario;
    private TextView txtResposta;
    private Button btnCadastrar;
    private Callback<Resposta> respostaCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        txtNomeUsuario = (TextView) findViewById(R.id.txtNomeUsuario);
        txtLoginUsuario = (TextView) findViewById(R.id.txtLoginUsuario);
        txtSenhaUsuario = (TextView)findViewById(R.id.txtSenhaUsuario);
        txtResposta = (TextView)findViewById(R.id.txtResposta);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NomeUsuario = txtNomeUsuario.getText().toString();
                String LoginUsuario = txtLoginUsuario.getText().toString();
                String SenhaUsuario = txtSenhaUsuario.getText().toString();

                configurarCallback();
                new APIClient().getRestService().setUsuarioDTO("12345", "CRIARUSUARIODTO", NomeUsuario, LoginUsuario, SenhaUsuario,respostaCallback);

            }
        });

    }

    private void configurarCallback() {
        respostaCallback = new Callback<Resposta>() {
            @Override
            public void success(Resposta resposta, Response response) {
                Toast.makeText(Main2Activity.this, "Deu Certo", Toast.LENGTH_SHORT).show();
                txtResposta.setText(resposta.getRETORNO());
                txtResposta.setVisibility(View.VISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(Main2Activity.this, "Deu Ruim: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
