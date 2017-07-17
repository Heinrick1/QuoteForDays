package com.example.heinrick.quotesfordays;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heinrick.quotesfordays.models.Quote;

import java.util.ArrayList;

/**
 * Created by Heinrick on 2017-06-04.
 */

public class QfdAdapter extends RecyclerView.Adapter<QfdAdapter.QdfAdapterViewHolder> {

    private ArrayList<Quote> aQuotes;


    public QfdAdapter(){


    }

    /*cache of children views for a list item*/

    public class QdfAdapterViewHolder extends RecyclerView.ViewHolder{

        //Holds the view for the quote's content and the author
        public final TextView pQuoteBodyView;
        public final TextView pQuoteAuthorView;

        //holds the view for the quote and the author
        QdfAdapterViewHolder(View view){
            super(view);
            pQuoteBodyView = (TextView) view.findViewById(R.id.tv_quote_body);
            pQuoteAuthorView = (TextView) view.findViewById(R.id.tv_quote_author);

        }

        //What happens when the viewHolder is created
        

    }

    public QdfAdapterViewHolder onCreateViewHolder(ViewGroup vGroup, int vType){

        //Context = needs to know what is going on right now before making new things
        Context context = vGroup.getContext();
        //where the xml layout has been stored
        int layoutId = R.layout.quote_layout;
        //inflates the xml to java code
        LayoutInflater inflater = LayoutInflater.from(context);
        //should not attach to the parent immediately
        boolean attach = false;

        View view = inflater.inflate(layoutId, vGroup, attach);
        return new QdfAdapterViewHolder(view);
    }
    
    public void onBindViewHolder (QdfAdapterViewHolder qdfAdapterViewHolder, int pos){
        //retrieves the quote at specified array position
        Quote curQuote = aQuotes.get(pos);

        String quoteBody = curQuote.getBody() ;
        String quoteAuthor = curQuote.getAuthor();

        qdfAdapterViewHolder.pQuoteBodyView.setText(quoteBody);
        qdfAdapterViewHolder.pQuoteAuthorView.setText(quoteAuthor);
    }

    public int getItemCount() {
        if (aQuotes == null) return 0;
        return aQuotes.size();
    }

    public void setQuoteList (ArrayList<Quote> quotes){
        aQuotes = quotes;
        notifyDataSetChanged();
    }
}
