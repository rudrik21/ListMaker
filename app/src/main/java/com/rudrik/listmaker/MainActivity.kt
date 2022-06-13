package com.rudrik.listmaker

import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rudrik.listmaker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var adpt: AdptListSelection
    private lateinit var listDataManager: ListDataManager
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        init()
    }

    //  for initialization
    private fun init() {
        listDataManager = ListDataManager(this)
        val list = listDataManager.readList()
        adpt = AdptListSelection(list)
        bind.contentMain.reyclerView.adapter = adpt
        bind.fabCreateList.setOnClickListener(this)
    }

    //  for creating alert dialog
    private fun createListDialog() {
        //1
        val strTitle = getString(R.string.dialog_create_list_title)
        val bCreate = getString(R.string.dialog_create_list_positive)

        //2 for making builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle(strTitle)

        //3 for creating views
        val linearView = LinearLayout(this)
        linearView.orientation = LinearLayout.VERTICAL
        val etName = EditText(this)
        val etItems = EditText(this)
        etName.hint = "name..."
        etName.inputType = InputType.TYPE_CLASS_TEXT
        etItems.hint = "Items separated by , "
        etItems.inputType = InputType.TYPE_CLASS_TEXT

        //4 for stacking views
        linearView.addView(etName)
        linearView.addView(etItems)
        builder.setView(linearView)

        //5  on positive button click action
        builder.setPositiveButton(bCreate) { dialog, _ ->
            val task = TaskList(etName.text.toString())
            val items = etItems.text.toString().split(",")
            task.tasksList = items
            listDataManager.saveList(task)
            adpt.addList(task)
            dialog.dismiss()
        }
        builder.create().show()
    }

    //  on floating action button click
    override fun onClick(v: View?) {
        when (v) {
            bind.fabCreateList -> createListDialog()
        }
    }


    companion object {
        const val INTENT_TASK_DETAILS = "task_detail"
    }
}
