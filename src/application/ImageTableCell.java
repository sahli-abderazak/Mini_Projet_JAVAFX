package application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

//public class ImageTableCell<S, T> extends TableCell<S, T> {
//    private final ImageView imageView = new ImageView();
//    
//    public ImageTableCell() {
//        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//    }
//
//    @Override
//    protected void updateItem(T item, boolean empty) {
//        super.updateItem(item, empty);
//        if (empty || item == null) {
//            setGraphic(null);
//        } else {
//            Image image = new Image(item.toString());
//            imageView.setImage(image);
//            setGraphic(imageView);
//        }
//    }
//}
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageTableCell extends TableCell<Voiture, String> {
    private final ImageView imageView = new ImageView();

    @Override
    protected void updateItem(String imageUrl, boolean empty) {
        super.updateItem(imageUrl, empty);

        if (empty || imageUrl == null || imageUrl.isEmpty()) {
            setGraphic(null);
        } else {
            Image image = new Image(imageUrl);
            imageView.setImage(image);
            imageView.setFitWidth(100); // Définissez la largeur souhaitée de l'image
            imageView.setFitHeight(100); // Définissez la hauteur souhaitée de l'image
            setGraphic(imageView);
        }
    }
}


