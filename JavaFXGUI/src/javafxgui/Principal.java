/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgui;

import com.javafx.experiments.importers.Importer3D;
import com.javafx.experiments.importers.obj.ObjOrPolyObjImporter;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author SoporteTI
 */
public class Principal extends Application {

    private StackPane parentcontainer;

    private int diapositiva_actual = 0;

    private final String[] modelos3D = {
    "lamp.obj",
    "table.obj",
    "chair.obj",
    "shelf.obj",
    "pc.obj"
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PRESENTACION HOMECENTER");
        primaryStage.setResizable(false);
        cargaEscenario(primaryStage);
    }

    public static void main(String... args) {
        launch(args);
    }

    private void cargaEscenario(Stage escenario) {
        Node modelo3D;
        try {
            modelo3D = Importer3D.load("file:/D:/Indra/repo/app-externaloperators/app/JavaFXGUI/src/resources/modelo3D/" + modelos3D[diapositiva_actual], new ObjOrPolyObjImporter());
        } catch (Exception e) {
            System.out.print("ERROR al cargar el modelo obj de esta diapositiva");
            e.printStackTrace();
            return;
        }

        if (diapositiva_actual < modelos3D.length - 1) {
            Button btn = new Button();
            btn.setText("Siguiente Disapositiva");
            btn.setOnAction(action -> {
                diapositiva_actual++;
                cargaEscenario(escenario);
            });

            btn.setTranslateX(400);
            btn.setTranslateY(300);
            btn.setFont(new Font(STYLESHEET_MODENA, 20));

            parentcontainer = new StackPane(modelo3D, btn);
            parentcontainer.setPickOnBounds(false);
        } else {
            parentcontainer = new StackPane(modelo3D);
        }

        Scene escena = new Scene(parentcontainer, 1360, 850, true, SceneAntialiasing.BALANCED);
        
        PerspectiveCamera camera = new PerspectiveCamera();
        modelo3D.setTranslateZ(-300);
        modelo3D.setTranslateX(200);

        switch (diapositiva_actual) {
            case 0:
                modelo3D.setScaleX(3);
                modelo3D.setScaleY(3);
                modelo3D.setScaleZ(3);
                modelo3D.getTransforms().add(new Rotate(-180, new Point3D(0,0,50)));
                TranslateTransition tr = new TranslateTransition(Duration.seconds(4), modelo3D);
                tr.setFromY(400.0);
                tr.setToY(200.0);
                tr.setFromX(200.0);
                tr.setToX(200.0);
                tr.play();
                break;
            case 1:
                modelo3D.setScaleX(1);
                modelo3D.setScaleY(1);
                modelo3D.setScaleZ(1);
                modelo3D.setTranslateX(300);
                modelo3D.setTranslateY(-40);
                modelo3D.getTransforms().add(new Rotate(180, new Point3D(0, 0, 1)));
                RotateTransition rt = new RotateTransition(Duration.seconds(5), modelo3D);
                rt.setCycleCount(1);
                rt.setByAngle(360);
                rt.setAxis(Rotate.X_AXIS);
                rt.play();
                break;
            case 2:
                modelo3D.getTransforms().add(new Rotate(180, new Point3D(0, 0, 1)));
                modelo3D.getTransforms().add(new Rotate(30, new Point3D(0, 1, 0)));
                modelo3D.setTranslateY(300);
                ScaleTransition st = new ScaleTransition(Duration.seconds(4), modelo3D);
                st.setCycleCount(1);
                st.setByX(2.5);
                st.setByY(2.5);
                st.setByZ(2.5);
                st.play();
                break;
            case 3:
                modelo3D.setScaleX(2);
                modelo3D.setScaleY(2);
                modelo3D.setScaleZ(2);
                modelo3D.setTranslateZ(-500);
                modelo3D.setTranslateX(300);
                modelo3D.getTransforms().add(new Rotate(180, new Point3D(0, 0, 1)));
                TranslateTransition tr2 = new TranslateTransition(Duration.seconds(5), modelo3D);
                tr2.setFromY(-500.0);
                tr2.setToY(300.0);
                tr2.setFromX(200.0);
                tr2.setToX(200.0);
                RotateTransition rt2 = new RotateTransition(Duration.seconds(5), modelo3D);
                rt2.setCycleCount(1);
                rt2.setByAngle(360);
                rt2.setAxis(Rotate.X_AXIS);
                rt2.play();
                tr2.play();
                break;
            case 4:
                modelo3D.getTransforms().add(new Rotate(90, new Point3D(1, 0, 0)));
                modelo3D.setScaleX(6);
                modelo3D.setScaleY(6);
                modelo3D.setScaleZ(6);
                RotateTransition rt3 = new RotateTransition(Duration.seconds(5), modelo3D);
                rt3.setCycleCount(1);
                rt3.setByAngle(360);
                rt3.setAxis(Rotate.Y_AXIS);
                rt3.play();
                break;
        }
        
        String imagenFondo = "file:/D:/Indra/repo/app-externaloperators/app/JavaFXGUI/src/resources/modelo3D/diap"+(diapositiva_actual+1)+".png";
        parentcontainer.setStyle("-fx-background-image: url('"+imagenFondo+"'); "+"-fx-background-position: center center"+"-fx-background-repeat: stretch");
        escena.setCamera(camera);
        escenario.setScene(escena);
        escenario.show();
       
    }
}
