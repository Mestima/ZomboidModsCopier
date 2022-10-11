package me.mestima.zomboidmodscopier

import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ProgressBar
import javafx.scene.control.TextField
import java.io.File
import java.util.concurrent.Semaphore

class CopierController {
    @FXML
    private lateinit var copyBtn: Button

    @FXML
    private lateinit var userBtn: Button

    @FXML
    private lateinit var pathName: TextField

    @FXML
    private lateinit var userName: TextField

    @FXML
    private lateinit var progress: ProgressBar

    @FXML
    private fun onChangeUsername() {
        userBtn.isDisable = true
        userName.isDisable = false
    }

    @FXML
    private fun onCopy() {
        var isCopying: Boolean = true
        copyBtn.isDisable = true
        userBtn.isDisable = true
        pathName.isDisable = true
        userName.isDisable = true

        val startAlert: Alert = Alert(Alert.AlertType.INFORMATION)
        startAlert.title = "Копирование"
        startAlert.headerText = null
        startAlert.contentText = "Копирование началось! Не закрывайте программу до окончания копирования. Это может привести к невообразимым последствиям..."
        startAlert.show()


        val path = pathName.text
        val dest = File("C:\\Users\\${userName.text}\\Zomboid")

        val queue = Semaphore(2)
        val folder = File(path)
        val modsCount: Double = folder.list().size.toDouble()
        val progressPerMod: Double = 1.0 / modsCount

        val lst = folder.list()
        lst.forEach {
            Thread {
                queue.acquireUninterruptibly()

                val subfolder = File("$path\\$it")
                subfolder.copyRecursively(dest, true)
                if (isCopying) {
                    progress.progress += progressPerMod
                    println(progress.progress)
                }
                queue.release()

                if (progress.progress >= 1.0) {
                    Platform.runLater {
                        if (isCopying) {
                            isCopying = false
                            copyBtn.isDisable = false
                            userBtn.isDisable = false
                            pathName.isDisable = false
                            progress.progress = 0.0
                            val doneAlert: Alert = Alert(Alert.AlertType.INFORMATION)
                            doneAlert.title = "Копирование"
                            doneAlert.headerText = null
                            doneAlert.contentText = "Копирование завершено! Программу можно закрыть."
                            doneAlert.show()
                        }
                    }
                }
            }.start()
        }
    }

    fun initialize() {
        progress.progress = 0.0
        userName.text = System.getProperty("user.name")
    }

}