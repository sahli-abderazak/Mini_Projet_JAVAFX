module Projet {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.base,javafx.graphics, javafx.fxml;
}
