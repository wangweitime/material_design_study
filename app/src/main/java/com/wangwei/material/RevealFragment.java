package com.wangwei.material;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * Created by wangwei on 11/27/15.
 */
public class RevealFragment extends Fragment {
    View mView;
    Animator mAnimIn;
    Animator mAnimOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
        View view = inflater.inflate(R.layout.reveal, null);
        mView = view.findViewById(R.id.revealview);
        mView.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mView.post(new Runnable() {
            @Override
            public void run() {
                CreatAnimation();
            }
        });
    }

    void CreatAnimation(){
        // get the center for the clipping circle
        int cx = mView.getWidth() / 2;
        int cy = mView.getHeight() / 2;

// get the final radius for the clipping circle
        int Radius = Math.max(mView.getWidth(), mView.getHeight());

// create the animator for this view (the start radius is zero)
        mAnimIn =
                ViewAnimationUtils.createCircularReveal(mView, cx, cy, 0, Radius);

// make the view visible and start the animation
        mView.setVisibility(View.VISIBLE);



// create the animation (the final radius is zero)
        mAnimOut =
                ViewAnimationUtils.createCircularReveal(mView, cx, cy, Radius, 0);

// make the view invisible when the animation is done
        mAnimIn.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mView.setVisibility(View.VISIBLE);
                        //mAnimOut.end();
                        mAnimOut.start();
                    }
                }, 1000);

            }
        });

        mAnimOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.setVisibility(View.INVISIBLE);

            }
        });
        mAnimIn.setDuration(1000);
        mView.setVisibility(View.VISIBLE);
        mAnimIn.start();

    }
}
