package me.mestima.zomboidmodscopier

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class ZomboidCopier : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(ZomboidCopier::class.java.getResource("main.fxml"))
        val scene = Scene(fxmlLoader.load(), 483.0, 281.0)
        stage.title = "Zomboid mods copier v2.0"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(ZomboidCopier::class.java)
}