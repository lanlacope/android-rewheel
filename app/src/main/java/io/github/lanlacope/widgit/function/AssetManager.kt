package io.github.lanlacope.widgit.function

import android.content.res.AssetManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Suppress("unused")
suspend fun AssetManager.copyFileEternalStorage(path: String, outputRoot: String): Unit
        = withContext(Dispatchers.IO) {

    val outputFile = File(outputRoot, path)
    outputFile.createNewFile()

    this@copyFileEternalStorage.open(path).use { input ->
        FileOutputStream(outputFile).use { output ->
            input.copyTo(output)
        }
    }
}

@Suppress("unused")
suspend fun AssetManager.copyFolderEternalStorage(path: String, outputRoot: String) : Unit
        = withContext(Dispatchers.IO){

    val assets = this@copyFolderEternalStorage.list(path) ?: return@withContext

    val outputFolder = File(outputRoot, path)

    if (!outputFolder.exists()) {
        outputFolder.mkdirs()
    }

    assets.forEach { asset ->
        val branchPath = "$path/$asset"
        if (this@copyFolderEternalStorage.list(branchPath).isNullOrEmpty()) {
            this@copyFolderEternalStorage.copyFileEternalStorage(branchPath, outputRoot)
        } else {
            this@copyFolderEternalStorage.copyFolderEternalStorage(branchPath, outputRoot)
        }
    }
}