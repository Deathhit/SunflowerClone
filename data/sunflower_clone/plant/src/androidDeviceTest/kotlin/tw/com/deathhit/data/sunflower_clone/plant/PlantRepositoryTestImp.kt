package tw.com.deathhit.data.sunflower_clone.plant

import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase

class PlantRepositoryTestImp : PlantRepositoryTest() {
    override fun createAppDatabase(): SunflowerCloneDatabase = Config.createAppDatabase()
}