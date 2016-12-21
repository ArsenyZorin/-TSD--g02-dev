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

import com.sun.javafx.tk.Toolkit.Task;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import javafx.util.Callback;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.CoordinatorController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.SystemStateController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectActorException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectFormatException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerNotBoundException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerOfflineException;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActProxyCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActProxyAuthenticated.UserType;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIsActor;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAlert;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtCrisis;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorFirstName;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorLastName;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtAlertStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.EtCrisisStatus;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.actors.ActProxyCoordinatorImpl;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
/*
 * This is the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

	/** The alerts pane that holds alerts. */
	@FXML
	private AnchorPane anpnCoordShowAlerts;
	
	/** The alerts pane that holds alert's information. */
	@FXML
	private AnchorPane anpnCoordAlertInfo;
	
	/** The crises pane that holds crises. */
	@FXML
	private AnchorPane anpnCoordShowCrises;
	
	/** The crisis pane that holds crisis information. */
	@FXML
	private AnchorPane anpnCoordCrisisInfo;
	
	/** The crises pane that holds crisis management buttons. */
	@FXML
	private AnchorPane anpnCrisisButtons;
	
	/** The alerts pane that holds alerts management buttons. */
	@FXML
	private AnchorPane anpnAlertButtons;

	/** Pane for crisis mode switcher. */
	@FXML
	private AnchorPane anpnSwitcherCrises;
	
	/** The info pane. */
	@FXML
	private Pane pnInformationError;

	/** The error pane. */
	@FXML
	private Pane pnError;
	
	/** The error pane. */
	@FXML
	private Pane pnAlertError;
	
	/** The crisis error pane. */
	@FXML
	private Pane pnCrisisError;
	
	@FXML
	private Pane pnLogin;
	/** Back to data user. */
	@FXML
	private Button bttnCrisesBack;

	/** Back to data user. */
	@FXML
	private Button bttnAlertsBack;
	
	/** Back to alerts table. */
	@FXML
	private Button bttnAlertInfoBack;
	
	/** Back to crisises table. */
	@FXML
	private Button bttnCrisisInfoBack;

    /** The button that allows a user to initiate the log on function. */
    @FXML
    private Button bttnCoordLogon;

    /** The button that allows a user to log off. */
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
    private Button bttnCoordShowCrises;
    
    /** The button that close pop-up menu. */
    @FXML
    private Button bttnChangeStatus;

    /** The button that close pop-up menu. */
    @FXML
    private Button bttnCloseAlertMenu;
    
    /** The button that allows a user to handle a crisis. */
    @FXML
    private Button bttnHandleCrisis;
    
    /** The button that allows a user to report on a crisis. */
    @FXML
    private Button bttnReport;

    /** The button that allows a user to change the status of a crisis. */
    @FXML
    private Button bttnChangeCrisisStatus;
    
    /** The button that allows a user to close a crisis. */
    @FXML
    private Button bttnCloseCrisis;
    
    /** The button that allows validation of the alert. */
    @FXML
    private Button bttnAlertValidate;
    
    /** The button that allows invalidation of the alert. */
    @FXML
    private Button bttnAlertInvalidate;
    
    @FXML
    private ChoiceBox<EtAlertStatus> choiceAlertStatus;
    
    @FXML
    private ChoiceBox<EtCrisisStatus> choiceCrisisStatus;
    
    @FXML
    private ChoiceBox<EtCrisisStatus> menuChangeCrisisStatus;

    /** The passwordfield for entering in the password for logging on. */
    @FXML
    private PasswordField psswrdfldCoordLogonPassword;
    
    /** The textfield for entering in the username for logging on. */
    @FXML
    private TextField txtfldCoordLogonUserName;
    
    /** The textfield for entering in the first name. */
    @FXML
    private TextField txtfldCoordLogonFirstName;

    /** The textfield for entering in the password for logging on. */
    @FXML
    private TextField txtfldCoordLogonLastName;
    
    /** The textfield for focusing in tableView. */
    @FXML
    private TextField txtfldFocus;
    
    /** The label for alert id. */
    @FXML
    private Label labelAlertID;
    
    /** The label for alert status. */
    @FXML
    private Label labelAlertStatus;
    
    /** The label for alert time. */
    @FXML
    private Label labelAlertDateTime;
    
    /** The label for alert location. */
    @FXML
    private Label labelAlertLocation;
    
    /** The text area for alert comment. */
    @FXML
    private TextArea textAreaAlertComment;
    
    /** The label for crisis id. */
    @FXML
    private Label labelCrisisID;
    
    /** The label for crisis status. */
    @FXML
    private Label labelCrisisStatus;
    
    @FXML
    private Label labelChangeID;
    
    /** The label for crisis time. */
    @FXML
    private Label labelCrisisDateTime;
    
    /** The label for crisis location. */
    @FXML
    private Label labelCrisisLocation;
    
    /** The text area for crisis report. */
    @FXML
    private TextArea textAreaCrisisReport;
    
    /** The pane with information about updating information. */
    @FXML
    private Pane pnUpdateMessage;
    
    @FXML
    private Pane pnChangeCrisisStatus;
    
    /** The Pane with information about updating information. */
    @FXML
    private Pane pnAccountDetails;

    /** The tablview that shows the user the crises they have selected. */
    @FXML
    private TableView<CtCrisis> tblvwCrisis;

    /** The tableview of the alerts the user has retrieved from the system. */
    @FXML
    private TableView<CtAlert> tblvwAlerts;
    
    private DtCoordinatorID currentCoordID;
    
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
     * @throws InterruptedException 
     * @throws ServerOfflineException 
     * @throws ServerNotBoundException 
     * @throws RemoteException 
     */
    @FXML
    void bttnCoordSaveUpdates_OnClick(ActionEvent event) throws InterruptedException, RemoteException, ServerNotBoundException, ServerOfflineException {
    	    	
    	if(txtfldCoordLogonFirstName.getText().length() == 0 
    			|| txtfldCoordLogonLastName.getText().length() == 0){
    		showUpdateError();
       	}    	
    	saveCoordinatorUpdates();
    }
    
    /**
     * Button event that deals with show alerts
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordShowAlerts_OnClick(ActionEvent event) {
    	showAlertsPanes(true);
    }
    
    /**
     * Button event that deals with show crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCoordShowCrises_OnClick(ActionEvent event) {
    	showCrisesPanes(true);
    }
    
    /**
     * Button show user data
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCrisesBack_OnClick(ActionEvent event) {
    	showUserDataPanes(true);
    }
    
    /**
     * Button show user data
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnAlertsBack_OnClick(ActionEvent event) {
    	showUserDataPanes(true);
    }
    
    @FXML
    void bttnAlertInfoBack_OnClick(ActionEvent event) {
    	showAlertsPanes(true);
    }
    
    @FXML
    void bttnCrisisInfoBack_OnClick(ActionEvent event) {
    	showCrisesPanes(true);
    }
    
    /**
     * Mouse click to table view action in crises
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void tblvwCrisis_OnClick(MouseEvent event) {
    	if(tblvwCrisis.getItems().isEmpty()) return;
		int clickCount = event.getClickCount();
		if(clickCount < 1) return;
		CtCrisis crisis = (CtCrisis)getObjectFromTableView(tblvwCrisis);
		labelCrisisID.setText(crisis.id.toString());
		labelCrisisStatus.setText(crisis.status.toString());
		labelCrisisDateTime.setText(crisis.instant.toString());
		labelCrisisLocation.setText(crisis.location.longitude.value.getValue() + 
				", " + crisis.location.latitude.value.getValue());
		try{
		textAreaCrisisReport.setText(crisis.comment.value.getValue());
		}
		catch (NullPointerException e){
			e.getMessage();
		}
		showCrisisInfoPanes(true);
    }
    
    /**
     * Mouse click to table view action in alerts
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void tblvwAlerts_OnClick(MouseEvent event) {
    	if(tblvwAlerts.getItems().isEmpty()) return;
		int clickCount = event.getClickCount();
		if(clickCount < 1) return;
		CtAlert alert = ((CtAlert)getObjectFromTableView(tblvwAlerts)); 
		labelAlertID.setText(alert.id.toString());
		labelAlertStatus.setText(alert.status.toString());
		labelAlertDateTime.setText(alert.instant.toString());
		labelAlertLocation.setText(alert.location.longitude.value.getValue() + ", " + tblvwAlerts.getSelectionModel().getSelectedItem().location.latitude.value.getValue());
		textAreaAlertComment.setText(alert.comment.toString());
		
		if(labelAlertStatus.getText().equals("valid")) {
			bttnAlertValidate.setDisable(true);
			bttnAlertInvalidate.setDisable(false);
		}
		else if (labelAlertStatus.getText().equals("invalid")) {
			bttnAlertValidate.setDisable(false);
			bttnAlertInvalidate.setDisable(true);
		}
		
		showAlertInfoPanes(true);
    }
    
    /**
     * Button close pop-up menu
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCloseCrisisMenu_OnClick(ActionEvent event) {
    	anpnCrisisButtons.setVisible(false);
		anpnCrisisButtons.setDisable(true);
    }
    
    /**
     * Button event that deals with handling of a crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnHandleCrisis_OnClick(ActionEvent event) {
    	handleCrisis();
    }
    
    /**
     *  Button event that deals with reportinng on a crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCrisisReport_OnClick(MouseEvent event) {
    	if(!textAreaCrisisReport.isEditable())
    		textAreaCrisisReport.setEditable(true);
    	else
    		reportOnCrisis();
    }

    /**
     * Button event that deals with changing the status of a crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnChangeCrisisStatus_OnClick(MouseEvent event) {
    	setDisVisChangeStatus(true);
    }
    
    @FXML
    void bttnChangeStatus_OnClick(MouseEvent event) {
    	changeCrisisStatus();
    }
    /**
     * Button event that deals with closing a crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCloseCrisis_OnClick(ActionEvent event) {
    	closeCrisis();
    }
    
    /**
     * Button event that deals with validating an alert
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnAlertValidate_OnClick(MouseEvent event) {
    	validateAlert();
    }
    
    @FXML
    void bttnInvalidate_OnClick(MouseEvent event) {
    	invalidateAlert();
    }
    /**
     * Button event that deals with invalidating of an alert
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnInvalidate_OnClick(ActionEvent event) {
    	invalidateAlert();
    }
    
    /**
     * Button close alert pop-up menu
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnCloseAlertMenu_OnClick(ActionEvent event) {
    	anpnAlertButtons.setVisible(false);
		anpnAlertButtons.setDisable(true);
    }
    
    @FXML
    void bttnCloseChangeWindow_OnClick(MouseEvent event){
    	setDisVisChangeStatus(false);
    }
    /*
     * These are other classes accessed by this controller
     */
	
	/** The user controller, for this GUI it's the coordinator controller and allows access to coordinator functions like reporting on crises. */
	private CoordinatorController userController;
	
	
	
	/**
	 * Populates the tableview with a list of crisis that have the same status as the one provided.
	*/ 
	private void populateCrisis(){
		try {
			userController.oeGetCrisisSet(choiceCrisisStatus.getValue());
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showServerOffLineMessage(e);
		}
	}
	
	/**
	 * Populates the tableview with a list of alerts that have the same status as the one provided.
	*/
	private void populateAlerts(){
		try {
			userController.oeGetAlertSet(choiceAlertStatus.getValue());
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showServerOffLineMessage(e);
		}
	}
	
	/**
	 * Runs the function that will allow the current user to handle the selected crisis.
	 */
	private void handleCrisis(){
		CtCrisis crisis = (CtCrisis)getObjectFromTableView(tblvwCrisis);
		if (crisis != null){
			try {
				if (!userController.handleCrisis(crisis.id.value.getValue()).getValue())
					showWarningMessage("Unable to handle crisis", "Unable to handle crisis, please try again");
			} catch (ServerOfflineException | ServerNotBoundException e) {
				showServerOffLineMessage(e);
			} catch (IncorrectFormatException e) {
				showWarningIncorrectInformationEntered(e);
			}
		}
		populateCrisis();
	}
	
	/**
	 * Runs the function that will allow the current user to close the selected crisis.
	 */
	private void closeCrisis(){
		CtCrisis crisis = (CtCrisis)getObjectFromTableView(tblvwCrisis);
		if (crisis != null)
		{
			try {
				userController.closeCrisis(crisis.id.value.getValue());
			} catch (ServerOfflineException | ServerNotBoundException e) {
				showServerOffLineMessage(e);
			} catch (IncorrectFormatException e) {
				showWarningIncorrectInformationEntered(e);
			}
		}
		populateCrisis();
	}
	
	/**
	 * Runs the function that will allow the current user to report on the selected crisis.
	 */
	private void reportOnCrisis(){
		try {
			userController.reportOnCrisis(labelCrisisID.getText(), textAreaCrisisReport.getText());
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showServerOffLineMessage(e);
		} catch (IncorrectFormatException e) {
			showWarningIncorrectInformationEntered(e);
		}
		textAreaCrisisReport.setEditable(false);
		populateCrisis();
	}
	
	/**
	 * Runs the function that will allow the current user to change the selected crisis' status.
	 */
	private void changeCrisisStatus(){
		try {
			if(userController.changeCrisisStatus(labelChangeID.getText(), menuChangeCrisisStatus.getValue()).getValue()){
				showCrisisError();
			}
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showServerOffLineMessage(e);
		} catch (IncorrectFormatException e) {
			showWarningIncorrectInformationEntered(e);
		}
		setDisVisChangeStatus(false);
		setDisVisCrisisInfo(false);
		setDisVisCrises(true);
		populateCrisis();
	}

	protected void getCoordinatorID(DtCoordinatorID aDtCoordinatorID){
		currentCoordID = aDtCoordinatorID;
	}
	
	protected void saveCoordinatorUpdates() throws RemoteException, ServerNotBoundException, ServerOfflineException{
			PtString firstName = new PtString(txtfldCoordLogonFirstName.getText());
			PtString lastName = new PtString(txtfldCoordLogonLastName.getText());
			
			try {
				if(userController.oeSaveUpdates(currentCoordID, new DtCoordinatorFirstName(firstName), 
						new DtCoordinatorLastName(lastName)).getValue()){
					showUpdateMessage();
				}
				else
					showUpdateError();
			} catch (ServerOfflineException | ServerNotBoundException | NotBoundException e) {
				showWarningIncorrectData(e.getMessage());
			}
			
	}
	
	/**
	 * Runs the function that will allow the current user to validate the selected alert.
	 */
	private void validateAlert(){
		try {
			userController.validateAlert(labelAlertID.getText());
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showServerOffLineMessage(e);
		} catch (IncorrectFormatException e) {
			showWarningIncorrectInformationEntered(e);
		}
		
		setDisVisAlertInfo(false);
		setDisVisAlerts(true);
		populateAlerts();	
	}
	
	/**
	 * Runs the function that will allow the current user to invalidate the selected alert.
	 */
	private void invalidateAlert(){
			try {
				if (!userController.invalidateAlert(labelAlertID.getText()).getValue()){
					showAlertError();					
				}
			} catch (ServerOfflineException | ServerNotBoundException e) {
				showServerOffLineMessage(e);
			} catch (IncorrectFormatException e) {
				showWarningIncorrectInformationEntered(e);
			}
		
		setDisVisAlertInfo(false);
		setDisVisAlerts(true);
		populateAlerts();
	}
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logon()
	 */
	@Override
	public void logon() {
		if(txtfldCoordLogonUserName.getText().length() > 0 && psswrdfldCoordLogonPassword.getText().length() > 0){
			try {
				if (userController.oeLogin(txtfldCoordLogonUserName.getText(), psswrdfldCoordLogonPassword.getText()).getValue()){
					if (userController.getUserType() == UserType.Coordinator){						
						showUserDataPanes(true);
					}
				}
				else {
					showLoginError();
				}
			}
			catch (ServerOfflineException | ServerNotBoundException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | IOException e) {
				showExceptionErrorMessage(e);
			}
    	}
    	else{
    		showLoginError();
    	}
	}

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logoff()
	 */
	@Override
	public void logoff() {
		try {
			if (userController.oeLogout().getValue()){
				logonShowPanes(true);
			}
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}	

	@Override
	protected void logonShowPanes(boolean loggedOn) {
		showLogonPanes(loggedOn);
	}
	
	private void showLoginError(){
		Thread loginError = new Thread(()->
		{
			setDisVisLoginError(true);
		 	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		 	setDisVisLoginError(false);
		});
		loginError.start();
	}
	
	private void showUpdateError(){
		Thread updateError = new Thread(()->
		{
			setDisVisUpdateError(true);
		 	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		 	setDisVisUpdateError(false);
		});
		updateError.start();
	}
	
	private void showUpdateMessage(){
		Thread updateMsg = new Thread(()->
		{
			setDisVisUpdateInfo(true);
		 	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		 	setDisVisUpdateInfo(false);
		});
		updateMsg.start();
	}
	
	private void showAlertError(){
		Thread alertError = new Thread(()->
		{
			setDisVisAlertError(true);
		 	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		 	setDisVisAlertError(false);
		});
		alertError.start();
	}
	
	private void showCrisisError(){
		Thread crisisError = new Thread(()->
		{
			setDisVisCrisisError(true);
		 	try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.getMessage();
			}
		 	setDisVisCrisisError(false);
		});
		crisisError.start();
	}
	
	private void setDisVisUpdateInfo(boolean flag){
		pnAccountDetails.setDisable(flag);
		pnAccountDetails.setVisible(!flag);
		pnUpdateMessage.setDisable(!flag);
		pnUpdateMessage.setVisible(flag);
	}
	
	private void setDisVisUpdateError(boolean flag){
		pnAccountDetails.setDisable(flag);
		pnAccountDetails.setVisible(!flag);
		pnInformationError.setDisable(!flag);
		pnInformationError.setVisible(flag);
	}
	/**
	 * Show logon error pane and hide all another.
	 */
	private void setDisVisLoginError(boolean flag){
		pnLogin.setDisable(flag);
		pnLogin.setVisible(!flag);
		pnError.setDisable(!flag);
		pnError.setVisible(flag);
	}
	
	/**
	 * Show logon panes and hide all another.
	 */
	protected void showLogonPanes(boolean flag){
		setDisVisLogon(flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(!flag);
		setDisVisAlertInfo(!flag);
		setDisVisCrises(!flag);
	}
	
	/**
	 * Show user data panes and hide all another.
	 */
	protected void showUserDataPanes(boolean flag){
		setDisVisLogon(!flag);
		setDisVisUserData(flag);
		setDisVisAlerts(!flag);
		setDisVisAlertInfo(!flag);
		setDisVisCrises(!flag);
		pnAccountDetails.setVisible(true);
    	pnUpdateMessage.setVisible(false);
	}
	
	/**
	 * Show alerts panes and hide all another.
	 */
	protected void showAlertsPanes(boolean flag){
		setDisVisLogon(!flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(flag);
		setDisVisAlertInfo(!flag);
		setDisVisCrises(!flag);
		choiceAlertStatus.getSelectionModel().select(0);
	}
	
	protected void showAlertInfoPanes(boolean flag){
		setDisVisLogon(!flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(!flag);
		setDisVisAlertInfo(flag);
		setDisVisCrises(!flag);
	}	

	/**
	 * Show crises panes and hide all another.
	 */
	protected void showCrisesPanes(boolean flag){
		setDisVisLogon(!flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(!flag);
		setDisVisAlertInfo(!flag);
		setDisVisCrises(flag);
		setDisVisCrisisInfo(!flag);
	}
	
	protected void showCrisisInfoPanes(boolean flag){
		setDisVisLogon(!flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(!flag);
		setDisVisAlertInfo(!flag);
		setDisVisCrises(!flag);
		setDisVisCrisisInfo(flag);
	}	

	/**
	 * Show or hide logon panes.
	 */
	private void setDisVisLogon(boolean flag){
		anpnLogon.setDisable(!flag);
		anpnLogon.setVisible(flag);
		if (flag) {
			txtfldCoordLogonUserName.setText("");
			psswrdfldCoordLogonPassword.setText("");
			txtfldCoordLogonUserName.requestFocus();
		}
	}

	/**
	 * Show or hide user data panes.
	 */
	private void setDisVisUserData(boolean flag){
		anpnCoordUserData.setDisable(!flag);
		anpnCoordUserData.setVisible(flag);
	}

	/**
	 * Show or hide alerts panes.
	 */
	private void setDisVisAlerts(boolean flag){
		anpnCoordShowAlerts.setDisable(!flag);
		anpnCoordShowAlerts.setVisible(flag);
		if (flag) {
			populateAlerts();
		}
	}

	private void setDisVisCrisisError(boolean flag){
		pnCrisisError.setDisable(!flag);
		pnCrisisError.setVisible(flag);
	}
	
	private void setDisVisAlertError(boolean flag){
		pnAlertError.setDisable(!flag);
		pnAlertError.setVisible(flag);
	}
	
	private void setDisVisAlertInfo(boolean flag){
		anpnCoordAlertInfo.setDisable(!flag);
		anpnCoordAlertInfo.setVisible(flag);
		if (flag) {
			populateAlerts();
		}
	}
	
	private void setDisVisChangeStatus (boolean flag) {
		pnChangeCrisisStatus.setDisable(!flag);
		pnChangeCrisisStatus.setVisible(flag);
		if(flag) {
			labelChangeID.setText(labelCrisisID.getText());
			menuChangeCrisisStatus.getSelectionModel().
				select(EtCrisisStatus.valueOf(labelCrisisStatus.getText()));				
		}
	}

	/**
	 * Show or hide crises panes.
	 */
	private void setDisVisCrises(boolean flag){
		anpnCoordShowCrises.setDisable(!flag);
		anpnCoordShowCrises.setVisible(flag);
		if (flag) {
			populateCrisis();
		}
	}
	
	/**
	 * Show or hide crisis information pane
	 */
	private void setDisVisCrisisInfo(boolean flag){
		anpnCoordCrisisInfo.setDisable(!flag);
		anpnCoordCrisisInfo.setVisible(flag);
		if (flag) {
			populateAlerts();
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
		showLogonPanes(true);
	}

	@Override
	public PtBoolean setActor(JIntIsActor actor) {
		try { 
			if (actor instanceof ActCoordinator)
			{
				try{
					userController = new CoordinatorController((ActCoordinator)actor);
					try{
						((ActProxyCoordinatorImpl)userController.getAuthImpl()).MapOfCtAlerts.addListener(new MapChangeListener<String, CtAlert>(){
				
							@Override
							public void onChanged(
									javafx.collections.MapChangeListener.Change<? extends String, ? extends CtAlert> change) {
								addAlertsToTableView(tblvwAlerts, change.getMap().values());
							}
						});
						((ActProxyCoordinatorImpl)userController.getAuthImpl()).MapOfCtCrisis.addListener(new MapChangeListener<String, CtCrisis>(){
							
							@Override
							public void onChanged(
									javafx.collections.MapChangeListener.Change<? extends String, ? extends CtCrisis> change) {
								addCrisesToTableView(tblvwCrisis, change.getMap().values());
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
		} catch (IncorrectActorException | ServerOfflineException 
				| ServerNotBoundException e) {
			showExceptionErrorMessage(e);
			return new PtBoolean(false);
		}
		return new PtBoolean(true);
	}

	@Override
	public void setUpTables() {
		setUpCrisisTableMobile(tblvwCrisis);
		setUpAlertTableMobile(tblvwAlerts);
		setUpAlertsChoiceBox();
		setUpCrisisChoiceBox();		
	}

	public void setUpAlertsChoiceBox(){
		choiceAlertStatus.setItems(FXCollections.
				observableArrayList( EtAlertStatus.values()));	
		choiceAlertStatus.getSelectionModel().select(0);
		choiceAlertStatus.getSelectionModel().selectedItemProperty()
			.addListener((ObservableValue<? extends EtAlertStatus> observable, 
					EtAlertStatus oldValue, EtAlertStatus newValue) -> 
			populateAlerts() );
	}
	
	public void setUpCrisisChoiceBox(){
		choiceCrisisStatus.setItems(FXCollections.
				observableArrayList(EtCrisisStatus.values()));
		choiceCrisisStatus.getSelectionModel().select(0);
		choiceCrisisStatus.getSelectionModel().selectedItemProperty()
			.addListener((ObservableValue<? extends EtCrisisStatus> observable, 
					EtCrisisStatus oldValue, EtCrisisStatus newValue) -> 
			populateCrisis());
		menuChangeCrisisStatus.setItems(FXCollections.
				observableArrayList(EtCrisisStatus.values()));
		menuChangeCrisisStatus.getSelectionModel().select(0);
	}
	
	public void setUpCrisisTableMobile(TableView<CtCrisis> tblvw){
		TableColumn<CtCrisis, String> idCol = 
				new TableColumn<CtCrisis, String>("ID");
		TableColumn<CtCrisis, String> statusCol = 
				new TableColumn<CtCrisis, String>("Date and time");
		idCol.setCellValueFactory(new Callback<CellDataFeatures<CtCrisis, 
				String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<CtCrisis, 
					String> crisis) {
				return new ReadOnlyObjectWrapper<String>(crisis.getValue()
						.id.value.getValue());
			}
		});
		statusCol.setCellValueFactory(new Callback<CellDataFeatures<CtCrisis, 
				String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<CtCrisis, 
					String> crisis) {
				return new ReadOnlyObjectWrapper<String>(crisis.getValue()
						.instant.toString());
			}
		});
		tblvw.getColumns().add(idCol);
		tblvw.getColumns().add(statusCol);
		setColumnsSameWidth(tblvw);
	}
	
	public void setUpAlertTableMobile(TableView<CtAlert> tblvw){
		TableColumn<CtAlert, String> idCol = new TableColumn<CtAlert, 
				String>("ID");
		TableColumn<CtAlert, String> statusCol = new TableColumn<CtAlert, 
				String>("Date and time");
		idCol.setCellValueFactory(new Callback<CellDataFeatures<CtAlert, 
				String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<CtAlert, 
					String> alert) {
				return new ReadOnlyObjectWrapper<String>(alert.getValue()
						.id.value.getValue());
			}
		});
		statusCol.setCellValueFactory(new Callback<CellDataFeatures<CtAlert, 
				String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<CtAlert, 
					String> alert) {
				return new ReadOnlyObjectWrapper<String>(alert.getValue()
						.instant.toString());
			}
		});
		tblvw.getColumns().add(idCol);
		tblvw.getColumns().add(statusCol);
		setColumnsSameWidth(tblvw);
	}
	
	private void setColumnsSameWidth(TableView<?> tblvw){
		for(TableColumn<?,?> col : tblvw.getColumns()){
			col.prefWidthProperty().bind(tblvw.widthProperty()
					.divide( tblvw.getColumns().size()));
		}
	}

}
