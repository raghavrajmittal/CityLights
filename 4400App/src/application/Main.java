package application;
	
import Database.DBModel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.scene.layout.BorderPane;

import java.sql.*;


public class Main extends Application {
	/**The stage for the javafx stuff*/
	private static Stage stage;
	
	/**Screen bounds */
//	private static final Rectangle2D bounds =
//			Screen.getPrimary().getVisualBounds();
	
	/**On start make the screen the login screen*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			stage = primaryStage;
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.
					getResource("../view/Login.fxml"));
			
			Parent root = loader.load();
			
			primaryStage.setScene(new Scene(root,
					600,
					400));
			
			primaryStage.show();
			Connection con = DBModel.getInstance().getConnection();
			String query = "SELECT * from user";
			PreparedStatement stmnt = con.prepareStatement(query);
			ResultSet resultSet = stmnt.executeQuery();
			while (resultSet.next()) {
				String email = resultSet.getString("email");
				boolean isManager = resultSet.getBoolean("isManager");
				Date dateJoined = resultSet.getDate("DateJoined");
				System.out.println(String.format("%s isManager=%b, Joined on %s", email, isManager, dateJoined.toString()));

			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
