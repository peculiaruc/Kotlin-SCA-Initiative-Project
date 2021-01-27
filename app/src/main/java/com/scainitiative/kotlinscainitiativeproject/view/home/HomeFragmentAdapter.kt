package com.scainitiative.kotlinscainitiativeproject.view.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.scainitiative.kotlinscainitiativeproject.R
import com.scainitiative.kotlinscainitiativeproject.model.Note
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.list_item_notes.view.*

class HomeFragmentAdapter( private val notes: List<Note>): RecyclerView.Adapter<HomeFragmentAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_notes, parent, false)
        )
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.view.view_first_note.text = notes[position].title
        holder.view.note_description.text = notes[position].note

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionAddNote()
       //     action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class NoteViewHolder(val view: View): RecyclerView.ViewHolder(view)
}