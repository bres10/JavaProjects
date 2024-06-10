package app.notas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class MainController {
    @FXML
    private ListView<String> notesListView;

    @FXML
    private void addNote() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Nota");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese la nota:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(note -> notesListView.getItems().add(note));
    }

    @FXML
    private void editNote() {
        int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            TextInputDialog dialog = new TextInputDialog(notesListView.getSelectionModel().getSelectedItem());
            dialog.setTitle("Editar Nota");
            dialog.setHeaderText(null);
            dialog.setContentText("Ingrese la nota:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(note -> notesListView.getItems().set(selectedIndex, note));
        } else {
            showAlert("Editar Nota", "Seleccione una nota para editar.");
        }
    }

    @FXML
    private void deleteNote() {
        int selectedIndex = notesListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            notesListView.getItems().remove(selectedIndex);
        } else {
            showAlert("Eliminar Nota", "Seleccione una nota para eliminar.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
