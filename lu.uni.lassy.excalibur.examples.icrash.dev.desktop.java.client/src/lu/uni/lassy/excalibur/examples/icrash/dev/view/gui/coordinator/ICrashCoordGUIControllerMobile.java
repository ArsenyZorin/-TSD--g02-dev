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
import lu.uni.lassy.excalibur.examples.icrash.dev.model.actors.ActProxyCoordinatorImpl;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
	
	/** The crises pane that holds crises. */
	@FXML
	private AnchorPane anpnCoordShowCrises;
	
	/** The crises pane that holds crisis management buttons. */
	@FXML
	private AnchorPane anpnCrisisButtons;
	
	/** The alerts pane that holds alerts management buttons. */
	@FXML
	private AnchorPane anpnAlertButtons;

	/** Pane for crisis mode switcher. */
	@FXML
	private AnchorPane anpnSwitcherCrises;

	/** Pane for crisis mode switcher. */
	@FXML
	private AnchorPane anpnSwitcherAlerts;
	
	/** The info pane. */
	@FXML
	private Pane pnInformationError;

	/** The error pane. */
	@FXML
	private Pane pnError;
	
	@FXML
	private Pane pnLogin;
	/** Back to data user. */
	@FXML
	private Button bttnCrisesBack;

	/** Back to data user. */
	@FXML
	private Button bttnAlertsBack;

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
    private Button bttnCloseCrisisMenu;

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
    private Button bttnChangeStatus;
    
    /** The button that allows a user to close a crisis. */
    @FXML
    private Button bttnCloseCrisis;
    
    /** The button that allows validation of the alert. */
    @FXML
    private Button bttnValidate;
    
    /** The button that allows invalidation of the alert. */
    @FXML
    private Button bttnInvalidate;
    
    // Switch change status show alerts 
    private SwitchButtonAlerts switchShowAlertsStatusButton;

    // Switch change status showed crises 
    private SwitchButtonCrises switchShowCrisesStatusButton;

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
    
    /** The pane with information about updating information. */
    @FXML
    private Pane pnUpdateMessage;
    
    /** The Pane with information about updating information. */
    @FXML
    private Pane pnAccountDetails;

    /** The tablview that shows the user the crises they have selected. */
    @FXML
    private TableView<CtCrisis> tblvwCrisis;

    /** The tableview of the alerts the user has retrieved from the system. */
    @FXML
    private TableView<CtAlert> tblvwAlerts;
    
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
     */
    @FXML
    void bttnCoordSaveUpdates_OnClick(ActionEvent event) throws InterruptedException {
    	if(txtfldCoordLogonFirstName.getText().length() == 0 
    			|| txtfldCoordLogonLastName.getText().length() == 0){
    		pnInformationError.setVisible(true);
       	}
    	else{
    	if(pnInformationError.isVisible()==true)
    		pnInformationError.setVisible(false);
    		pnUpdateMessage.setVisible(true);
    	}
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
    
    
    /**
     * Mouse click to table view action in crises
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void tblvwCrisis_OnClick(MouseEvent event) {
		int rowIndex = tblvwCrisis.getSelectionModel().selectedIndexProperty().get();
		System.out.println("rowIndex = " + rowIndex + ", tblviewCrisis size = " + 										tblvwCrisis.getSelectionModel().getTableView().getItems().size());	
		
		
		if ((rowIndex < 0) || (rowIndex > tblvwCrisis.getSelectionModel().getTableView().getItems().size())) {
			System.out.println("rowIndex is wrong : " + rowIndex);
			return;
		}
		
		anpnCrisisButtons.setVisible(true);
		anpnCrisisButtons.setDisable(false);
    }
    
    /**
     * Mouse click to table view action in alerts
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void tblvwAlerts_OnClick(MouseEvent event) {
		int rowIndex = tblvwCrisis.getSelectionModel().selectedIndexProperty().get();
		System.out.println("rowIndex = " + rowIndex + ", tblvwAlerts size = " + 										tblvwAlerts.getSelectionModel().getTableView().getItems().size());	
		
		
		if ((rowIndex < 0) || (rowIndex > tblvwAlerts.getSelectionModel().getTableView().getItems().size())) {
			System.out.println("rowIndex is wrong : " + rowIndex);
			return;
		}
		
		anpnAlertButtons.setVisible(true);
		anpnAlertButtons.setDisable(false);
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
    void bttnReport_OnClick(ActionEvent event) {
    	reportOnCrisis();
    }
    
    /**
     * Button event that deals with changing the status of a crisis
     *
     * @param event The event type fired, we do not need it's details
     */
    @FXML
    void bttnChangeStatus_OnClick(ActionEvent event) {
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
    void bttnValidate_OnClick(ActionEvent event) {
    	validateAlert();
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
			switch(switchShowCrisesStatusButton.switchPosProperty().get()) {
			case 1:
				userController.oeGetCrisisSet(EtCrisisStatus.pending);
				break;
			case 2:
				userController.oeGetCrisisSet(EtCrisisStatus.handled);
				break;
			case 3:
				userController.oeGetCrisisSet(EtCrisisStatus.solved);
				break;
			case 4:
				userController.oeGetCrisisSet(EtCrisisStatus.closed);
				break;
			}
			
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showServerOffLineMessage(e);
		}
	}
	
	/**
	 * Populates the tableview with a list of alerts that have the same status as the one provided.
	*/
	private void populateAlerts(){
		try {
			switch(switchShowAlertsStatusButton.switchPosProperty().get()) {
			case 1:
				userController.oeGetAlertSet(EtAlertStatus.pending);
				break;
			case 2:
				userController.oeGetAlertSet(EtAlertStatus.valid);
				break;
			case 3:
				userController.oeGetAlertSet(EtAlertStatus.invalid);
				break;
			}
			
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
		CtCrisis crisis = (CtCrisis)getObjectFromTableView(tblvwCrisis);
		if (crisis != null)
		{
			Dialog<PtBoolean> dialog = new Dialog<PtBoolean>();
			TextField txtfldCtCrisisID = new TextField();
			txtfldCtCrisisID.setText(crisis.id.value.getValue());
			txtfldCtCrisisID.setDisable(true);
			TextArea txtarCtCrisisReport = new TextArea();
			txtarCtCrisisReport.setPromptText("Enter in report");
			ButtonType bttntypReportOK = new ButtonType("Report", ButtonData.OK_DONE);
			ButtonType bttntypeReportCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			GridPane grdpn = new GridPane();
			grdpn.add(txtfldCtCrisisID, 1, 1);
			grdpn.add(txtarCtCrisisReport, 1, 2);
			dialog.getDialogPane().setContent(grdpn);
			dialog.getDialogPane().getButtonTypes().add(bttntypeReportCancel);
			dialog.getDialogPane().getButtonTypes().add(bttntypReportOK);
			dialog.setResultConverter(new Callback<ButtonType, PtBoolean>(){

				@Override
				public PtBoolean call(ButtonType param) {
					if (param.getButtonData() == ButtonData.OK_DONE && checkIfAllDialogHasBeenFilledIn(grdpn)){
						try {
							return userController.reportOnCrisis(crisis.id.value.getValue(), txtarCtCrisisReport.getText());
						} catch (ServerOfflineException | ServerNotBoundException e) {
							showServerOffLineMessage(e);
						} catch (IncorrectFormatException e) {
							showWarningIncorrectInformationEntered(e);
						}
					}
					//User cancelled the dialog
					return new PtBoolean(true);
				}
			});
			dialog.initOwner(window);
			dialog.initModality(Modality.WINDOW_MODAL);
			Optional<PtBoolean> result = dialog.showAndWait();
			if (result.isPresent()){
				if (!result.get().getValue())
					showWarningMessage("Unable to report on crisis", "Unable to report on the crisis, please try again");
			}
		}
		populateCrisis();
	}
	
	/**
	 * Runs the function that will allow the current user to change the selected crisis' status.
	 */
	private void changeCrisisStatus(){
		CtCrisis crisis = (CtCrisis)getObjectFromTableView(tblvwCrisis);
		if (crisis != null){
			Dialog<PtBoolean> dialog = new Dialog<PtBoolean>();
			dialog.setTitle("Change the crisis status");
			TextField txtfldCtCrisisID = new TextField();
			txtfldCtCrisisID.setText(crisis.id.value.getValue());
			txtfldCtCrisisID.setDisable(true);
			ComboBox<EtCrisisStatus> cmbbx = new ComboBox<EtCrisisStatus>();
			cmbbx.setItems( FXCollections.observableArrayList( EtCrisisStatus.values()));
			cmbbx.setValue(crisis.status);
			ButtonType bttntypOK = new ButtonType("Change status", ButtonData.OK_DONE);
			ButtonType bttntypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			GridPane grdpn = new GridPane();
			grdpn.add(txtfldCtCrisisID, 1, 1);
			grdpn.add(cmbbx, 1, 2);
			dialog.getDialogPane().setContent(grdpn);
			dialog.getDialogPane().getButtonTypes().add(bttntypeCancel);
			dialog.getDialogPane().getButtonTypes().add(bttntypOK);
			dialog.setResultConverter(new Callback<ButtonType, PtBoolean>(){
				@Override
				public PtBoolean call(ButtonType param) {
					if (param.getButtonData() == ButtonData.OK_DONE && checkIfAllDialogHasBeenFilledIn(grdpn)){
						try {
							return userController.changeCrisisStatus(crisis.id.value.getValue(), cmbbx.getValue());
						} catch (ServerOfflineException | ServerNotBoundException e) {
							showServerOffLineMessage(e);
						} catch (IncorrectFormatException e) {
							showWarningIncorrectInformationEntered(e);
						}
					}
					//User cancelled the dialog
					return new PtBoolean(true);
				}
			});
			dialog.initOwner(window);
			dialog.initModality(Modality.WINDOW_MODAL);
			Optional<PtBoolean> result = dialog.showAndWait();
			if (result.isPresent()){
				if (!result.get().getValue())
					showWarningMessage("Unable to change status of crisis", "Unable to change status of crisis, please try again");
			}
		}
		populateCrisis();
	}
	
	/**
	 * Runs the function that will allow the current user to validate the selected alert.
	 */
	private void validateAlert(){
		CtAlert alert = (CtAlert)getObjectFromTableView(tblvwAlerts);
		if (alert != null)
			try {
				userController.validateAlert(alert.id.value.getValue());
			} catch (ServerOfflineException | ServerNotBoundException e) {
				showServerOffLineMessage(e);
			} catch (IncorrectFormatException e) {
				showWarningIncorrectInformationEntered(e);
			}
		populateAlerts();
	}
	
	/**
	 * Runs the function that will allow the current user to invalidate the selected alert.
	 */
	private void invalidateAlert(){
		CtAlert alert = (CtAlert)getObjectFromTableView(tblvwAlerts);
		if (alert != null)
			try {
				if (!userController.invalidateAlert(alert.id.value.getValue()).getValue())
					showWarningMessage("Unable to invalidate alert", "Unable to invalidate alert, please try again");
			} catch (ServerOfflineException | ServerNotBoundException e) {
				showServerOffLineMessage(e);
			} catch (IncorrectFormatException e) {
				showWarningIncorrectInformationEntered(e);
			}
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
					pnError.setVisible(true);
				}
			}
			catch (ServerOfflineException | ServerNotBoundException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | IOException e) {
				showExceptionErrorMessage(e);
			}
    	}
    	else{
    		pnError.setVisible(true);
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

	/**
	 * Show logon panes and hide all another.
	 */
	protected void showLogonPanes(boolean flag){
		setDisVisLogon(flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(!flag);
		setDisVisCrises(!flag);
	}
	
	/**
	 * Show user data panes and hide all another.
	 */
	protected void showUserDataPanes(boolean flag){
		setDisVisLogon(!flag);
		setDisVisUserData(flag);
		setDisVisAlerts(!flag);
		setDisVisCrises(!flag);
		pnAccountDetails.setVisible(true);
    	pnUpdateMessage.setVisible(false);
	}
	

	/**
	 * Show alerts panes and hide all another.
	 */
	protected void showAlertsPanes(boolean flag){
		switchShowAlertsStatusButton = new SwitchButtonAlerts();
		setDisVisLogon(!flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(flag);
		setDisVisCrises(!flag);
		for(int i = anpnSwitcherAlerts.getChildren().size() -1; i >= 0; i--)
			anpnSwitcherAlerts.getChildren().remove(i);
		
		anpnSwitcherAlerts.getChildren().add(switchShowAlertsStatusButton);
		AnchorPane.setTopAnchor(switchShowAlertsStatusButton, 240.0);
		AnchorPane.setLeftAnchor(switchShowAlertsStatusButton, 20.0);
		AnchorPane.setBottomAnchor(switchShowAlertsStatusButton, 80.0);
		AnchorPane.setRightAnchor(switchShowAlertsStatusButton, 20.0);
	}
	

	/**
	 * Show crises panes and hide all another.
	 */
	protected void showCrisesPanes(boolean flag){
		switchShowCrisesStatusButton = new SwitchButtonCrises();
		setDisVisLogon(!flag);
		setDisVisUserData(!flag);
		setDisVisAlerts(!flag);
		setDisVisCrises(flag);
		for(int i = anpnSwitcherCrises.getChildren().size() -1; i >= 0; i--)
			anpnSwitcherCrises.getChildren().remove(i);
		
		anpnSwitcherCrises.getChildren().add(switchShowCrisesStatusButton);
		AnchorPane.setTopAnchor(switchShowCrisesStatusButton, 240.0);
		AnchorPane.setLeftAnchor(switchShowCrisesStatusButton, 20.0);
		AnchorPane.setBottomAnchor(switchShowCrisesStatusButton, 80.0);
		AnchorPane.setRightAnchor(switchShowCrisesStatusButton, 20.0);
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
		} catch (IncorrectActorException | ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
			return new PtBoolean(false);
		}
		return new PtBoolean(true);
	}

	@Override
	public void setUpTables() {
		setUpCrisesTables(tblvwCrisis);
		setUpAlertTables(tblvwAlerts);
		//cmbbxCrisisStatus.setItems( FXCollections.observableArrayList( EtCrisisStatus.values()));
		//cmbbxAlertStatus.setItems( FXCollections.observableArrayList( EtAlertStatus.values()));
	}

}
