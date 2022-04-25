package com.jossan.todoApp

import android.os.Parcel
import android.os.Parcelable

class ListItem(var name: String, var items: ArrayList<Item>?) : Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString()?: "",
        items = parcel.createTypedArrayList(Item.CREATOR)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListItem> {
        override fun createFromParcel(parcel: Parcel): ListItem {
            return ListItem(parcel)
        }

        override fun newArray(size: Int): Array<ListItem?> {
            return arrayOfNulls(size)
        }
    }

}