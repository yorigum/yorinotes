package com.yohanesrizky.yorinotes.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.yohanesrizky.core.data.Note
import com.yohanesrizky.yorinotes.R
import com.yohanesrizky.yorinotes.databinding.FragmentNoteBinding
import com.yohanesrizky.yorinotes.framework.NoteViewModel



class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoteViewModel
    private var noteId = 0L
    private var currentNote = Note("","",0L,0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        arguments?.let{
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if(noteId != 0L){
            viewModel.getNote(noteId)
        }

        binding.checkButton.setOnClickListener {
            if(binding.titleView.text.toString() != "" || binding.contentView.text.toString()!=""){
                val time = System.currentTimeMillis()
                currentNote.title = binding.titleView.text.toString()
                currentNote.content = binding.contentView.text.toString()
                currentNote.updateTime = time
                if(currentNote.id == 0L){
                    currentNote.creationTime =time
                }
                viewModel.saveNote(currentNote)
            }else{
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.saved.observe(viewLifecycleOwner,Observer {
            if(it){
                Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
                Navigation.findNavController(binding.titleView).popBackStack()
            }else{
                Toast.makeText(context,"Something when wrong, please try again",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner,Observer  { note ->
           note?.let{
               currentNote = it
               binding.titleView.setText(it.title,TextView.BufferType.EDITABLE)
               binding.contentView.setText(it.content,TextView.BufferType.EDITABLE)

           }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote->{
                if(context != null && noteId != 0L){
                    AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure to delete this note?")
                        .setPositiveButton("Yes"){
                                _, _ ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Cancel"){
                                _, _ -> }

                        .show()

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}