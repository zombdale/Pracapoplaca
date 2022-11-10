package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


    public class RVAdapter_user_offert extends RecyclerView.Adapter<RVAdapter_user_offert.ViewHolder> {
        private String[][] data;
        private CardView cv ;


        public static class ViewHolder extends RecyclerView.ViewHolder  {

            private CardView cardView;

            public ViewHolder(CardView v) {
                super(v);
                cardView = v;
            }

            public CardView getCardView() {
                return cardView;
            }
        }

        public RVAdapter_user_offert(String[][] data){
            this.data= data;

        }
        @Override
        public long getItemId(int position) {
            return Integer.parseInt(data[position][0]);
        }

        @Override
        public int getItemCount(){ return this.data.length;}

        @Override
        public RVAdapter_user_offert.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate
                    (R.layout.user_offerts_cardview, viewGroup, false);
            //  CardView cv= (CardView) findViewById(R.id.CV);
            return new RVAdapter_user_offert.ViewHolder(cv);

        }
        @Override
        public void onBindViewHolder(RVAdapter_user_offert.ViewHolder holder, int i){
            CardView cardView = holder.cardView;
            TextView obiektid= (TextView) cardView.findViewById(R.id.empr_offert_id);
            TextView obiektNazwa = (TextView) cardView.findViewById(R.id.empr_offert_nazwa);
            LinearLayout more = (LinearLayout) cardView.findViewById(R.id.empr_offert_more);
            TextView obiektNazwal = (TextView) cardView.findViewById(R.id.empr_offert_nazwa_l);
            Log.v("#BXI", "i :"+i+"");
            obiektNazwa.setText(data[i][1]);
            obiektNazwal.setText(data[i][1]);
            obiektid.setText("i");
            more.setTag(data[i][0]);
            //cardView.setOnTouchListener();

        }


    }



