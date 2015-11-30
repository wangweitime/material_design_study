package com.wangwei.material;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by wangwei on 11/27/15.
 */
public class TittleFragment extends ListFragment {
    static final int SHOW_RIPPLE = 0;
    static final int SHOW_REVEAL = 1;
    static final int SHOW_TRANSITION = 2;
    static final int SHOW_TRANSITION_SHARE = 3;
    int mIndex = 0;

    static String[] sTittles ={"Ripple","Reveal","Transition","Share Transition"};
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.simple_list_item_checkable,
                android.R.id.text1, sTittles));

        ChangeFragment(SHOW_REVEAL);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ChangeFragment(position);
    }

    void ChangeFragment(int index){

        // Check what fragment is currently shown, replace if needed.
        Fragment details = (Fragment)
                getFragmentManager().findFragmentById(R.id.frag_content);
        //if (details == null || mIndex != index)
        {
            mIndex = index;
            Intent intent=null;
            View ripple = null;
            // Make new fragment to show this selection.
            switch (index) {
                case SHOW_RIPPLE:
                   /* details = new RippleFragment();

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frag_content, details)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();*/
                    if(details!=null){getActivity().findViewById(R.id.frag_content).setVisibility(View.INVISIBLE);}
                    ripple = getActivity().findViewById(R.id.ripple);
                    if(ripple!=null){
                        ripple.setVisibility(View.VISIBLE);
                    }

                    break;
                case SHOW_REVEAL:

                    getActivity().findViewById(R.id.frag_content).setVisibility(View.VISIBLE);
                    ripple = getActivity().findViewById(R.id.ripple);
                    if(ripple!=null){
                        ripple.setVisibility(View.INVISIBLE);
                    }

                    details = new RevealFragment();

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frag_content, details)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                    break;
                case SHOW_TRANSITION:
                    intent = new Intent( getActivity(), ItemListActivity.class );
                    //startActivity(intent);
                    startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                    break;
                case SHOW_TRANSITION_SHARE:
                    if(details!=null){
                        details.getView().setVisibility(View.INVISIBLE);
                    }
                    ripple = getActivity().findViewById(R.id.ripple);
                    if(ripple!=null){
                        ripple.setVisibility(View.VISIBLE);
                    }
                    View shareview =getActivity().findViewById(R.id.robot);
                    intent = new Intent( getActivity(), LoginActivity.class );
                    //startActivity(intent);
                    startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(getActivity(),shareview,"robot").toBundle()
                    );
                    break;
            }
        }
    }
}
