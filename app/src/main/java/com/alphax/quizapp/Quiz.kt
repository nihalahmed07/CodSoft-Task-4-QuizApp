package com.alphax.quizapp
import android.os.Parcel
import android.os.Parcelable

data class Quiz(
    val title: String,
    val questions: List<Question>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createTypedArrayList(Question.CREATOR)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(questions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Quiz> = object : Parcelable.Creator<Quiz> {
            override fun createFromParcel(parcel: Parcel): Quiz {
                return Quiz(parcel)
            }

            override fun newArray(size: Int): Array<Quiz?> {
                return arrayOfNulls(size)
            }
        }
    }
}


data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeStringList(options)
        parcel.writeInt(correctAnswer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}
