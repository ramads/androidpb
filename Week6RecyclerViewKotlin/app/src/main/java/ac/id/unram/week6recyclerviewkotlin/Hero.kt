package ac.id.unram.week6recyclerviewkotlin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    var name: String,
    var description: String,
    var photo: String
) : Parcelable