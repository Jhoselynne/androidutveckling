package com.jossan.todoApp

import android.os.Parcel
import android.os.Parcelable

class Item (var name: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        name = parcel.readString()?: "",
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(name)
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}