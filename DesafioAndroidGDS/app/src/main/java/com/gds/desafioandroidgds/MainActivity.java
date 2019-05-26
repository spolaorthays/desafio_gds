package com.gds.desafioandroidgds;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gds.desafioandroidgds.model.Cartao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText codCartao;
    private Button btConsultar;
    private Button btHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();

        verificarEConsultar();

        visualizarHistorico();

    }

    private void getIds(){
        codCartao = findViewById(R.id.codigo_cartao_id);
        btConsultar = findViewById(R.id.bt_consultar);
        btHistorico = findViewById(R.id.bt_historico);
    }

    private void verificarEConsultar(){
        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificandoValorDigitado();
            }
        });
    }

    private void goToConsulta(){
        Intent intent = new Intent(this, ConsultaActivity.class);
        startActivity(intent);
    }

    private void visualizarHistorico(){
        btHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHistorico();
            }
        });
    }

    private void goToHistorico(){
        Intent intent = new Intent(this, HistoricoActivity.class);
        startActivity(intent);
    }

    public void verificandoValorDigitado(){
        String codigo = codCartao.getEditableText().toString();
//        String codDigitado = "000346";
//        String codDigitado2 = "00346";
//        String codDigitado3 = "0346";
        String codDigitado = "346";
        if (codigo.equals(codDigitado)){
            goToConsulta();
//        }else if (codigo.equals(codDigitado2)){
//            goToConsulta();
//        }else if (codigo.equals(codDigitado3)){
//            goToConsulta();
//        }else if (codigo.equals(codDigitado4)){
//            goToConsulta();
        }else {
            Toast.makeText(this, "Código Inválido", Toast.LENGTH_LONG).show();
        }
    }
}
