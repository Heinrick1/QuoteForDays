package com.example.heinrick.quotesfordays;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heinrick.quotesfordays.models.Quote;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Heinrick on 2017-07-09.
 */

public class QfdSwipeAdapter extends PagerAdapter {

    private ArrayList<Quote> aQuotes;

    private Context ct;
    private LayoutInflater linft;


    public QfdSwipeAdapter(Context pCt){
        ct = pCt;
    }

    @Override
    public int getCount() {
        return aQuotes.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // retrieving data from the quote at the current position
        Quote curQuote = aQuotes.get(position);

        String quoteBody = curQuote.getBody() ;
        String quoteAuthor = curQuote.getAuthor();


        linft = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View quoteView = linft.inflate(R.layout.activity_display_quote, container, false);

        TextView pBodyView= (TextView) quoteView.findViewById(R.id.tv_quote_body);
        TextView pAuthorView = (TextView) quoteView.findViewById(R.id.tv_quote_author);



        pBodyView.setText(quoteBody);
        pAuthorView.setText(quoteAuthor);

        container.addView(quoteView);

        return quoteView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    public void setQuoteList (ArrayList<Quote> quotes){
        aQuotes = quotes;
        notifyDataSetChanged();
    }
}
