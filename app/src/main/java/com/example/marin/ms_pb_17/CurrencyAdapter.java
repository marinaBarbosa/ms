package com.example.marin.ms_pb_17;

/**
 * Created by marina on 24/11/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import com.example.marin.ms_pb_17.Currency.Currency;

public class CurrencyAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Currency> currencyList;

    public CurrencyAdapter(Context context, List<Currency> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
        //this.currencyItemClickListener = currencyItemClickListener;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int i) {
        return currencyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View currencyItemView = layoutInflater.inflate(R.layout.activity_currency_list, null);
        TextView tvName = (TextView) currencyItemView.findViewById(R.id.tvName);
        TextView tvRate = (TextView) currencyItemView.findViewById(R.id.tvRate);

        final Currency c = currencyList.get(i);
        tvName.setText(c.getName());
        tvRate.setText(Double.toString(c.getRate()));


        return currencyItemView;
    }
}

