package com.gds.desafioandroidgds;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String COD_CARTAO = "codCartao";

    private TextInputEditText codCartao;
    private TextInputEditText codCartaoHist;
    private Button btConsultar;
    private Button btHistorico;

    private String codDigitadoUm = "346";
    private String codDigitadoDois = "150";
    private String codDigitadoTres = "410";
    private String codDigitadoQuatro = "500";
    private String codDigitadoCinco = "671";
    private String codDigitadoVazio = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIds();

        visualizarConsulta();

        visualizarHistorico();
    }

    private void getIds(){
        codCartao = findViewById(R.id.codigo_cartao_id);
        codCartaoHist = findViewById(R.id.codigo_main_hist_id);
        btConsultar = findViewById(R.id.bt_consultar);
        btHistorico = findViewById(R.id.bt_historico);
    }

    private void visualizarConsulta(){
        btConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorDigitadoConsulta();
            }
        });
    }

    private void goToConsulta(String codCartao){
        Intent intent = new Intent(this, ConsultaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(COD_CARTAO, codCartao);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void visualizarHistorico(){
        btHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorDigitadoHistorico();
            }
        });
    }

    private void goToHistorico(String codCartao){
        Intent intent = new Intent(this, HistoricoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(COD_CARTAO, codCartao);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void valorDigitadoConsulta(){
        String codigo = codCartao.getEditableText().toString();

        if (codigo.equals(codDigitadoUm)){
            goToConsulta(codigo);
        }else if (codigo.equals(codDigitadoDois)){
            goToConsulta(codigo);
        }else if (codigo.equals(codDigitadoTres)){
            goToConsulta(codigo);
        }else if (codigo.equals(codDigitadoQuatro)){
            goToConsulta(codigo);
        }else if (codigo.equals(codDigitadoCinco)){
            goToConsulta(codigo);
        }else {
            Snackbar.make(btConsultar, "Código Inválido, digite os 3 últimos dígitos do seu cartão.",
                    Snackbar.LENGTH_INDEFINITE).setAction("Entendi", new View.OnClickListener() {
                @Override
                public void onClick(View v) {}
            }).show();
        }
    }

    public void valorDigitadoHistorico(){
        String codigoHist = codCartaoHist.getEditableText().toString();

        if (codigoHist.equals(codDigitadoUm)){
            goToHistorico(codigoHist);
        }else if (codigoHist.equals(codDigitadoDois)){
            goToHistorico(codigoHist);
        }else if (codigoHist.equals(codDigitadoTres)){
            goToHistorico(codigoHist);
        }else if (codigoHist.equals(codDigitadoQuatro)){
            goToHistorico(codigoHist);
        }else if (codigoHist.equals(codDigitadoCinco)){
            goToHistorico(codigoHist);
        }else if (codigoHist.equals(codDigitadoVazio)){
            goToHistorico(codigoHist);
        }else {
            Snackbar.make(btHistorico, "Código Inválido. Para visualizar todas consultas, deixe o campo vazio.",
                    Snackbar.LENGTH_INDEFINITE).setAction("Entendi", new View.OnClickListener() {
                @Override
                public void onClick(View v) {}
            }).show();
        }
    }
}
