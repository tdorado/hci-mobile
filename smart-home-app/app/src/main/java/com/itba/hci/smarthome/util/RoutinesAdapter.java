package com.itba.hci.smarthome.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Routine;
import com.itba.hci.smarthome.view.fragmentView.ClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoutinesAdapter {
    private List<Routine> routines;
    private ClickListener clickListener;

    public RoutinesAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setList(List<Routine> routines){
        this.routines = routines;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Routine routine;

        @BindView(R.id.routine_name)
        TextView name;

        @BindView(R.id.routine_button)
        Button button;

        private ClickListener clickListener;

        public ViewHolder(View v) {
            super(v);
        }

        @OnClick(R.id.routine_button)
        public void onDeviceClick() {
            clickListener.onClick(routine.getId());
        }

        public ViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
        }
    }
}
