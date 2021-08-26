package com.aldev.pokemontcg.data.source.remote.response

import android.os.Parcel
import android.os.Parcelable

data class Card(
    val id: String?,
    val name: String?,
    val rarity: String?,
    val images: CardImage?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("images")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(rarity)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}

data class CardImage(
    val small: String,
    val large: String
)

data class CardResponse(
    val data: MutableList<Card>
)


