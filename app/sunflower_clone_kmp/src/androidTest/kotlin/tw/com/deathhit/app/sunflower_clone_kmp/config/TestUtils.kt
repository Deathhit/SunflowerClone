package tw.com.deathhit.app.sunflower_clone_kmp.config

import java.util.UUID

fun generatePlantId() = getRandomStr()
fun generatePlantName() = getRandomStr()

private fun getRandomStr() = UUID.randomUUID().toString()