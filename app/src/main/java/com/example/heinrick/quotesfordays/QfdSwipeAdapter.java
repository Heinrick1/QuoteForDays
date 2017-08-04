package com.example.heinrick.quotesfordays;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

    private Quote currentQuote;

   // private View quoteView;
   // private TextView pBodyView;
   // private TextView pAuthorView;


    public QfdSwipeAdapter(Context pCt){
        ct = pCt;
        currentQuote = new Quote("Boom", "Dickens");
    }

    @Override
    public int getCount() {
        return aQuotes.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // retrieving data from the quote at the current position
        currentQuote= aQuotes.get(position);

        String quoteBody = currentQuote.getBody() ;
        String quoteAuthor = currentQuote.getAuthor();

        //inflating the layout and populating the textvies associated to the current quote

        linft = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View quoteView = linft.inflate(R.layout.quote_layout, container, false);

        TextView pBodyView = (TextView) quoteView.findViewById(R.id.tv_quote_body);
        TextView pAuthorView = (TextView) quoteView.findViewById(R.id.tv_quote_author);

        pBodyView.setText(quoteBody);
        pAuthorView.setText(quoteAuthor);


        // adding the quote view to the view pager
        ViewPager viewp = (ViewPager) container;
        viewp.addView(quoteView);

        return quoteView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        //destroys the current quoteView
        ((ViewPager) container).removeView((View) object);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    public void setQuoteList (ArrayList<Quote> quotes){
        aQuotes = quotes;
        notifyDataSetChanged();
    }

    //returns a clone of the current Quote to maintain limited access to quote values
    public Quote curQuote (){
        return currentQuote;
        //return currentQuote.clone();
    };


}
