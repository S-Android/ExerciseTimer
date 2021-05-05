package com.ht.exceciseinternal.base

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.BaseWV
import com.ht.exceciseinternal.widgets.BaseWVH
import com.ht.exceciseinternal.widgets.add_excecise.AddExerciseWC
import com.ht.exceciseinternal.widgets.add_excecise.AddExerciseWV
import com.ht.exceciseinternal.widgets.circuit.CircuitWC
import com.ht.exceciseinternal.widgets.circuit.CircuitWV
import com.ht.exceciseinternal.widgets.exercise.ExerciseWC
import com.ht.exceciseinternal.widgets.exercise.ExerciseWV


class BaseAdapter:  ListAdapter<BaseWC, BaseWVH<BaseWV<BaseWC>, BaseWC>>(DiffCallback()) {
    override fun getItemViewType(position: Int): Int {
        return getItem(position).type()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseWVH<BaseWV<BaseWC>, BaseWC>{
        val wv = getWV(viewType, parent.context) as BaseWV<BaseWC>
        return BaseWVH(wv)
    }

    override fun onBindViewHolder(holder: BaseWVH<BaseWV<BaseWC>, BaseWC>, position: Int) {
        val item = getItem(position)
        val baseWC = item as BaseWC
        holder.updateView(baseWC)
    }

    private fun getWV(viewType: Int, context: Context): BaseWV<*>? = when (viewType) {
        CircuitWC.type -> CircuitWV(context)
        ExerciseWC.type -> ExerciseWV(context)
        AddExerciseWC.type -> AddExerciseWV(context)
        else -> null
    }

    class DiffCallback : DiffUtil.ItemCallback<BaseWC>() {
        override fun areContentsTheSame(oldItem: BaseWC, newItem: BaseWC) = oldItem == newItem
        override fun areItemsTheSame(oldItem: BaseWC, newItem: BaseWC) = oldItem.isSame(newItem)
    }
}