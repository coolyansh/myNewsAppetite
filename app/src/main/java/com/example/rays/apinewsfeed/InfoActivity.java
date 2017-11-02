package com.example.rays.apinewsfeed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by rays on 8/30/2017.
 */

public class InfoActivity extends AppCompatActivity{

    boolean mShowingBack1=false;
    boolean mShowingBack2=false;
    boolean mShowingBack3=false;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        AdView adView=(AdView)findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        fragmentManager=getFragmentManager();

        fragmentManager.beginTransaction().add(R.id.first_container,new first_front_fragment()).commit();
        fragmentManager.beginTransaction().add(R.id.second_container,new second_front_fragment()).commit();
        fragmentManager.beginTransaction().add(R.id.third_container,new third_front_fragment()).commit();

        FrameLayout frameLayout1=(FrameLayout)findViewById(R.id.first_container);
        frameLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard1();
            }
        });

        FrameLayout frameLayout2=(FrameLayout)findViewById(R.id.second_container);
        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard2();
            }
        });

        FrameLayout frameLayout3=(FrameLayout)findViewById(R.id.third_container);
        frameLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard3();
            }
        });

    }

    private void flipCard1() {
        if (mShowingBack1) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in,
                            R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out)
                    .replace(R.id.first_container, new first_front_fragment())
                    .commit();
            mShowingBack1=false;
            return;
        }

        // Flip to the back.

        mShowingBack1 = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        fragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.first_container, new first_back_fragment())
                .commit();
    }

    private void flipCard2() {
        if (mShowingBack2) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in,
                            R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out)
                    .replace(R.id.second_container, new second_front_fragment())
                    .commit();
            mShowingBack2=false;
            return;
        }

        // Flip to the back.

        mShowingBack2 = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        fragmentManager
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.second_container, new second_back_fragment())
                // Commit the transaction.
                .commit();
    }

    private void flipCard3() {
        if (mShowingBack3) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(
                            R.animator.card_flip_right_in,
                            R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out)
                    .replace(R.id.third_container, new third_front_fragment())
                    .commit();
            mShowingBack3=false;
            return;
        }

        // Flip to the back.

        mShowingBack3 = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        fragmentManager
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.third_container, new third_back_fragment())

                // Commit the transaction.
                .commit();
    }



    public static class first_front_fragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.card_front,container,false);
            TextView textView=(TextView)view.findViewById(R.id.text_front);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            textView.setPadding(50,30,50,30);
            textView.setText("myNewsAppetite brings you the latest news from sites like the The Hindu, AlJazeera English, Engadget, ESPN, National Geographic and Fortune.");
            return view;
        }
    }

    public static class first_back_fragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.card_back,container,false);
            TextView textView=(TextView)view.findViewById(R.id.text_back);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            textView.setText("I forgot to mention \nThanks for downloading the app :)");
            return view;
        }
    }

    public static class second_front_fragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.card_front,container,false);
            TextView textView=(TextView)view.findViewById(R.id.text_front);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
            textView.setText("DEVELOPED \nBY ...");
            return view;
        }
    }

    public static class second_back_fragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.card_back,container,false);
            TextView textView=(TextView)view.findViewById(R.id.text_back);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
            textView.setText("Shreyansh Ray \n rayshreyansh06@gmail.com\n+91 8444905943");
            return view;
        }
    }

    public static class third_front_fragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.card_front,container,false);
            TextView textView=(TextView)view.findViewById(R.id.text_front);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
            textView.setText("ACKNOWLEDGEMENTS ");
            return view;
        }
    }

    public static class third_back_fragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.card_back,container,false);
            TextView textView=(TextView)view.findViewById(R.id.text_back);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            textView.setText("API SOURCE \nhttps://newsapi.org");
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
