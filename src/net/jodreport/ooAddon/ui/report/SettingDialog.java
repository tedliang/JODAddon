package net.jodreport.ooAddon.ui.report;

import com.sun.star.awt.XButton;
import com.sun.star.lang.XComponent;
import com.sun.star.awt.XControl;
import com.sun.star.awt.XControlModel;
import com.sun.star.awt.XControlContainer;
import com.sun.star.awt.XDialog;
import com.sun.star.awt.XTextComponent;
import com.sun.star.awt.XToolkit;
import com.sun.star.awt.XWindow;
import com.sun.star.beans.XPropertySet;
import com.sun.star.container.XNameContainer;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.lang.XMultiServiceFactory;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import net.jodreport.ooAddon.ui.StandardActionListener;
import net.jodreport.ooAddon.ui.StandardDialog;

/**
 *
 * @author tedliang
 */

public class SettingDialog implements StandardDialog {

    private static final String _datafileName = "DataFile";
    private static final String _buttonName = "Button1";
    private static final String _cancelButtonName = "CancelButton";
    private static final String _labelName = "Label1";
    
    private final XComponentContext _xComponentContext;
    private final Setting setting;

    private boolean reportRequired = false;

    private XDialog xDialog;
    private Object dialog;
    
    
    public SettingDialog( XComponentContext xComponentContext, Setting setting ) {
        _xComponentContext = xComponentContext;
        this.setting=setting;
    }

