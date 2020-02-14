package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskukirjaListAdapter extends RecyclerView.Adapter<TaskukirjaListAdapter.TaskukirjaViewHolder> {

    class TaskukirjaViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskukirjaItemView;

        private TaskukirjaViewHolder(View itemView) {
            super(itemView);
            taskukirjaItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Taskukirja> mTaskukirjat; // Cached copy of words

    TaskukirjaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public TaskukirjaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TaskukirjaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskukirjaViewHolder holder, int position) {
        if (mTaskukirjat != null) {
            Taskukirja current = mTaskukirjat.get(position);
            holder.taskukirjaItemView.setText(current.getMNumero()+" "+current.getMNimi());
        } else {
            // Covers the case of data not being ready yet.
            holder.taskukirjaItemView.setText("No Word");
        }
    }

    void setTaskukirjat(List<Taskukirja> taskukirjat){
        mTaskukirjat = taskukirjat;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTaskukirjat != null)
            return mTaskukirjat.size();
        else return 0;
    }
}
