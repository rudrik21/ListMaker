package com.rudrik.listmaker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rudrik.listmaker.databinding.ListSelectionViewHolderBinding


class AdptListSelection(
    private val tasks: MutableList<TaskList>
) : RecyclerView.Adapter<VHListSelection>() {   // for Implementing 'RecyclerView.Adapter'

    private lateinit var bind: ListSelectionViewHolderBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHListSelection {

        //  for inflating view
        bind = ListSelectionViewHolderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        //  for initializing Custom ViewHolder
        return VHListSelection(bind)
    }

    override fun onBindViewHolder(holder: VHListSelection, position: Int) {
        //  on binding data with View
        holder.bind(Pair(position, tasks[position]))
    }

    //  for size of list and views
    override fun getItemCount(): Int = tasks.size

    //  for adding item to list
    fun addList(task: TaskList) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)  //  for updating lastly added item on view
    }


}

//  for custom ViewHolder class, implementing 'RecyclerView.ViewHolder'
class VHListSelection(var viewBind: ListSelectionViewHolderBinding) :
    RecyclerView.ViewHolder(viewBind.root) {

    //  on setting data to views
    fun bind(dataSet: Pair<Int, TaskList>) {
        val strNum = "# ${(dataSet.first) + 1}"
        viewBind.tvItemNumber.text = strNum
        viewBind.tvItemName.text = dataSet.second.name

        viewBind.root.setOnClickListener(onItemClicked(dataSet))
    }

    //  on view click listener for specific data
    private fun onItemClicked(dataSet: Pair<Int, TaskList>): View.OnClickListener {
        return View.OnClickListener {
            val msg =
                ("CLICKED ${(dataSet.first) + 1} ${dataSet.second.name} -> " + dataSet.second.tasksList.toString())
//            msg.snack(it)
            showTaskDetails(viewBind.root.context, dataSet.second)
        }
    }

    private fun showTaskDetails(context: Context, task: TaskList) {
        val i = Intent(context, TaskDetailsActivity::class.java)
        i.putExtra(MainActivity.INTENT_TASK_DETAILS, task)
        context.startActivity(i)
    }
}
