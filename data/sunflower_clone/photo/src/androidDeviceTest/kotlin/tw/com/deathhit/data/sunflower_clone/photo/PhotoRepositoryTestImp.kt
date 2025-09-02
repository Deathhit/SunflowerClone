package tw.com.deathhit.data.sunflower_clone.photo

import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase

class PhotoRepositoryTestImp : PhotoRepositoryTest() {
    override fun createAppDatabase(): SunflowerCloneDatabase = Config.createAppDatabase()
}