package me.mestima.zomboidmodscopier

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("main.fxml"))
        val scene = Scene(fxmlLoader.load(), 483.0, 281.0)
        stage.title = "Zomboid mods copier v1.0"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}