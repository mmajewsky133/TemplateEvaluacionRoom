package ni.edu.uca.evaluacionroom.ui.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import ni.edu.uca.evaluacionroom.R
import ni.edu.uca.evaluacionroom.databinding.FragmentAddEntityBinding
import ni.edu.uca.evaluacionroom.ui.viewmodel.MainViewModel

class AddEntityFragment : Fragment() {

    //ViewModel
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.factory
    }

    //el view binding
    private var _binding: FragmentAddEntityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddEntityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnAgregar.setOnClickListener {
                agregarNuevoEntity()

                it.findNavController().navigate(R.id.action_addEntityFragment_to_entitiesFragment)
            }
        }
    }

    //Funcion para mandar a agregar una palabra
    private fun agregarNuevoEntity() {
        viewModel.agregarEntity(
            binding.etParam1.text.toString(),
            binding.etParam2.text.toString(),
            binding.etParam3.text.toString().toInt(),
            binding.etParam4.text.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}