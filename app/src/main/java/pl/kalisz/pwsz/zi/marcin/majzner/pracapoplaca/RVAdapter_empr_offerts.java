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

public class RVAdapter_empr_offerts extends RecyclerView.Adapter<RVAdapter_empr_offerts.ViewHolder> {
      private String[][] data;
    private CardView cv ;
private Pracodawca_main pm;
    private int t;
private AppCompatActivity ACV = new AppCompatActivity();

//git
    public static class ViewHolder extends RecyclerView.ViewHolder  {

            private CardView cardView;
            private int id;
            public ViewHolder(CardView v) {
                super(v);
                cardView = v;
            }

        public CardView getCardView() {
            return cardView;
        }

        public void setID(int i){
                this.id = i;
        }
        public int getId(){
                return this.id;
        }
    }

        public RVAdapter_empr_offerts(String[][] data, Pracodawca_main pm){
            this.data= data;
this.pm=pm;
        }

        public void setT(int t){
        this.t = t ;

        }

        public int getT(){
        return this.t;

        }
        public String getMSG(){
        return this.pm.getResources().getString(R.string.employer_offert_none);
        }
        public String[][] getData(){
        return this.data;
        }

        public AppCompatActivity getACV(){ return this.ACV;}
@Override
public long getItemId(int position) {
    return Integer.parseInt(data[position][0]);
}

        @Override
        public int getItemCount(){ return getData().length;}

        @Override
        public RVAdapter_empr_offerts.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate
                    (R.layout.employer_offert_cardview, viewGroup, false);
          //  CardView cv= (CardView) findViewById(R.id.CV);
            return new ViewHolder(cv);

        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int i){
            CardView cardView = holder.cardView;
            TextView nazwa = (TextView) cardView.findViewById(R.id.empr_offert_nazwa);
            TextView kateg = (TextView) cardView.findViewById(R.id.empr_offert_cat);
            TextView city = (TextView) cardView.findViewById(R.id.empr_offert_city);
            TextView typ= (TextView) cardView.findViewById(R.id.empr_offert_typ);
            TextView date= (TextView) cardView.findViewById(R.id.empr_offert_date);
            TextView date_from= (TextView) cardView.findViewById(R.id.empr_offert_date_from);
            TextView date_to= (TextView) cardView.findViewById(R.id.empr_offert_date_to);
            TextView hours= (TextView) cardView.findViewById(R.id.empr_offert_hours);
            TextView salary= (TextView) cardView.findViewById(R.id.empr_offert_salary);
            TextView phone = (TextView) cardView.findViewById(R.id.empr_offert_phone);

            Button buttoninfo = (Button)cardView.findViewById(R.id.empr_offert_b);
            Log.v("#BXI", "i :"+i+"");

            switch (Integer.parseInt(data[i][7])){ //typ
                case 1: {
                    typ.setText("Zlecenie/Praca");
                    salary.setText(data[i][9]+"PLN/H");
                    break;}

                case 0 : {
                    typ.setText("O dzieło.");
                    salary.setText(data[i][9]+"PLN");
                    break;}

            }
                nazwa.setText('"'+data[i][1]+'"');
                kateg.setText(cardView.getResources().getStringArray(R.array.category_list)[Integer.parseInt(data[i][15])]);
                city.setText(data[i][3]);

                //typ.setText(data[i][7]);
                //date.setText(data[i][9]);
                //date_from.setText(data[i][10]);
                //date_to.setText(data[i][11]);
                phone.setText(data[i][2]);
                if (data[i][12].length() == 0) {
                    hours.setVisibility(View.GONE);
                } else {
                    hours.setText(data[i][10]);
                    hours.setVisibility(View.VISIBLE);
                }

                    setT(i);
               // setT(Integer.parseInt(data[i][0]));
            Log.d("#EMPROFFERT", "i :"+i+" data: "+data[i][0]);
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LinearLayout more = (LinearLayout) v.findViewById(R.id.empr_offert_more);

                        if (more.getVisibility() == View.VISIBLE) {
                            more.setVisibility(View.GONE);
                        } else {
                            more.setVisibility(View.VISIBLE);
                        }
                    }});
                buttoninfo.setTag(getItemId(getT()));
                   /* buttoninfo.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Intent in = new Intent(v.getContext(), EmployerOffert.class);
                                Log.d("EMPROFFERT", "clicked: "+getT());
                                in.putExtra("_idoffert", getItemId(getT()));
                                v.getContext().startActivity(in);
                            }
                        });*/

                    }

            }





