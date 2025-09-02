package tw.com.deathhit.data.sunflower_clone.garden_planting

import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase

class GardenPlantingRepositoryTestImp : GardenPlantingRepositoryTest() {
    override fun createAppDatabase(): SunflowerCloneDatabase = Config.createAppDatabase()
}