package com.scainitiative.kotlinscainitiativeproject.view.addNote

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import com.scainitiative.kotlinscainitiativeproject.R
import com.scainitiative.kotlinscainitiativeproject.model.Note
import com.scainitiative.kotlinscainitiativeproject.db.Notedb
import com.scainitiative.kotlinscainitiativeproject.view.BaseFragment
import com.scainitiative.kotlinscainitiativeproject.view.toast
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {


    private  var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?. let {
            note = AddNoteFragmentArgs.fromBundle(it).Note
            text_title.setText(note?.title)
            text_note.setText(note?.note)
        }

        btn_save.setOnClickListener { view ->
            val noteTitle = text_title.text.toString().trim()
            val noteBody = text_note.text.toString().trim()

            if (noteTitle.isEmpty()) {
                text_title.error = "title required"
                text_title.requestFocus()
                return@setOnClickListener
            }
            if (noteBody.isEmpty()) {
                text_note.error = "note required"
                text_note.requestFocus()
                return@setOnClickListener
            }

            //Used corotines instead of AsynTask

            launch {

                context?.let {
                    val mNote = Note(noteTitle, noteBody)

                    if (note == null) {
                        Notedb(it).getNoteDao().addNote(note!!)
                        it.toast("Note saved")
                    }else {
                        mNote.id = note!!.id
                        Notedb(it).getNoteDao().updateNotes(note!!)
                        it.toast("Note edited")
                    }

                    val action = AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }

        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are yuo sure")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") { _, _ ->

               launch {
                   Notedb(context).getNoteDao().deleteNote(note!!)
                   val action = AddNoteFragmentDirections.actionSaveNote()
                   Navigation.findNavController(requireView()).navigate(action)
               }
            }
            setNegativeButton("No") {
                _,_ ->
            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when(item.itemId) {
            R.id.delete_note -> if (note != null) deleteNote()else context?.toast("Cannot delete this")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

}
