package br.com.appviral.persistindocomsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.appviral.persistindocomsqlite.Entidade.Imovel;
import br.com.appviral.persistindocomsqlite.Persistencia.ImovelDAO;

public class DetalhesActivity extends AppCompatActivity {
    String operacao;
    int posicao;
    Long id;
    EditText etEndereco, etNumero, etBairro, etCidade, etEstado, etImobiliaria, etTelefone;

    ImovelDAO imovelDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        operacao = getIntent().getStringExtra("OPERACAO");
        posicao = getIntent().getIntExtra("POSICAO", 0);
        id = getIntent().getLongExtra("ID",0);

        etEndereco = (EditText) findViewById(R.id.etEndereco);
        etNumero = (EditText) findViewById(R.id.etNumero);
        etBairro = (EditText) findViewById(R.id.etBairro);
        etCidade = (EditText) findViewById(R.id.etCidade);
        etEstado = (EditText) findViewById(R.id.etEstado);
        etImobiliaria = (EditText) findViewById(R.id.etImobiliaria);
        etTelefone = (EditText) findViewById(R.id.etTelefone);

        if(operacao.equals("alterar")){
            Imovel imovel = ImovelDAO.listaImovels.get(posicao);

            etEndereco.setText(imovel.endereco);
            etNumero.setText(imovel.numero);
            etBairro.setText(imovel.bairro);
            etCidade.setText(imovel.cidade);
            etEstado.setText(imovel.estado);
            etImobiliaria.setText(imovel.imobiliaria);
            etTelefone.setText(imovel.telefone);
        }
        imovelDAO = new ImovelDAO(this);
    }


    public void salvar(View view) {
        if (operacao.equals("inserir")) {

            Imovel umImovel = new Imovel();

            umImovel.endereco = etEndereco.getText().toString();
            umImovel.numero = etNumero.getText().toString();
            umImovel.bairro = etBairro.getText().toString();
            umImovel.cidade = etCidade.getText().toString();
            umImovel.estado = etEstado.getText().toString();
            umImovel.imobiliaria = etImobiliaria.getText().toString();
            umImovel.telefone = etTelefone.getText().toString();

            if (imovelDAO.inserir(umImovel)) {
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }

        if(operacao.equals("alterar")){
            Imovel umImovel = new Imovel();
            umImovel.id = id;
            umImovel.endereco = etEndereco.getText().toString();
            umImovel.numero = etNumero.getText().toString();
            umImovel.bairro = etBairro.getText().toString();
            umImovel.cidade = etCidade.getText().toString();
            umImovel.estado = etEstado.getText().toString();
            umImovel.imobiliaria = etImobiliaria.getText().toString();
            umImovel.telefone = etTelefone.getText().toString();

            if (imovelDAO.alterar(umImovel)) {
                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                etEndereco.setText("");
                etNumero.setText("");
                etBairro.setText("");
                etCidade.setText("");
                etEstado.setText("");
                etImobiliaria.setText("");
                etTelefone.setText("");
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }

    public void excluir(View view) {
        if(operacao.equals("alterar")){
            if ( imovelDAO.excluir(id)) {
                Toast.makeText(this, "Excluido com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Operação não realizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void fechar(View view) {
        finish();
    }
}
