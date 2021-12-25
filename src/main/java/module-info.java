module me.mestima.zomboidmodscopier {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens me.mestima.zomboidmodscopier to javafx.fxml;
    exports me.mestima.zomboidmodscopier;
}