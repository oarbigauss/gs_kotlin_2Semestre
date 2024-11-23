import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.gs_kotlin.data.ItemDao
import br.com.fiap.gs_kotlin.model.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}