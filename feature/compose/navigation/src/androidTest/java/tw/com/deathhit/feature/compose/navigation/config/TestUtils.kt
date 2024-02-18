package tw.com.deathhit.feature.compose.navigation.config

import java.util.UUID

fun generatePlantId() = getRandomStr()

private fun getRandomStr() = UUID.randomUUID().toString()