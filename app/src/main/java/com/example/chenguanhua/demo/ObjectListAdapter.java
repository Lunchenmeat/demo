package com.example.chenguanhua.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ObjectListAdapter extends RecyclerView.Adapter<ObjectListAdapter.ObjectViewHolder> {

    private final LayoutInflater mInflater;
    private List<Object> mObjects; // Cached copy of Objects

    ObjectListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public ObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ObjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ObjectViewHolder holder, int position) {
        if (mObjects != null) {
            Object current = mObjects.get(position);
            holder.objectCodeView.setText(current.getCode());
            Log.d("WEIRD", "What is the name " + current.getName());

            holder.objectNameView.setText(current.getName());
            holder.objectLineView.setText(current.getLine());
        } else {
            // Covers the case of data not being ready yet.
            holder.objectCodeView.setText("No Object");
            holder.objectCodeView.setText("No Name");
            holder.objectCodeView.setText("No Line");

        }
    }

    void setObjects(List<Object> objects){
        mObjects = objects;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mObjects has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mObjects != null)
            return mObjects.size();
        else return 0;
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        private final TextView objectCodeView;
        private final TextView objectNameView;
        private final TextView objectLineView;

        private ObjectViewHolder(View itemView) {
            super(itemView);
            objectCodeView = itemView.findViewById(R.id.codeView);
            objectNameView = itemView.findViewById(R.id.nameView);
            objectLineView = itemView.findViewById(R.id.lineView);
        }
    }
}