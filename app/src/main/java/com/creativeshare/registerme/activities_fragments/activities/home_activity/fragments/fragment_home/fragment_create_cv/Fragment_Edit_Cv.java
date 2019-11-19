package com.creativeshare.registerme.activities_fragments.activities.home_activity.fragments.fragment_home.fragment_create_cv;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.creativeshare.registerme.R;
import com.creativeshare.registerme.activities_fragments.activities.home_activity.activity.Home_Activity;
import com.creativeshare.registerme.adapter.ImagesAdapter;
import com.creativeshare.registerme.adapter.Spinner_HandGrafuation_Adapter;
import com.creativeshare.registerme.adapter.Spinner_Qulificatin_Adapter;
import com.creativeshare.registerme.adapter.Spinner_Skills_Adapter;
import com.creativeshare.registerme.models.AllInFo_Model;
import com.creativeshare.registerme.models.UserModel;
import com.creativeshare.registerme.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Edit_Cv extends Fragment {
    private ImageView image_selection;
    private Preferences preferences;
    private UserModel userModel;
    private Home_Activity activity;

    private Spinner_Qulificatin_Adapter spinner_qulificatin_adapter;
    private Spinner_HandGrafuation_Adapter spinner_handGrafuation_adapter;
    private Spinner_Skills_Adapter spinner_skills_adapter;

    private List<AllInFo_Model.Data.Quallifcation> quallifcationList;
    private List<AllInFo_Model.Data.HandGraduations> handGraduationsList;
    private List<AllInFo_Model.Data.Skills> skillsList;

    //private RecyclerView recyclerView_images;
    private Spinner spinner_qualification, spinner_handgraduate, spinner_skill;
    private EditText edt_email,edt_note;
    private Button bt_Send;
    private int qulifid=0,skillid=0,qradutateid=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_cv, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        quallifcationList = new ArrayList<>();
        handGraduationsList = new ArrayList<>();
        skillsList = new ArrayList<>();
        activity = (Home_Activity) getActivity();
        preferences = Preferences.getInstance();
        userModel = preferences.getUserData(activity);
        image_selection = view.findViewById(R.id.imageSelectPhoto);
        //recyclerView_images = view.findViewById(R.id.recView);
        spinner_qualification = view.findViewById(R.id.spinner_qualification);
        spinner_handgraduate = view.findViewById(R.id.spinner_hanfgraduate);
        spinner_skill = view.findViewById(R.id.spinner_skill);
        edt_email=view.findViewById(R.id.edt_email);
        edt_note=view.findViewById(R.id.edt_note);
        bt_Send=view.findViewById(R.id.btn_send);
        spinner_qulificatin_adapter = new Spinner_Qulificatin_Adapter(activity, quallifcationList);
        spinner_handGrafuation_adapter = new Spinner_HandGrafuation_Adapter(activity, handGraduationsList);
        spinner_skills_adapter = new Spinner_Skills_Adapter(activity, skillsList);
        spinner_qualification.setAdapter(spinner_qulificatin_adapter);
        spinner_handgraduate.setAdapter(spinner_handGrafuation_adapter);



       // recyclerView_images.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false));

        spinner_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    qulifid =0 ;
                } else {
                    qulifid = quallifcationList.get(position).getId();
                    // Move_Data_Model.setcityt(to_city);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_handgraduate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    qradutateid =0 ;
                } else {
                    qradutateid = handGraduationsList.get(position).getId();
                    // Move_Data_Model.setcityt(to_city);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_skill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    skillid =0 ;
                } else {
                    skillid = skillsList.get(position).getId();
                    // Move_Data_Model.setcityt(to_city);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkdata();
            }
        });
    }

    private void checkdata() {
        String email=edt_email.getText().toString();
        String note=edt_note.getText().toString();
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(note)&& Patterns.EMAIL_ADDRESS.matcher(email).matches()&&qulifid!=0&&qradutateid!=0&&skillid!=0){

        }
        else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edt_email.setError(getResources().getString(R.string.inv_email));
            }
            if(TextUtils.isEmpty(email)){
                edt_email.setError(getResources().getString(R.string.field_req));
            }
            if(TextUtils.isEmpty(note)){
                edt_note.setError(getResources().getString(R.string.field_req));
            }
            if(qradutateid==0){
                Toast.makeText(activity,getResources().getString(R.string.choose_handgradauted),Toast.LENGTH_LONG).show();
            }
            if(qulifid==0){
                Toast.makeText(activity,getResources().getString(R.string.choose_qalified),Toast.LENGTH_LONG).show();
            }
            if(skillid==0){
                Toast.makeText(activity,getResources().getString(R.string.choose_Skill),Toast.LENGTH_LONG).show();
            }
        }
    }
    public static Fragment_Edit_Cv newInstance() {
        return new Fragment_Edit_Cv();
    }

}
