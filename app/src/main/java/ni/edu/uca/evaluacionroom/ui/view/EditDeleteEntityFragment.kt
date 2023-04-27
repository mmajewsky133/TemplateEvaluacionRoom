package ni.edu.uca.evaluacionroom.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ni.edu.uca.evaluacionroom.R
import ni.edu.uca.evaluacionroom.data.database.entities.EntityClass
import ni.edu.uca.evaluacionroom.databinding.FragmentEditDeleteEntityBinding
import ni.edu.uca.evaluacionroom.ui.viewmodel.MainViewModel

class EditDeleteEntityFragment : BottomSheetDialogFragment() {

    lateinit var entity: EntityClass

    //ViewModel
    private val viewModel: MainViewModel by activityViewModels {
        MainViewModel.factory
    }

    //el view binding
    private var _binding: FragmentEditDeleteEntityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditDeleteEntityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = this.tag?.toInt()

        if (id != null) {
            viewModel.getEntity(id).observe(this.viewLifecycleOwner) { selectedEntity ->
                selectedEntity?.let {
                    entity = it
                    bind(entity)
                }
            }
        }
    }

    private fun bind(entityClass: EntityClass) {
        binding.apply {
            tvEntity.text = entityClass.column1
            etParam1.setText(entityClass.column1)
            etParam2.setText(entityClass.column2)
            etParam3.setText(entityClass.column3.toString())
            etParam4.setText(entityClass.column4)

            btnEditar.setOnClickListener { editarEntity() }
            btnEliminar.setOnClickListener {
                mostrarDialogConfirmacion()
            }
        }
    }

    private fun editarEntity() {
        val id = this.tag?.toInt()

        changeLayout()

        binding.btnEditar.setOnClickListener {
            if (id != null) {
                viewModel.editarEntity(id,
                    binding.etParam1.text.toString(),
                    binding.etParam2.text.toString(),
                    binding.etParam3.text.toString().toInt(),
                    binding.etParam4.text.toString()
                )
            }
            dismiss()
        }
    }

    private fun eliminarEntity(){
        viewModel.eliminarEntity(entity)
        dismiss()
    }

    private fun changeLayout(){
        binding.apply {
            tvEntity.isGone = true

            btnEliminar.isEnabled = false
            btnEditar.text = "Guardar"
            etParam1.requestFocus()
        }
    }

    private fun mostrarDialogConfirmacion() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Desea eliminar este entity?")
            .setCancelable(false)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ ->
                eliminarEntity()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}