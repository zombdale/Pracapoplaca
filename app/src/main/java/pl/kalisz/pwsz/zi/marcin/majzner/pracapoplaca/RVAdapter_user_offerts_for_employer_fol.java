package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployeeOffert.EmployeeOffert;


    public class RVAdapter_user_offerts_for_employer_fol   extends RecyclerView.Adapter<pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.RVAdapter_user_offerts_for_employer_fol .ViewHolder> {
        private String[][] data;
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

        public RVAdapter_user_offerts_for_employer_fol  (String[][] data) {
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
        public pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.RVAdapter_user_offerts_for_employer_fol  .ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate
                    (R.layout.fol_user_offerts_for_employer_cardview, viewGroup, false);
            //  CardView cv= (CardView) findViewById(R.id.CV);
            return new pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.RVAdapter_user_offerts_for_employer_fol  .ViewHolder(cv);

        }

        @Override
        public void onBindViewHolder(pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.RVAdapter_user_offerts_for_employer_fol  .ViewHolder holder, int i) {
            CardView cardView = holder.cardView;
            TextView nazwa = (TextView) cardView.findViewById(R.id.f_empe_offert_nazwa);
            TextView city = (TextView) cardView.findViewById(R.id.f_empe_offert_city);
            Log.v("#BXI", "i :" + i + "");
            nazwa.setText(data[i][0]);
            city.setText(data[i][1]);
            setT(Integer.parseInt(data[i][2]));
          //  kateg.setText(cardView.getResources().getStringArray(R.array.category_list)[Integer.parseInt(data[i][2])]);
            cardView.findViewById(R.id.f_empe_offert_b).setTag(getT());

        }

    }






