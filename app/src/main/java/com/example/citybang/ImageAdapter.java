package com.example.citybang;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

class ImageAdapter extends BaseAdapter{
    Context context;

    //가데이터
    private Integer[] mThumblds ={R.drawable.test1, R.drawable.test1, R.drawable.test1,
            R.drawable.test1, R.drawable.test1, R.drawable.test1,
            R.drawable.test1, R.drawable.test1, R.drawable.test1,

    };

    public ImageAdapter(Context context){

        this.context = context;
    }

    @Override
    public int getCount() {

        return mThumblds.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(350,300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else{
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumblds[position]);
        return imageView;
    }
}