/*
package pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.EmployerOffert;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import pl.kalisz.pwsz.zi.marcin.majzner.pracapoplaca.R;

public class EmployerOffertInfoFragment extends Fragment {

    private long employeroffert_id;

    public EmployerOffertInfoFragment(){

    }
@Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState !=null){
            employeroffert_id =savedInstanceState.getLong("employeroffert_id");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        outState.putLong("employeroffert_id", employeroffert_id);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_employeroffert_info, container, false);
//    }

    public void setObiekt(long id){
        this.employeroffert_id= id;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view!=null){
           // ##########
         //   EmployerOffert EMPLOYER_OFFERT = EmployerOffert.lista[(int) obiektId];
            // ##########
//            TextView EMPLOYER_OFFERT_NAME = (TextView) view.findViewById(R.id.employer_offert_textview_name);
//            EMPLOYER_OFFERT_NAME.setText(EMPLOYER_OFFERT.getName());
//            TextView EMPLOYER_OFFERT_DESC = (TextView)view.findViewById(R.id.employer_offert_textview_desc);
//            EMPLOYER_OFFERT_DESC .setText(string_desc_cuter(EMPLOYER_OFFERT.getDescription()));
//            ImageView EMPLOYER_OFFERT_AVATAR = (ImageView)view.findViewById(R.id.employer_offert_avatar_img);
//            // foto po nr category avatar
//            Drawable drawable = ContextCompat.getDrawable(view.getContext(), EMPLOYER_OFFERT.getAvatarId());
//            EMPLOYER_OFFERT_AVATAR.setImageDrawable(drawable);

        }

    }

    public String string_desc_cuter(String desc, int percentage){
        String temp="";
        for(int ITERATOR=0;ITERATOR < (int)Math.round(desc.length()*(percentage/100));ITERATOR++){
            temp+=desc.charAt(ITERATOR);}



        return temp;
    }


}
*/
