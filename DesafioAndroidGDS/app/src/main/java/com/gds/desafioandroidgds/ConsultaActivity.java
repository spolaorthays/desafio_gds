package com.gds.desafioandroidgds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.gds.desafioandroidgds.adapter.RecyclerMovimentosAdapter;
import com.gds.desafioandroidgds.dao.Dao;
import com.gds.desafioandroidgds.model.Cartao;
import com.gds.desafioandroidgds.model.Movimento;
import com.gds.desafioandroidgds.service.ServiceListener;

import java.util.List;

public class ConsultaActivity extends AppCompatActivity implements ServiceListener {

    private TextView nomeEmpresa;
    private TextView saldo;
    private TextView dtUpdate;
    private TextView nomeCliente;

    private Cartao cartao;
    private List<Cartao> cartaoList;

    private RecyclerView recyclerView;
    private RecyclerMovimentosAdapter adapter;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        cartao = new Cartao();

        dao = new Dao();
        cartaoList = dao.getCartaoList(this,this);

        getIds();
    }

    private void getIds(){
        nomeEmpresa = findViewById(R.id.consulta_nomeempresa_id);
        saldo = findViewById(R.id.consulta_saldo_id);
        dtUpdate = findViewById(R.id.consulta_ultimo_update);
        nomeCliente = findViewById(R.id.consulta_nomecliente_id);
        recyclerView = findViewById(R.id.consulta_recycler_id);
    }

    @Override
    public void onSuccess(Object object) {
        Cartao cartao = (Cartao) object;
        cartaoList.add(cartao);
        String real = "R$ ";

        for (int i=0; i<cartaoList.size(); i++){
            nomeEmpresa.setText(cartaoList.get(i).getNomeEmpresa());
            saldo.setText(String.format("%s%s", real, cartaoList.get(i).getSaldo()));
            dtUpdate.setText(cartaoList.get(i).getDtUltimoUpdate());
            nomeCliente.setText(cartaoList.get(i).getNome());
        }

        List<Movimento> movimentos = cartao.getMovimentos();
        adapter = new RecyclerMovimentosAdapter(movimentos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(this,"Erro"+throwable.getMessage(),Toast.LENGTH_LONG).show();
    }
}
