package com.example.citybang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListviewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> chapterList;
    private HashMap<String, List<String>> topicsList;

    public ExpandableListviewAdapter(Context context, List<String> chapterList, HashMap<String, List<String>> topicsList) {
        this.context = context;
        this.chapterList = chapterList;
        this.topicsList = topicsList;
    }

    @Override
    public int getGroupCount() {
        return this.chapterList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.topicsList.get(this.chapterList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.chapterList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.topicsList.get(this.chapterList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String chapterTitle = (String) getGroup(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.chapter_list, null);
        }
        TextView chapter_tv = view.findViewById(R.id.chapter_tv);
        chapter_tv.setText(chapterTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String topicTitle = (String) getChild(i,i1);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.topic_list, null);
        }
        TextView topic_tv = view.findViewById(R.id.topics_tv);
        topic_tv.setText(topicTitle);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
