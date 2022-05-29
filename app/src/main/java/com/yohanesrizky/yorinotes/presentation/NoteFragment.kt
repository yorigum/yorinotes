package com.yohanesrizky.yorinotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.yohanesrizky.core.data.Note
import com.yohanesrizky.yorinotes.databinding.FragmentNoteBinding
import com.yohanesrizky.yorinotes.framework.NoteViewModel



class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L)

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
    }


}