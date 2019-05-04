package br.com.appviral.persistindocomsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.appviral.persistindocomsqlite.Entidade.Imovel;
import br.com.appviral.persistindocomsqlite.Persistencia.ImovelDAO;

/**
 * Created by Martin on 14/05/2016.
 */
public class AdaptadorListView extends BaseAdapter {

    LayoutInflater layoutInflater;
    ImovelDAO imovelDAO;

    public AdaptadorListView(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imovelDAO = new ImovelDAO(context);
    }


    @Override
    public int getCount() {
        return ImovelDAO.listaImovels.size();
    }

    @Override
    public Object getItem(int position) {
        return ImovelDAO.listaImovels.get(position);
    }

    @Override
    public long getItemId(int position) {
        Imovel umaImovel = ImovelDAO.listaImovels.get(position);
        return umaImovel.id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_item_listview, null);
        }

        TextView tvEndereco = (TextView) convertView.findViewById(R.id.tvEndereco);
        TextView tvNumero = (TextView) convertView.findViewById(R.id.tvNumero);
        TextView tvBairro = (TextView) convertView.findViewById(R.id.tvBairro);
        TextView tvCidade = (TextView) convertView.findViewById(R.id.tvCidade);
        TextView tvEstado = (TextView) convertView.findViewById(R.id.tvEstado);
        TextView tvImobiliaria = (TextView) convertView.findViewById(R.id.tvImobiliaria);
        TextView tvTelefone = (TextView) convertView.findViewById(R.id.tvTelefone);

        Imovel umImovel = ImovelDAO.listaImovels.get(position);

        tvEndereco.setText(umImovel.endereco);
        tvNumero.setText(umImovel.numero);
        tvBairro.setText(umImovel.bairro);
        tvCidade.setText(umImovel.cidade);
        tvEstado.setText(umImovel.estado);
        tvImobiliaria.setText(umImovel.imobiliaria);
        tvTelefone.setText(umImovel.telefone);

        return convertView;
    }

}

