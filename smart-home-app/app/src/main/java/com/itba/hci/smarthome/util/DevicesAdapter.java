package com.itba.hci.smarthome.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.itba.hci.smarthome.R;
import com.itba.hci.smarthome.model.entities.Device;
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_device, viewGroup, false);

        return new DevicesAdapter.ViewHolder(v, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesAdapter.ViewHolder viewHolder, int i) {
        //aca se bindea toda la data piola vago, el i es la position dentro de la lista de productos
        Device deviceResponse = devices.get(i);

        viewHolder.deviceResponse = deviceResponse;
        viewHolder.name.setText(deviceResponse.getName());
        viewHolder.icon.setImageDrawable(context.getDrawable(R.drawable.ic_kitchen_black_36dp));
        //Me falta ver como conseguir status
    }

    @Override
    public int getItemCount() {
        return devices == null ? 0 : devices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Device deviceResponse;

        @BindView(R.id.device_name)
        TextView name;

        @BindView(R.id.device_icon)
        ImageView icon;

        @BindView(R.id.device_status)
        Switch status;

        private ClickListener clickListener;


        public ViewHolder(View v) {
            super(v);
        }

        @OnClick(R.id.content_device)
        public void onDeviceClick() {
            clickListener.onClick(deviceResponse.getId());
        }

        public ViewHolder(@NonNull View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            ButterKnife.bind(this, itemView);
        }
    }
}