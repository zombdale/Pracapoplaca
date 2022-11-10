package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployeeOffert.EmployeeOffert;

public class RVAdapter_user_offerts_employee extends RecyclerView.Adapter<RVAdapter_user_offerts_employee.ViewHolder> {
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

    public RVAdapter_user_offerts_employee(String[][] data) {
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
    public RVAdapter_user_offerts_employee.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.getContext()).inflate
                (R.layout.user_offerts_employee_cardview, viewGroup, false);
        //  CardView cv= (CardView) findViewById(R.id.CV);
        return new ViewHolder(cv);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        CardView cardView = holder.cardView;
        TextView nazwa = (TextView) cardView.findViewById(R.id.empee_offert_nazwa);
        TextView kateg = (TextView) cardView.findViewById(R.id.empee_offert_cat);
        Log.v("#BXI", "i :" + i + "");
        nazwa.setText(data[i][1]);
       // kateg.setText(cardView.getResources().getStringArray(R.array.category_list)[Integer.parseInt(data[i][2])]);
        setT(Integer.parseInt(data[i][0])); //!!
        cardView.findViewById(R.id.empee_offert_b).setTag(getT());

    }

}



