package com.example.lesson14roomexercices

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson14roomexercices.databinding.FragmentDatabaseBinding

class DatabaseFragment : Fragment() {
    private var _binding: FragmentDatabaseBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val noteDao by lazy { //from abstract class
        requireContext().appDatabase.noteDao
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDatabaseBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateData()
        binding.buttonAdd.setOnClickListener {
            val text = binding.editText.text.toString()
            noteDao.insert(Note(text = text))
            updateData()
        }
        binding.buttonDelete.setOnClickListener {
            deleteRandomNote()
            updateData()
        }
    }

    private fun deleteRandomNote() {
        val note = noteDao.getAll().random()
        noteDao.delete(note)
        updateData()
    }

    private fun updateData() {
        binding.textView.text = noteDao.getAll().joinToString("\n") {
            "Note[${it.id}]: ${it.text}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}