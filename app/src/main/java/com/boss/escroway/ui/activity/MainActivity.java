package com.boss.escroway.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.boss.escroway.R;
import com.boss.escroway.data.retrofit.EscrowayAPI;
import com.boss.escroway.data.retrofit.JsonPlaceholderAPI;
import com.boss.escroway.data.retrofit.models.Good;
import com.boss.escroway.data.retrofit.models.Post;
import com.boss.escroway.data.retrofit.models.Subcategory;
import com.boss.escroway.data.retrofit.models.Wrapper;
import com.boss.escroway.ui.fragment.GoodsFragment;
import com.boss.escroway.ui.fragment.GoodsSubCategoryFragment;
import com.boss.escroway.ui.fragment.ServicesFragment;
import com.boss.escroway.ui.fragment.ServicesSubCategoryFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements GoodsSubCategoryFragment.OnFragmentInteractionListener,
        ServicesSubCategoryFragment.OnFragmentInteractionListener{
    Context mContext = this;
    private FragmentManager mFragmentManager;
    private View mButton1;
    private View mButton2;
    private TextView mTextview;
    private List<Good> mGoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new GoodsFragment());
        fragmentTransaction.commit();

        initViews();
        onClickGoods();
        onClickServices();

        mTextview = findViewById(R.id.text_dummy);

    }

    // for the left button
    private void onClickGoods() {
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked goods", Toast.LENGTH_LONG).show();

                //change button1 to active
                mButton1.setBackgroundResource(R.drawable.widget_rounded_button_left_active);
                mButton1.findViewById(R.id.image_button_1).setBackgroundResource(R.drawable.ic_vector_check);

                TextView text1 = mButton1.findViewById(R.id.text1_button1);
                text1.setTextColor(getResources().getColor(R.color.white, getTheme()));

                TextView text2 = mButton1.findViewById(R.id.text2_button1);
                text2.setTextColor(getResources().getColor(R.color.white, getTheme()));

                //change button2 to inactive
                mButton2.setBackgroundResource(R.drawable.widget_rounded_button_right_inactive);
                mButton2.findViewById(R.id.image_button_2).setBackgroundResource(R.drawable.ic_vector_circle);

                TextView text3 = mButton2.findViewById(R.id.text1_button2);
                text3.setTextColor(getResources().getColor(R.color.black, getTheme()));

                TextView text4 = mButton2.findViewById(R.id.text2_button2);
                text4.setTextColor(getResources().getColor(R.color.black, getTheme()));


                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new GoodsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    // for right button
    private void onClickServices() {
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked services", Toast.LENGTH_LONG).show();

                //change button2 to active
                mButton2.setBackgroundResource(R.drawable.widget_rounded_button_right_active);
                mButton2.findViewById(R.id.image_button_2).setBackgroundResource(R.drawable.ic_vector_check);

                TextView text1 = mButton2.findViewById(R.id.text1_button2);
                text1.setTextColor(getResources().getColor(R.color.white, getTheme()));

                TextView text2 = mButton2.findViewById(R.id.text2_button2);
                text2.setTextColor(getResources().getColor(R.color.white, getTheme()));

                //change button1 to inactive
                mButton1.setBackgroundResource(R.drawable.widget_rounded_button_left_inactive);
                mButton1.findViewById(R.id.image_button_1).setBackgroundResource(R.drawable.ic_vector_circle);

                TextView text3 = mButton1.findViewById(R.id.text1_button1);
                text3.setTextColor(getResources().getColor(R.color.black, getTheme()));

                TextView text4 = mButton1.findViewById(R.id.text2_button1);
                text4.setTextColor(getResources().getColor(R.color.black, getTheme()));

                mFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new ServicesFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void setupNextButton() {

    }

    private void initViews() {
        mButton2 = findViewById(R.id.button2);
        mButton1 = findViewById(R.id.button1);
    }

    @Override
    public void onFragmentInteraction() {

    }

    @Override
    public void activateNextButton() {
        ImageView arrowImageView = findViewById(R.id.next_arrow);
        TextView textViewNext = findViewById(R.id.text_next);
        arrowImageView.setBackgroundResource(R.drawable.ic_next_active);
        textViewNext.setTextColor(getResources().getColor(R.color.white, getTheme()));
    }

    @Override
    public void deactivateNextButton() {
        ImageView arrowImageView = findViewById(R.id.next_arrow);
        TextView textViewNext = findViewById(R.id.text_next);

        arrowImageView.setBackgroundResource(R.drawable.ic_next_inactive);
        textViewNext.setTextColor(getResources().getColor(R.color.light_grey, getTheme()));
    }
}
