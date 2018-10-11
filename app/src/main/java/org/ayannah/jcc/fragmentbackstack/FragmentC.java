package org.ayannah.jcc.fragmentbackstack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentC extends Fragment {

    Button btnFragA,btnFragB;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c,container,false );

        btnFragA = view.findViewById(R.id.btnForA);
        btnFragB = view.findViewById(R.id.btnForB);
        btnFragA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.chosenFragment = "A";
                FragmentActions fragmentActions = (FragmentActions) getActivity();
                fragmentActions.goToA();
            }
        });
        btnFragB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.chosenFragment = "B";
                FragmentActions fragmentActions = (FragmentActions) getActivity();
                fragmentActions.goToB();
            }
        });
        return view;
    }
}
