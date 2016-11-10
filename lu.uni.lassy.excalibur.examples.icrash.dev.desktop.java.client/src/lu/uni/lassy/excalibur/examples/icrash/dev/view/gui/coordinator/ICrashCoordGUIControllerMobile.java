package lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.coordinator;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import javafx.util.Callback;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.CoordinatorController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectActorException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectFormatException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerNotBoundException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerOfflineException;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActProxyAuthenticated.UserType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIsActor;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAlert;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtAlertStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.Message;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.actors.ActProxyCoordinatorImpl;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
/*
 * This is the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/*
 * This is the end of the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
/**
 * The Class ICrashGUIControllerMobile, used for dealing with the GUI for the coordinator.
 */
public class ICrashCoordGUIControllerMobile extends AbstractAuthGUIController {
	/*
	* This section of controls and methods is to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
	* When replacing, remember to reassign the correct methods to the button event methods and set the correct types for the tableviews
	*/

	/** The logon pane that holds the controls for logging on. */
	@FXML
    private AnchorPane anpnLogon;

	/** The data pane that holds the controls for change coord user data. */
	@FXML
	private AnchorPane anpnCoordUserData;

    /** The textfield for entering in the username for logging on. */
    @FXML
    private TextField txtfldCoordLogonUserName;

    /** The passwordfield for entering in the password for logging on. */
    @FXML
    private PasswordField psswrdfldCoordLogonPassword;

    /** The button that allows a user to initiate the logon function. */
    @FXML
    private Button bttnCoordLogon;

    /** The textfield for entering in the first name. */
    @FXML
    private TextField txtfldCoordLogonFirstName;

    /** The textfield for entering in the password for logging on. */
    @FXML
    private TextField txtfldCoordLogonLastName;
    
    /** The button that allows a user to logoff. */
    @FXML
    private Button bttnCoordLogoff;

    /** The button that allows a user to save updates. */
    @FXML
    private Button bttnCoordSaveUpdates;
    
    /** The button that shows to user alerts. */
    @FXML
    private Button bttnCoordShowAlerts;

    /** The button that shows to user crisis. */
    @FXML
    private Button bttnCoordShowCrisis;
    

    /**
     * Button event that deals with logging off the user
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordLogoff_OnClick(ActionEvent event) {
    	logoff();
    }

    /**
     * Button event that deals with logging on the user
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordLogon_OnClick(ActionEvent event) {
    	logon();
    }
    
    /**
     * Button event that deals with save user updates
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordSaveUpdates_OnClick(ActionEvent event) {
    }
    
    /**
     * Button event that deals with show alerts
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordShowAlerts_OnClick(ActionEvent event) {
    }
    
    /**
     * Button event that deals with show crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordShowCrisis_OnClick(ActionEvent event) {
    }
    
    /*
     * These are other classes accessed by this controller
     */
	
	/** The user controller, for this GUI it's the coordinator controller and allows access to coordinator functions like reporting on crises. */
	private CoordinatorController userController;
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logon()
	 */
	@Override
	public void logon() {
		if(txtfldCoordLogonUserName.getText().length() > 0 && psswrdfldCoordLogonPassword.getText().length() > 0){
			try {
				if (userController.oeLogin(txtfldCoordLogonUserName.getText(), 
						psswrdfldCoordLogonPassword.getText()).getValue()){
					if (userController.getUserType() == UserType.Coordinator){
						logonShowPanes(true);
					}
				}
			}
			catch (ServerOfflineException | ServerNotBoundException | InvalidKeyException 
					| NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException 
					| BadPaddingException | IllegalBlockSizeException | IOException e) {
				showExceptionErrorMessage(e);
			}
    	}
    	else
    		showWarningNoDataEntered();
	}

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logoff()
	 */
	@Override
	public void logoff() {
		try {
			if (userController.oeLogout().getValue()){
				logonShowPanes(false);
			}
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logonShowPanes(boolean)
	 */
	protected void logonShowPanes(boolean loggedOn){
		anpnCoordUserData.setVisible(loggedOn);
		bttnCoordLogoff.setDisable(!loggedOn);
		bttnCoordSaveUpdates.setDisable(!loggedOn);
		bttnCoordShowAlerts.setDisable(!loggedOn);
		bttnCoordShowCrisis.setDisable(!loggedOn);
		anpnLogon.setVisible(!loggedOn);
		bttnCoordLogon.setDefaultButton(!loggedOn);
		if (loggedOn){
		}
		else{
			txtfldCoordLogonUserName.setText("");
			psswrdfldCoordLogonPassword.setText("");
			txtfldCoordLogonUserName.requestFocus();	
		}
	}

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractGUIController#closeForm()
	 */
	@Override
	public void closeForm() {
		try {
			userController.removeAllListeners();
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUpTables();
		
		logonShowPanes(false);
	}

	@Override
	public PtBoolean setActor(JIntIsActor actor) {
		try { 
			if (actor instanceof ActCoordinator)
			{
				try{
					userController = new CoordinatorController((ActCoordinator)actor);
					try{
						userController.getAuthImpl().listOfMessages.addListener(new ListChangeListener<Message>() {
							@Override
							public void onChanged(ListChangeListener.Change<? extends Message> c) {
								
							}
						});
						((ActProxyCoordinatorImpl)userController.getAuthImpl()).MapOfCtAlerts.addListener(new MapChangeListener<String, CtAlert>(){
				
							@Override
							public void onChanged(
									javafx.collections.MapChangeListener.Change<? extends String, ? extends CtAlert> change) {
								
							}
						});
						((ActProxyCoordinatorImpl)userController.getAuthImpl()).MapOfCtCrisis.addListener(new MapChangeListener<String, CtCrisis>(){
							
							@Override
							public void onChanged(
									javafx.collections.MapChangeListener.Change<? extends String, ? extends CtCrisis> change) {
								
							}
						});
					} catch (Exception e){
						showExceptionErrorMessage(e);
					}
				} catch (RemoteException e){
					Log4JUtils.getInstance().getLogger().error(e);
					throw new ServerOfflineException();
				} catch (NotBoundException e){
					Log4JUtils.getInstance().getLogger().error(e);
					throw new ServerNotBoundException();
				}
			}
			else
				throw new IncorrectActorException(actor, ActCoordinator.class);
		} catch (IncorrectActorException | ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
			return new PtBoolean(false);
		}
		return new PtBoolean(true);
	}

	@Override
	public void setUpTables() {
		// TODO Auto-generated method stub
		
	}
}
