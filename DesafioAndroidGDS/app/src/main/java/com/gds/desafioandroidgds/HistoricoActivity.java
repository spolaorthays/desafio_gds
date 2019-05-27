package com.gds.desafioandroidgds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.gds.desafioandroidgds.adapter.RecyclerHistoricoAdapter;
import com.gds.desafioandroidgds.database.MyDbHandler;
import com.gds.desafioandroidgds.interfaces.OnCardClick;
import com.gds.desafioandroidgds.model.Cartao;

public class HistoricoActivity extends AppCompatActivity implements OnCardClick {

    public static String CARTAO_ID = "idCartao";

    private RecyclerView recyclerView;
    private RecyclerHistoricoAdapter adapter;
    private MyDbHandler dbHandler;

    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        dbHandler = new MyDbHandler(this, null, null,1);

        String codDigitado = getIntent().getExtras().getString(MainActivity.COD_CARTAO);

        getIds();

        setupRecycler(codDigitado);

        voltarAoInicio();
    }

    private void getIds(){
        recyclerView = findViewById(R.id.recycler_historico_id);
        voltar = findViewById(R.id.bt_voltar_his);
    }

    private void setupRecycler(String codDigitado) {
        int idCartao;

        if (codDigitado.equals("")){
            adapter = new RecyclerHistoricoAdapter(dbHandler.getAllUltimasConsultas(), dbHandler, this);
        }else if (codDigitado != null){
            idCartao = Integer.parseInt(codDigitado);
            if (codDigitado.equals("346")){
                adapter = new RecyclerHistoricoAdapter(dbHandler.getUltimasConsultasPorApi(idCartao), dbHandler, this);
            }else if(codDigitado.equals("150")){
                adapter = new RecyclerHistoricoAdapter(dbHandler.getUltimasConsultasPorApi(idCartao), dbHandler, this);
            }else if(codDigitado.equals("410")){
                adapter = new RecyclerHistoricoAdapter(dbHandler.getUltimasConsultasPorApi(idCartao), dbHandler, this);
            }else if(codDigitado.equals("500")){
                adapter = new RecyclerHistoricoAdapter(dbHandler.getUltimasConsultasPorApi(idCartao), dbHandler, this);
            }else if(codDigitado.equals("671")){
                adapter = new RecyclerHistoricoAdapter(dbHandler.getUltimasConsultasPorApi(idCartao) , dbHandler, this);
            }
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void voltarAoInicio(){
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onCardClicado(Cartao cartao) {
        Intent intent = new Intent(this, DetalhesHistoricoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(CARTAO_ID, cartao.getIdCartao());
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
