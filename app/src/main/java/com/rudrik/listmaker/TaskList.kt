package com.rudrik.listmaker

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//  for taskList model
@Parcelize
data class TaskList(var name: String, var tasksList: List<String> = listOf<String>()) : Parcelable{
        companion object {
            fun isNull(t: TaskList): Boolean {
                return (t == null)
            }
        }

}

/*

{

    override fun describeContents(): Int = 0

    //1 for reading from parcel
    */
/*
        Reading from a Parcel: Here, you’re adding a second constructor (as opposed to
        the primary constructor in the class declaration) so that a TaskList object can be
        created from a passed-in Parcel.
        The constructor grabs the values from the Parcel for the title (by calling readString
        on the Parcel) and the list of tasks (by calling createStringArrayList on the
        Parcel), then passes them into the primary constructor using this().
        Note that readString() and createStringArrayList() return optionals.
    * *//*

    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.createStringArrayList() ?: listOf<String>()
    )

    //2 for writing to a parcel
    */
/*
        This method is called when a Parcel needs to be created from
        the TaskList object. The parcel being created is handed into this function, and you
        fill it in with the appropriate contents using the assorted write... functions.
    * *//*

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.let {
            it.writeString(name)
            it.writeStringList(tasksList)
        }
    }

    //3 for Fulfilling static interface requirements
    */
/*
        The Parcelable protocol requires you
        to create a public static Parcelable.Creator<T> CREATOR field and override some
        methods in it using Java.
    * *//*

    companion object CREATOR : Parcelable.Creator<TaskList> {

        //4 for Calling constructor
        */
/*
            In the CREATOR companion object, you override the
            interface function createFromParcel, and pass the parcel you get from this function
            along to the second constructor you just created, giving back a nice new TaskList
            with all of the data from the Parcel.
        * *//*

        override fun createFromParcel(source: Parcel): TaskList =
            TaskList(source)

        override fun newArray(size: Int): Array<TaskList?> =
            arrayOfNulls<TaskList>(size)

//        fun isNull(t:TaskList): Boolean {
//            return (t==null)
//        }
    }

}*/
