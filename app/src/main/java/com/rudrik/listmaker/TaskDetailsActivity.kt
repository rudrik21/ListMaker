package com.rudrik.listmaker

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.rudrik.listmaker.databinding.ActivityTaskDetailsBinding

class TaskDetailsActivity : AppCompatActivity() {
    private lateinit var taskDetails: TaskList
    private lateinit var adpt: AdptListSelection
    private lateinit var listDataManager: ListDataManager
    private lateinit var bind: ActivityTaskDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(bind.root)

        //  for showing back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Task Details"

        checkDetails()
    }

    //  for checking passed data
    private fun checkDetails() {
        intent.getParcelableExtra<TaskList>(MainActivity.INTENT_TASK_DETAILS)?.let {
            taskDetails = it
        }

        if (!TaskList.isNull(taskDetails)){
            init()
//            taskDetails.toString().toast(this)
        }
    }

    //  for initialization
    private fun init() {
        listDataManager = ListDataManager(this)

        bind.tvTaskName.text = taskDetails.name

        //  for converting List<String> to List<TaskList>
        val list = mutableListOf<TaskList>()
        taskDetails.tasksList.onEach {
            list.add(TaskList(name = it))
        }
        adpt = AdptListSelection(list)
        bind.contentTaskDetails.reyclerView.adapter = adpt
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            //  for click event on back button to close this activity
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}