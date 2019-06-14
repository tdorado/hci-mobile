package com.itba.hci.smarthome.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
import com.itba.hci.smarthome.model.entities.DeviceTypes;
import com.itba.hci.smarthome.view.fragmentView.ClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.ViewHolder> {
    private List<Device> devices;
    private ClickListener clickListener;
    private Context context;

    public DevicesAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setList(List<Device> devices) {
        this.devices = devices;
    }

    @NonNull
    @Override
    public DevicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.row_device, viewGroup, false);

        return new DevicesAdapter.ViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesAdapter.ViewHolder viewHolder, int i) {
        //aca se bindea toda la data piola vago, el i es la position dentro de la lista de productos
        Device device = devices.get(i);

        viewHolder.device = device;
        viewHolder.name.setText(device.getName());
        switch(device.getTypeId()){
            case "go46xmbqeomjrsjr": //lamp
                viewHolder.status.setVisibility(View.VISIBLE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_lamp));
                break;
            case "li6cbv5sdlatti0j": //ac
                viewHolder.status.setVisibility(View.VISIBLE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_ac));
                break;
            case "eu0v2xgprrhhg41g": // blinds
                viewHolder.status.setVisibility(View.GONE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_blinds));
                break;
            case "mxztsyjzsrq7iaqc": // alarm
                viewHolder.status.setVisibility(View.VISIBLE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_alarm));
                break;
            case "lsf78ly0eqrjbz91": // door
                viewHolder.status.setVisibility(View.GONE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_door));
                break;
            case "im77xxyulpegfmv8": // oven
                viewHolder.status.setVisibility(View.VISIBLE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_oven));
                break;
            case "rnizejqr2di0okho": // refrigerator
                viewHolder.status.setVisibility(View.GONE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_refrigerator));
                break;
            case "ofglvd9gqX8yfl3l": // timer
                viewHolder.status.setVisibility(View.VISIBLE);
                viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_timer));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return devices == null ? 0 : devices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Device device;

        @BindView(R.id.device_name)
        TextView name;

        @BindView(R.id.device_icon)
        ImageView icon;

        @BindView(R.id.device_status)
        Switch status;

        @BindView(R.id.device_edit_button)
        ImageButton editButton;

        private ClickListener clickListener;


        public ViewHolder(View v) {
            super(v);
        }

        @OnClick(R.id.device_edit_button)
        public void onEditDeviceClick(){
            clickListener.onClick(1, device.getId());
        }

        @OnClick(R.id.content_device)
        public void onDeviceClick() {
            clickListener.onClick(0, device.getId());
        }

        public ViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
        }
    }
}