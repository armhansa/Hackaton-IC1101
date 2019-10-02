package com.armhansa.hackaton.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.armhansa.hackaton.R
import com.armhansa.hackaton.listener.OnBluetoothDeviceItemClick
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_bluetooth_username.*

class BluetoothDeviceAdapter(private val view: OnBluetoothDeviceItemClick) :
    RecyclerView.Adapter<BluetoothDeviceViewHolder>() {

    private var bluetoothDevices: MutableSet<String> = mutableSetOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothDeviceViewHolder {
        return BluetoothDeviceViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bluetooth_username, parent, false),
            view
        )
    }

    override fun getItemCount(): Int = bluetoothDevices.count()

    override fun onBindViewHolder(holder: BluetoothDeviceViewHolder, position: Int) {
        holder.bind(bluetoothDevices.elementAt(position))
    }

    fun reset() {
        bluetoothDevices = mutableSetOf()
        notifyDataSetChanged()
    }

    fun isNew(btName: String): Boolean {
        return bluetoothDevices.indexOf(btName) == -1
    }

    fun addBluetoothDevice(bluetoothDeviceName: String) {
        bluetoothDevices.add(bluetoothDeviceName)
        notifyDataSetChanged()
    }

}

class BluetoothDeviceViewHolder(
    override val containerView: View,
    private val view: OnBluetoothDeviceItemClick
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(bluetoothDeviceName: String) {
        tvUsername.text = bluetoothDeviceName
        itemView.setOnClickListener {
            view.onClickBluetoothItem(bluetoothDeviceName)
        }
    }

}