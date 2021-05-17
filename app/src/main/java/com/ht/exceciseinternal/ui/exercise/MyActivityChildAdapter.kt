package com.ht.exceciseinternal.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.databinding.MyActivityDetailItemBinding


class MyActivityChildAdapter :
        ListAdapter<Exercise, MyActivityChildAdapter.MyActivityChildAdapterViewHolder>(
                DiffCallback()
        ) {

    class DiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise) = oldItem == newItem
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise) = oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivityChildAdapterViewHolder {
        val binding = MyActivityDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyActivityChildAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyActivityChildAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyActivityChildAdapterViewHolder(private val binding: MyActivityDetailItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.apply {
                myActivityExerciseTiet.text = exercise.name
                myActivityExerciseDurationMinTiet.text = exercise.exerciseDuration.min.toString()
                myActivityExerciseDurationSecTiet.text = exercise.exerciseDuration.sec.toString()
            }
        }
    }
}