package com.gds.desafioandroidgds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gds.desafioandroidgds.adapter.RecyclerDetalhesAdapter;
import com.gds.desafioandroidgds.adapter.RecyclerMovimentosAdapter;
import com.gds.desafioandroidgds.database.MyDbHandler;
import com.gds.desafioandroidgds.model.Cartao;

public class DetalhesHistoricoActivity extends AppCompatActivity {

    private TextView nomeEmpresa;
    private TextView saldo;
    private TextView dtUpdate;
    private TextView nomeCliente;
    private Button voltar;

    private Cartao cartao;

    private RecyclerView recyclerView;
    private RecyclerDetalhesAdapter adapter;

    private MyDbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_historico);

        dbHandler = new MyDbHandler(this, null, null, 1);

        int getIdCartao = getIntent().getExtras().getInt(HistoricoActivity.CARTAO_ID);
        cartao = dbHandler.getCartao(getIdCartao);

        getIds();
        buscandoDadosDatabase(cartao);
        setupRecycler();
        fecharDetalhes();
    }

    private void getIds(){
        nomeEmpresa = findViewById(R.id.detalhes_nomeempresa_id);
        saldo = findViewById(R.id.detalhes_saldo_id);
        dtUpdate = findViewById(R.id.detalhes_ultimo_update);
        nomeCliente = findViewById(R.id.detalhes_nomecliente_id);
        recyclerView = findViewById(R.id.detalhes_recycler_id);
        voltar = findViewById(R.id.bt_voltar_det);
    }

    private void setupRecycler(){
        adapter = new RecyclerDetalhesAdapter(dbHandler.getAllMovimentos(cartao.getIdCartao()), dbHandler);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void buscandoDadosDatabase(Cartao cartao){
        String real = "R$ ";

        nomeEmpresa.setText(cartao.getNomeEmpresa());
        saldo.setText(String.format("%s%s", real, cartao.getSaldo()));
        dtUpdate.setText(cartao.getDtUltimoUpdate());
        nomeCliente.setText(cartao.getNome());
    }

    private void fecharDetalhes(){
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
