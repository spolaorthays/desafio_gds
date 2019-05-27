package com.gds.desafioandroidgds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gds.desafioandroidgds.adapter.RecyclerMovimentosAdapter;
import com.gds.desafioandroidgds.database.dao.Dao;
import com.gds.desafioandroidgds.database.MyDbHandler;
import com.gds.desafioandroidgds.model.Cartao;
import com.gds.desafioandroidgds.model.Movimento;
import com.gds.desafioandroidgds.service.ServiceListener;

import java.util.List;

public class ConsultaActivity extends AppCompatActivity implements ServiceListener {

    private TextView nomeEmpresa;
    private TextView saldo;
    private TextView dtUpdate;
    private TextView nomeCliente;
    private Button voltar;

    private Cartao cartao;

    private RecyclerView recyclerView;
    private RecyclerMovimentosAdapter adapter;

    private Dao dao;
    private MyDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        String codDigitado = getIntent().getExtras().getString(MainActivity.COD_CARTAO);

        cartao = new Cartao();

        dao = new Dao();

        //Com isso eu consigo abrir as diferentes api dependendo apenas do número do cartão previamente digitado
        if (codDigitado.equals("346")){
            cartao = dao.getCartao(this, this);
        }else if(codDigitado.equals("150")){
            cartao = dao.getApiDois(this, this);
        }else if(codDigitado.equals("410")){
            cartao = dao.getApiTres(this, this);
        }else if(codDigitado.equals("500")){
            cartao = dao.getApiQuatro(this, this);
        }else if(codDigitado.equals("671")){
            cartao = dao.getApiCinco(this, this);
        }

        dbHandler = new MyDbHandler(this, null, null,1);

        getIds();

        voltarParaMain();
    }

    private void getIds(){
        nomeEmpresa = findViewById(R.id.consulta_nomeempresa_id);
        saldo = findViewById(R.id.consulta_saldo_id);
        dtUpdate = findViewById(R.id.consulta_ultimo_update);
        nomeCliente = findViewById(R.id.consulta_nomecliente_id);
        recyclerView = findViewById(R.id.consulta_recycler_id);
        voltar = findViewById(R.id.bt_voltar);
    }


    private void voltarParaMain(){
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private long salvarDadosCartao(Cartao cartao){
        return dbHandler.adicionarCartao(cartao);
    }

    private void salvarDadosMovimento(List<Movimento> movimentos, int idCartao){
        for (Movimento mov: movimentos) {
            mov.setFkIdCartao(idCartao);
            dbHandler.adicionarMovimentos(mov);
        }
    }

    @Override
    public void onSuccess(Object object) {
        Cartao cartao = (Cartao) object;
        String real = "R$ ";

        nomeEmpresa.setText(cartao.getNomeEmpresa());
        saldo.setText(String.format("%s%s", real, cartao.getSaldo()));
        dtUpdate.setText(cartao.getDtUltimoUpdate());
        nomeCliente.setText(cartao.getNome());

        List<Movimento> movimentos = cartao.getMovimentos();
        adapter = new RecyclerMovimentosAdapter(movimentos, dbHandler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        int idConsulta = (int) salvarDadosCartao(cartao);
        salvarDadosMovimento(movimentos, idConsulta);
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this,"Erro"+throwable.getMessage(),Toast.LENGTH_LONG).show();
    }
}
