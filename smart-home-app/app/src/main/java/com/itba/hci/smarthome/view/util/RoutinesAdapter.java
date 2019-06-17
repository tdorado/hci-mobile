package com.itba.hci.smarthome.view.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Routine;
import com.itba.hci.smarthome.view.fragmentView.ClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoutinesAdapter extends RecyclerView.Adapter<RoutinesAdapter.ViewHolder>{
    private Context context;
    private List<Routine> routines;
    private ClickListener clickListener;

    public RoutinesAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setList(List<Routine> routines){
        this.routines = routines;
    }

    @NonNull
    @Override
    public RoutinesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_routine, viewGroup, false);

        return new RoutinesAdapter.ViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RoutinesAdapter.ViewHolder viewHolder, int i) {
        Routine routine = routines.get(i);

        viewHolder.routine = routine;
        viewHolder.name.setText(routine.getName());
    }

    @Override
    public int getItemCount() {
        return routines == null ? 0 : routines.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Routine routine;

        @BindView(R.id.routine_name)
        TextView name;

        private ClickListener clickListener;

        public ViewHolder(View v) {
            super(v);
        }

        @OnClick(R.id.routine_button)
        public void onRoutineClick() {
            clickListener.onClick(0, routine.getId());
        }

        @OnClick(R.id.routine_delete_button)
        public void OnDeleteRoutineClick(){
            clickListener.onClick(1, routine.getId());
        }

        public ViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
        }
    }
}
