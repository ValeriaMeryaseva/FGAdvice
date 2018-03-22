package com.example.lera.fgadvice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.lera.fgadvice.R;
import com.example.lera.fgadvice.db.DatabaseDAO;
import com.example.lera.fgadvice.db.DatabaseHelper;
import com.example.lera.fgadvice.model.Advice;

import java.util.List;

public class FavoritesListAdapter extends BaseAdapter {

    List<Advice> list;
    private Context context;

    private Button deleteAdviceButton;

    public FavoritesListAdapter(Context context, List<Advice> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void addElement(Advice fAdvice){
        list.add(fAdvice);
    }

    @Override
    public Advice getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_favorite_advice, viewGroup, false);
        }
        Advice advice = getItem(i);
        ((TextView) view.findViewById(R.id.advice_text)).setText(advice.getText());

        deleteAdviceButton = view.findViewById(R.id.delete_button);

        final int position = i;
        deleteAdviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper.deleteFromDB(DatabaseDAO.getDBInstance(context.getApplicationContext()),
                        Integer.parseInt(list.get(position).getId()));
                list.remove(position);
            }
        });

        return view;
    }
}
