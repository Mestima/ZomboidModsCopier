package me.mestima.zomboidmodscopier

import javafx.fxml.FXML
import javafx.scene.control.ProgressBar
import javafx.scene.control.TextField
import java.io.File
import java.util.concurrent.Semaphore

class CopierController {

    //private val secondPerMod: Long = 2

    @FXML
    private lateinit var pathName: TextField

    @FXML
    private lateinit var userName: TextField

    @FXML
    private lateinit var progress: ProgressBar

    @FXML
    private fun onCopy() {
        val path = pathName.text
        val dest = File("C:\\Users\\${userName.text}\\Zomboid")

        val queue = Semaphore(2)
        val folder = File(path)
        //val modsCount: Double = folder.list().size.toDouble()
        //val progressPerMod: Double = 100.0 / modsCount

        //var i: Long = 0
        val lst = folder.list()
        lst.forEach {
            Thread {
                queue.acquireUninterruptibly()

                val subfolder = File("$path\\$it")
                subfolder.copyRecursively(dest, true)

                if (it == lst.last()) {
                    progress.progress = 100.0
                }

                queue.release()
            }.start()
        }
    }

    fun initialize() {
        progress.progress = 0.0
    }

}