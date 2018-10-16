package sportsapp.sashi.in.sportsapp.ui.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.ui.activities.HomeActivity;

public class VideoOfDayFragment extends DialogFragment {

    private static final String TAG = VideoOfDayFragment.class.getSimpleName();

    private Toolbar toolbar;
    private TextView actionTV;
    private VideoView videoView;

    private MediaController controller;
    private Uri vidUri;
    private String videoUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFullScreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vid_day, container, false);

        initViews(v);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.action_close));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                skipVOD();
            }
        });
        toolbar.setTitle("Video of Day");

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int w = ViewGroup.LayoutParams.MATCH_PARENT;
            int h = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(w, h);
        }
    }

    private void skipVOD() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    private void initViews(View v) {
        toolbar = v.findViewById(R.id.toolbar);
        actionTV = v.findViewById(R.id.actionTV);
        videoView = v.findViewById(R.id.videoView);
    }
}
