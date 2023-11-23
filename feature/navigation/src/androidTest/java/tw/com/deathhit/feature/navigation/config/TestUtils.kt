package tw.com.deathhit.feature.navigation.config

import java.util.UUID

fun generatePlantId() = getRandomStr()

private fun getRandomStr() = UUID.randomUUID().toString()