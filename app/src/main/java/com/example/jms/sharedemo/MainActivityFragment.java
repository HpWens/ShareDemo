package com.example.jms.sharedemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private Button textBtn;
    private Button simpleBtn;
    private Button moreBtn;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textBtn= (Button) view.findViewById(R.id.text);
        simpleBtn= (Button) view.findViewById(R.id.simple);
        moreBtn= (Button) view.findViewById(R.id.more);

        textBtn.setOnClickListener(this);
        simpleBtn.setOnClickListener(this);
        moreBtn.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "我是一颗小小的石头");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "分享的文字"));
                break;
            case  R.id.simple:

                Uri uriImage = Uri.parse("content://media/external/images/media/19641");
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriImage);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, "分享的图片"));

                break;
            case R.id.more:

                ArrayList<Uri> listImage = new ArrayList<>();
                Uri imageUri0 = Uri.parse("content://media/external/images/media/171");
                Uri imageUri1 = Uri.parse("content://media/external/images/media/165");
                listImage.add(imageUri0);
                listImage.add(imageUri1);
                Intent moreIntent = new Intent();
                moreIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                moreIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, listImage);
                moreIntent.setType("image/*");
                startActivity(Intent.createChooser(moreIntent, "分享多张图片"));

                break;

            default:
        }
    }
}
