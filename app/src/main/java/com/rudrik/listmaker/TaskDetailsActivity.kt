package com.rudrik.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_task_details.*
import kotlinx.android.synthetic.main.content_main.*

class TaskDetailsActivity : AppCompatActivity() {
    private lateinit var taskDetails: TaskList
    private lateinit var adpt: AdptListSelection
    private lateinit var listDataManager: ListDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

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
        tvTaskName.text = taskDetails.name
        adpt = AdptListSelection(mutableListOf<TaskList>(taskDetails))
        reyclerView.adapter = adpt
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            //  for click event on back button to close this activity
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}