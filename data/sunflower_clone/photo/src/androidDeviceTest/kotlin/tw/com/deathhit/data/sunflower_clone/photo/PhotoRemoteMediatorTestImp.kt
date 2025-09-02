package tw.com.deathhit.data.sunflower_clone.photo

import tw.com.deathhit.core.sunflower_clone.app_database.SunflowerCloneDatabase

class PhotoRemoteMediatorTestImp : PhotoRemoteMediatorTest() {
    override fun createAppDatabase(): SunflowerCloneDatabase = Config.createAppDatabase()
}