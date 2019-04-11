package genius.paad.com.myweather.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import genius.paad.com.myweather.R;

public class NoTownFragment extends DialogFragment {

    TextView text;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment,
                container, false);
        text = (TextView) view.findViewById(R.id.textView1);

        text.setText(getResources().getString(R.string.notown));

        return view;
    }
}
