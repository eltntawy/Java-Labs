package main.java.javafx.lab4;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class FXMLFileSystemTreeController implements Initializable {

    @FXML
    TreeView<File> treeViewParent;

    @FXML
    ListView<File> listViewChild;

    public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

	listViewChild.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
	    public ListCell<File> call(ListView<File> param) {
		// TODO Auto-generated method stub
		return new ListCellRendrer();
	    }
	});

	treeViewParent.setCellFactory(new Callback<TreeView<File>, TreeCell<File>>() {

	    public TreeCell<File> call(TreeView<File> param) {
		// TODO Auto-generated method stub
		return new TreeCellRendrer();
	    }
	});
	File rootFile = new File("/home/eltntawy");
	rootFile.listFiles();
	TreeItem<File> itemRoot = new TreeItem<File>(rootFile);

	treeViewParent.setRoot(createNode(rootFile));

	treeViewParent.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<File>>() {

	    public void changed(ObservableValue<? extends TreeItem<File>> observable, TreeItem<File> oldValue, TreeItem<File> newValue) {
		System.out.println("Old value : " + oldValue + " - " + "new value : " + newValue);

		ObservableList<File> listItems = FXCollections.observableArrayList();
		for (TreeItem<File> treeFileItem : newValue.getChildren()) {
		    listItems.add(treeFileItem.getValue());
		}
		listViewChild.setItems(listItems);
	    }
	});
    }

    public TreeItem<File> createNode(final File file) {

	return new TreeItem<File>(file) {

	    private boolean isLeaf;
	    private boolean isFirstTimeChild = true;
	    private boolean isFirstTimeLeaf = true;

	    @Override
	    public ObservableList<TreeItem<File>> getChildren() {
		// TODO Auto-generated method stub

		if (isFirstTimeChild) {
		    isFirstTimeChild = false;
		    super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	    }

	    @Override
	    public boolean isLeaf() {
		// TODO Auto-generated method stub
		if (isFirstTimeLeaf) {
		    isFirstTimeLeaf = false;
		    File file = (File) getValue();
		    if (file != null)
			isLeaf = file.isFile();
		}
		return isLeaf;
	    }

	};
    }

    public ObservableList<TreeItem<File>> buildChildren(TreeItem<File> treeItems) {

	File f = treeItems.getValue();

	if (f != null && f.isDirectory()) {
	    File files[] = f.listFiles();
	    if (files != null) {
		ObservableList<TreeItem<File>> fileChildern = FXCollections.observableArrayList();
		for (File file : files) {
		    fileChildern.add(createNode(file));
		}
		return fileChildern;
	    }
	}
	return FXCollections.emptyObservableList();
    }

    class ListCellRendrer extends ListCell<File> {

	@Override
	protected void updateItem(File item, boolean empty) {
	    // TODO Auto-generated method stub
	    super.updateItem(item, empty);
	    Label l = null;
	    Image imageFile = new Image(getClass().getResourceAsStream("file.png"));
	    Image imageFolder = new Image(getClass().getResourceAsStream("FilesFolder.png"));

	    if (item != null)
		if (item.isDirectory()) {
		    l = new Label(item.getName(), new ImageView(imageFolder));
		} else {
		    l = new Label(item.getName(), new ImageView(imageFolder));
		}

	    setGraphic(l);
	}
    }

    class TreeCellRendrer extends TreeCell<File> {

	@Override
	protected void updateItem(File item, boolean empty) {
	    // TODO Auto-generated method stub
	    super.updateItem(item, empty);
	    Label l = null;
	    Image imageFile = new Image(getClass().getResourceAsStream("file.png"));
	    Image imageFolder = new Image(getClass().getResourceAsStream("FilesFolder.png"));

	    if (item != null)
		if (item.isDirectory()) {
		    l = new Label(item.getName(), new ImageView(imageFolder));
		} else {
		    l = new Label(item.getName(), new ImageView(imageFile));
		}

	    setGraphic(l);
	}
    }

    public void treeViewAction(ActionEvent e) {

    }
}
