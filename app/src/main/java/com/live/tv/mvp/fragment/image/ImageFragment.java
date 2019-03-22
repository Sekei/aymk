package com.live.tv.mvp.fragment.image;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ysjk.health.iemk.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by taoh on 2017/11/16.
 */

public class ImageFragment extends Fragment {

    private static final String IMAGE_URL = "image";
    PhotoView image;
    private String imageUrl;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(String param1) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getString(IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        image = (PhotoView) view.findViewById(R.id.image);
        image.setOnPhotoTapListener(new PhotoTapListener());
       // Glide.with(getContext()).load(imageUrl).into(image);


        Glide.with(getContext()).load(imageUrl)
                .placeholder(R.drawable.pic_defaults)
                .error(R.drawable.pic_defaults)
                .crossFade(800)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(image);





        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {
        @Override
        public void onPhotoTap(View view, float v, float v1) {
            getActivity().finish();
        }

        @Override
        public void onOutsidePhotoTap() {
            getActivity().finish();
        }
    }
}