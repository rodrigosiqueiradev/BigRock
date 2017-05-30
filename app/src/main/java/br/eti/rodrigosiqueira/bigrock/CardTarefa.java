package br.eti.rodrigosiqueira.bigrock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardTarefa extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_tarefa, container, false);
        TextView textView = (TextView) view.findViewById(R.id.card_tarefa_text);
        textView.setText(String.valueOf(getArguments().getInt("position")));
        return view;
    }

    public CardTarefa() {

    }
}
