package tw.com.deathhit.feature.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import tw.com.deathhit.feature.garden_planting_list.GardenPlantingListFragment
import tw.com.deathhit.feature.plant_list.PlantListFragment
import java.lang.RuntimeException

class NavigationTabAdapter(
    fragmentManager: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment =
        when (position) {
            POS_MY_GARDEN -> GardenPlantingListFragment.create()
            POS_PLANT_LIST -> PlantListFragment.create()
            else -> throw RuntimeException("Unexpected position of $position!")
        }

    override fun getItemCount(): Int = 2
}