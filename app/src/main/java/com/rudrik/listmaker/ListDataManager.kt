package com.rudrik.listmaker

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

//  for repository
class ListDataManager(context: Context) {

    //  for default prefs
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    //  for saving task
    fun saveList(list: TaskList) {
        prefs.edit(true) {
            putStringSet(list.name, list.tasksList.toHashSet())
        }
    }

    //  for reading tasks
    fun readList(): MutableList<TaskList> {
        val list = prefs.all.toList()

        val taskLists = mutableListOf<TaskList>()
        list.onEach {
            val t = TaskList(it.first)
            t.tasksList = (it.second as HashSet<String>).toList()
            taskLists.add(t)
        }

        return taskLists
    }

}