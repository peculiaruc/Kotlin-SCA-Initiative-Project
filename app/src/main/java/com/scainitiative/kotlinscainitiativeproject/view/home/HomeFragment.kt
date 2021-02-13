package com.scainitiative.kotlinscainitiativeproject.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.scainitiative.kotlinscainitiativeproject.R
import com.scainitiative.kotlinscainitiativeproject.db.Notedb
import com.scainitiative.kotlinscainitiativeproject.model.Note
import com.scainitiative.kotlinscainitiativeproject.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_item_notes.*
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_note.setHasFixedSize(true)
        recycler_view_note.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val notes = Notedb(it).getNoteDao().getAllNotes()
                recycler_view_note.adapter = HomeFragmentAdapter(notes)
            }
        }

        btn_add.setOnClickListener {
          val action =
              HomeFragmentDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }
    }
}

fun HomeFragmentDirections.Companion.actionAddNote(): NavDirections {
    TODO("Not yet implemented")
}
