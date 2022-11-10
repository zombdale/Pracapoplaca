package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert.EmployerOffert;

public class RVAdapter_empee_offerts extends RecyclerView.Adapter<RVAdapter_empee_offerts.ViewHolder> {
    private String[][] data;
    private String[][] tags;
    private CardView cv;
    private int t;
    private AppCompatActivity ACV = new AppCompatActivity();


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public RVAdapter_empee_offerts(String[][] data) {
        this.data = data;
    }

    public void setT(int t) {
        this.t = t;

    }

    public int getT() {
        return this.t;

    }

    public AppCompatActivity getACV() {
        return this.ACV;
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(data[position][0]);
    }

    @Override
    public int getItemCount() {
        return this.data.length;
    }

    @Override
    public RVAdapter_empee_offerts.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.employee_offert_cardview, viewGroup, false);
        //  CardView cv= (CardView) findViewById(R.id.CV);
        return new RVAdapter_empee_offerts.ViewHolder(cv);

    }

    @Override
    public void onBindViewHolder(RVAdapter_empee_offerts.ViewHolder holder, int i) {
        CardView cardView = holder.cardView;
           TextView obiektNazwa = (TextView) cardView.findViewById(R.id.empee_offert_nazwa);
        TextView obiektCategory = (TextView)  cardView.findViewById(R.id.empee_offert_cat);
        TextView obiektCity = (TextView)  cardView.findViewById(R.id.empee_offert_city);
        TextView obiektTags = (TextView)  cardView.findViewById(R.id.empee_offert_tags);
        LinearLayout more = (LinearLayout) cardView.findViewById(R.id.empee_offert_more);
        Button buttoninfo = (Button) cardView.findViewById(R.id.empee_offert_b);
      Log.v("#BXI", "i :" + i + "");
        obiektNazwa.setText(data[i][1]);

        //obiektCategory
        obiektCity.setText(data[i][4]);
        obiektTags.setText(data[i][10]);
        setT(i);
        //!!

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout more = (LinearLayout) v.findViewById(R.id.empee_offert_more);

                if (more.getVisibility() == View.VISIBLE) {
                    more.setVisibility(View.GONE);
                } else {
                    more.setVisibility(View.VISIBLE);
                }
                    }
                });

        buttoninfo.setTag(getItemId(getT()));

            }




    }


