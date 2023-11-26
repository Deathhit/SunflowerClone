package tw.com.deathhit.sunflower_clone.config

import java.util.UUID

fun generatePlantId() = getRandomStr()
fun generatePlantName() = getRandomStr()

private fun getRandomStr() = UUID.randomUUID().toString()