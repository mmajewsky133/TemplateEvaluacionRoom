package ni.edu.uca.evaluacionroom

import android.app.Application
import ni.edu.uca.evaluacionroom.data.database.EntitiesRoomDatabase

class EvaluacionRoomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        lateinit var application: Application
            private set
        //Se usa lazy para que solo sea init cuando se llame
        val database: EntitiesRoomDatabase by lazy { EntitiesRoomDatabase.getDatabase(application) }
    }
}