    /** method for creating a dialog at runtime
     */
    public boolean createDialog() throws com.sun.star.uno.Exception {

        // get the service manager from the component context
        XMultiComponentFactory xMultiComponentFactory = _xComponentContext.getServiceManager();
        
        // create the dialog model and set the properties
        Object dialogModel = xMultiComponentFactory.createInstanceWithContext(
            "com.sun.star.awt.UnoControlDialogModel", _xComponentContext );
        XPropertySet xPSetDialog = UnoRuntime.queryInterface(
            XPropertySet.class, dialogModel );      
        xPSetDialog.setPropertyValue( "PositionX", new Integer( 100 ) );
        xPSetDialog.setPropertyValue( "PositionY", new Integer( 100 ) );
        xPSetDialog.setPropertyValue( "Width", new Integer( 250 ) );
        xPSetDialog.setPropertyValue( "Height", new Integer( 70 ) );
        xPSetDialog.setPropertyValue( "Title", new String( "Generate Report" ) );

        // get the service manager from the dialog model
        XMultiServiceFactory xMultiServiceFactory = UnoRuntime.queryInterface(
            XMultiServiceFactory.class, dialogModel );

        // create the label model and set the properties
        Object labelModel = xMultiServiceFactory.createInstance("com.sun.star.awt.UnoControlFixedTextModel" );
        XPropertySet xPSetLabel = UnoRuntime.queryInterface(
            XPropertySet.class, labelModel );
        xPSetLabel.setPropertyValue( "PositionX", new Integer( 10 ) );
        xPSetLabel.setPropertyValue( "PositionY", new Integer( 10 ) );
        xPSetLabel.setPropertyValue( "Width", new Integer( 100 ) );
        xPSetLabel.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetLabel.setPropertyValue( "Name", _labelName );
        xPSetLabel.setPropertyValue( "Label", "Please specify data file:" );

        // create a controlmodel at the multiservicefactory of the dialog model...
        Object dataFileModel = xMultiServiceFactory.createInstance("com.sun.star.awt.UnoControlFileControlModel");

        // Set the properties at the model - keep in mind to pass the property names in alphabetical order!
        XPropertySet xFCModelMPSet = UnoRuntime.queryInterface(XPropertySet.class, dataFileModel);
        xFCModelMPSet.setPropertyValue( "PositionX", new Integer( 10 ) );
        xFCModelMPSet.setPropertyValue( "PositionY", new Integer( 25 ) );
        xFCModelMPSet.setPropertyValue( "Width", new Integer( 220 ) );
        xFCModelMPSet.setPropertyValue( "Height", new Integer( 14 ) );
        xFCModelMPSet.setPropertyValue( "Name", _datafileName );
        xFCModelMPSet.setPropertyValue( "TabIndex", new Short( (short)10 ) );
        if(setting.getDataFilePath()!=null){
            xFCModelMPSet.setPropertyValue( "Text", setting.getDataFilePath());
        }

        // create the button model and set the properties
        Object buttonModel = xMultiServiceFactory.createInstance(
            "com.sun.star.awt.UnoControlButtonModel" );
        XPropertySet xPSetButton = UnoRuntime.queryInterface(
            XPropertySet.class, buttonModel );
        xPSetButton.setPropertyValue( "PositionX", new Integer( 120 ) );
        xPSetButton.setPropertyValue( "PositionY", new Integer( 50 ) );
        xPSetButton.setPropertyValue( "Width", new Integer( 50 ) );
        xPSetButton.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetButton.setPropertyValue( "Name", _buttonName );
        xPSetButton.setPropertyValue( "TabIndex", new Short( (short)20 ) );
        xPSetButton.setPropertyValue( "Label", new String( "Generate" ) );
      
        // create a Cancel button model and set the properties
        Object cancelButtonModel = xMultiServiceFactory.createInstance(
            "com.sun.star.awt.UnoControlButtonModel" );
        XPropertySet xPSetCancelButton = UnoRuntime.queryInterface(
            XPropertySet.class, cancelButtonModel );
        xPSetCancelButton.setPropertyValue( "PositionX", new Integer( 180 ) );
        xPSetCancelButton.setPropertyValue( "PositionY", new Integer( 50 ) );
        xPSetCancelButton.setPropertyValue( "Width", new Integer( 50 ) );
        xPSetCancelButton.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetCancelButton.setPropertyValue( "Name", _cancelButtonName );
        xPSetCancelButton.setPropertyValue( "TabIndex", new Short( (short)40 ) );
        xPSetCancelButton.setPropertyValue( "PushButtonType", new Short( (short)2 ) );
        xPSetCancelButton.setPropertyValue( "Label", new String( "Cancel" ) );
        
        // insert the control models into the dialog model
        XNameContainer xNameCont = UnoRuntime.queryInterface(
            XNameContainer.class, dialogModel );
        xNameCont.insertByName( _labelName, labelModel );
        xNameCont.insertByName( _datafileName, dataFileModel );
        xNameCont.insertByName( _buttonName, buttonModel );
        xNameCont.insertByName( _cancelButtonName, cancelButtonModel );
      
        // create the dialog control and set the model
        dialog = xMultiComponentFactory.createInstanceWithContext(
            "com.sun.star.awt.UnoControlDialog", _xComponentContext );
        XControl xControl = UnoRuntime.queryInterface(
            XControl.class, dialog );
        XControlModel xControlModel = UnoRuntime.queryInterface(
            XControlModel.class, dialogModel );      
        xControl.setModel( xControlModel );

        // add an action listener to the button control
        XButton xButton = UnoRuntime.queryInterface(XButton.class, getControl(_buttonName));
        xButton.addActionListener(new StandardActionListener( this ));

        // create a peer
        Object toolkit = xMultiComponentFactory.createInstanceWithContext(
            "com.sun.star.awt.ExtToolkit", _xComponentContext );      
        XToolkit xToolkit = UnoRuntime.queryInterface(
            XToolkit.class, toolkit );
        XWindow xWindow = UnoRuntime.queryInterface(
            XWindow.class, xControl );
        xWindow.setVisible( false );
        xControl.createPeer( xToolkit, null );
      
        // execute the dialog
        xDialog = UnoRuntime.queryInterface(
            XDialog.class, dialog );
        xDialog.execute();
      
        // dispose the dialog
        XComponent xComponent = UnoRuntime.queryInterface(
            XComponent.class, dialog );
        xComponent.dispose();

        return reportRequired;
    }

    public void performOkAction(){
        XTextComponent xTextComponent = UnoRuntime.queryInterface(XTextComponent.class, getControl( _datafileName ));
        setting.setDataFilePath(xTextComponent.getText());
        reportRequired = true;
        this.xDialog.endExecute();
    }

    private Object getControl(String name){
        XControlContainer xControlCont = UnoRuntime.queryInterface(
            XControlContainer.class, dialog );
        return xControlCont.getControl( name );
    }
    
}
