package com.yohanesrizky.yorinotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yohanesrizky.yorinotes.databinding.FragmentListBinding
import com.yohanesrizky.yorinotes.framework.NoteListViewModel
import com.yohanesrizky.yorinotes.framework.NoteViewModel


class ListFragment : Fragment(),NoteListAction {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteListViewModel
    private val noteListAdapter = NoteListAdapter(arrayListOf(),this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.noteListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }
        viewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)
        observeViewModel()
        binding.floatingActionButton.setOnClickListener { goToNoteDetails() }
    }

    private fun observeViewModel(){
        viewModel.notes.observe(viewLifecycleOwner,Observer{ noteList ->
            binding.progressBarListView.visibility = View.GONE
            binding.noteListView.visibility = View.VISIBLE
            noteListAdapter.updateNotes(noteList)
        })
    }

    private fun goToNoteDetails(id:Long = 0L){
        val action = ListFragmentDirections.actionToGoNote(id)
        Navigation.findNavController(binding.noteListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}