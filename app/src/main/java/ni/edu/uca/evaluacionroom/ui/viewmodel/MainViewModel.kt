package ni.edu.uca.evaluacionroom.ui.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import ni.edu.uca.evaluacionroom.EvaluacionRoomApp
import ni.edu.uca.evaluacionroom.data.database.dao.EntityDao
import ni.edu.uca.evaluacionroom.data.database.entities.EntityClass

class MainViewModel(private val entityDao: EntityDao): ViewModel() {

    val allEntities: LiveData<List<EntityClass>> = entityDao.getEntities().asLiveData()

    fun getEntity(id: Int): LiveData<EntityClass> {
        return entityDao.getEntity(id).asLiveData()
    }

    fun agregarEntity(param1: String, param2: String, param3: Int, param4: String) {
        val nuevoEntity = EntityClass(
            0, param1, param2, param3, param4
        )
        insertEntity(nuevoEntity)
    }

    fun editarEntity(id: Int, param1: String, param2: String, param3: Int, param4: String) {
        val entity = EntityClass(
            id, param1, param2, param3, param4
        )
        updateEntity(entity)
    }

    fun eliminarEntity(entityClass: EntityClass) {
        deleteEntity(entityClass)
    }

    private fun updateEntity(entityClass: EntityClass) {
        viewModelScope.launch {
            entityDao.update(entityClass)
        }
    }

    private fun insertEntity(entityClass: EntityClass) {
        viewModelScope.launch {
            entityDao.insert(entityClass)
        }
    }

    private fun deleteEntity(entityClass: EntityClass) {
        viewModelScope.launch {
            entityDao.delete(entityClass)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            addInitializer(MainViewModel::class) {
                MainViewModel(EvaluacionRoomApp.database.EntityDao())
            }
            build()
        }
    }
}