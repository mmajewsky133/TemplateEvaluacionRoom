package ni.edu.uca.evaluacionroom.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ni.edu.uca.evaluacionroom.R
import ni.edu.uca.evaluacionroom.databinding.FragmentEntitiesBinding
import ni.edu.uca.evaluacionroom.ui.view.adapter.EntityListAdapter
import ni.edu.uca.evaluacionroom.ui.viewmodel.MainViewModel

class EntitiesFragment : Fragment() {

    //ViewModel
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.factory
    }

    //el view binding
    private var _binding: FragmentEntitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEntitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EntityListAdapter {
            EditDeleteEntityFragment().show(childFragmentManager, it.id.toString())
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        binding.fabAgregar.setOnClickListener {
            it.findNavController().navigate(R.id.action_entitiesFragment_to_addEntityFragment)
        }

        viewModel.allEntities.observe(this.viewLifecycleOwner) { entities ->
            entities.let {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}