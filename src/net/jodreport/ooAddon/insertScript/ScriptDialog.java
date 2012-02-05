package net.jodreport.ooAddon.insertScript;

import com.sun.star.awt.XButton;
import com.sun.star.awt.XCheckBox;
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

public class ScriptDialog {

    private static final String _textFieldName = "TextInput";
    private static final String _buttonName = "Button1";
    private static final String _checkboxName = "CheckBox";
    private static final String _cancelButtonName = "CancelButton";
    private static final String _labelName = "Label1";
    
    private final XComponentContext _xComponentContext;

    private XDialog xDialog;
    private Object dialog;

    private Script script;
    
    
    public ScriptDialog( XComponentContext xComponentContext) {
        _xComponentContext = xComponentContext;
    }

    /** method for creating a dialog at runtime
     */
    public Script createDialog() throws com.sun.star.uno.Exception {

        // get the service manager from the component context
        XMultiComponentFactory xMultiComponentFactory = _xComponentContext.getServiceManager();
        
        // create the dialog model and set the properties
        Object dialogModel = xMultiComponentFactory.createInstanceWithContext(
            "com.sun.star.awt.UnoControlDialogModel", _xComponentContext );
        XPropertySet xPSetDialog = ( XPropertySet )UnoRuntime.queryInterface(
            XPropertySet.class, dialogModel );      
        xPSetDialog.setPropertyValue( "PositionX", new Integer( 100 ) );
        xPSetDialog.setPropertyValue( "PositionY", new Integer( 100 ) );
        xPSetDialog.setPropertyValue( "Width", new Integer( 240 ) );
        xPSetDialog.setPropertyValue( "Height", new Integer( 170 ) );
        xPSetDialog.setPropertyValue( "Title", new String( "Insert JOOScript" ) );

        // get the service manager from the dialog model
        XMultiServiceFactory xMultiServiceFactory = ( XMultiServiceFactory )UnoRuntime.queryInterface(
            XMultiServiceFactory.class, dialogModel );

        // create the label model and set the properties
        Object labelModel = xMultiServiceFactory.createInstance("com.sun.star.awt.UnoControlFixedTextModel" );
        XPropertySet xPSetLabel = ( XPropertySet )UnoRuntime.queryInterface(
            XPropertySet.class, labelModel );
        xPSetLabel.setPropertyValue( "PositionX", new Integer( 10 ) );
        xPSetLabel.setPropertyValue( "PositionY", new Integer( 10 ) );
        xPSetLabel.setPropertyValue( "Width", new Integer( 100 ) );
        xPSetLabel.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetLabel.setPropertyValue( "Name", _labelName );
        xPSetLabel.setPropertyValue( "Label", "Please input JOOScript:" );

        Object oTFModel = xMultiServiceFactory.createInstance("com.sun.star.awt.UnoControlEditModel");
        // Set the properties at the model - keep in mind to pass the property names in alphabetical order!
        XPropertySet xFCModelMPSet = (XPropertySet) UnoRuntime.queryInterface(XPropertySet.class, oTFModel);
        xFCModelMPSet.setPropertyValue( "PositionX", new Integer( 10 ) );
        xFCModelMPSet.setPropertyValue( "PositionY", new Integer( 25 ) );
        xFCModelMPSet.setPropertyValue( "Width", new Integer( 220 ) );
        xFCModelMPSet.setPropertyValue( "Height", new Integer( 110 ) );
        xFCModelMPSet.setPropertyValue( "Name", _textFieldName );
        xFCModelMPSet.setPropertyValue( "TabIndex", new Short( (short)10 ) );
        xFCModelMPSet.setPropertyValue( "MultiLine", true );
        xFCModelMPSet.setPropertyValue( "AutoVScroll", true );
        xFCModelMPSet.setPropertyValue( "VScroll", true );

        // create the button model and set the properties
        Object checkboxModel = xMultiServiceFactory.createInstance(
            "com.sun.star.awt.UnoControlCheckBoxModel" );
        XPropertySet xPSetCheck = ( XPropertySet )UnoRuntime.queryInterface(
            XPropertySet.class, checkboxModel );
        xPSetCheck.setPropertyValue( "PositionX", new Integer( 10 ) );
        xPSetCheck.setPropertyValue( "PositionY", new Integer( 150 ) );
        xPSetCheck.setPropertyValue( "Width", new Integer( 100 ) );
        xPSetCheck.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetCheck.setPropertyValue( "Name", _checkboxName );
        xPSetCheck.setPropertyValue( "TabIndex", new Short( (short)20 ) );
        xPSetCheck.setPropertyValue( "Label", "Visiable" );
        xPSetCheck.setPropertyValue( "State", new Short((short) 1));

        // create the button model and set the properties
        Object buttonModel = xMultiServiceFactory.createInstance(
            "com.sun.star.awt.UnoControlButtonModel" );
        XPropertySet xPSetButton = ( XPropertySet )UnoRuntime.queryInterface(
            XPropertySet.class, buttonModel );
        xPSetButton.setPropertyValue( "PositionX", new Integer( 120 ) );
        xPSetButton.setPropertyValue( "PositionY", new Integer( 150 ) );
        xPSetButton.setPropertyValue( "Width", new Integer( 50 ) );
        xPSetButton.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetButton.setPropertyValue( "Name", _buttonName );
        xPSetButton.setPropertyValue( "TabIndex", new Short( (short)30 ) );
        xPSetButton.setPropertyValue( "Label", new String( "Insert" ) );
      
        // create a Cancel button model and set the properties
        Object cancelButtonModel = xMultiServiceFactory.createInstance(
            "com.sun.star.awt.UnoControlButtonModel" );
        XPropertySet xPSetCancelButton = ( XPropertySet )UnoRuntime.queryInterface(
            XPropertySet.class, cancelButtonModel );
        xPSetCancelButton.setPropertyValue( "PositionX", new Integer( 180 ) );
        xPSetCancelButton.setPropertyValue( "PositionY", new Integer( 150 ) );
        xPSetCancelButton.setPropertyValue( "Width", new Integer( 50 ) );
        xPSetCancelButton.setPropertyValue( "Height", new Integer( 14 ) );
        xPSetCancelButton.setPropertyValue( "Name", _cancelButtonName );
        xPSetCancelButton.setPropertyValue( "TabIndex", new Short( (short)40 ) );
        xPSetCancelButton.setPropertyValue( "PushButtonType", new Short( (short)2 ) );
        xPSetCancelButton.setPropertyValue( "Label", new String( "Cancel" ) );
        
        // insert the control models into the dialog model
        XNameContainer xNameCont = ( XNameContainer )UnoRuntime.queryInterface(
            XNameContainer.class, dialogModel );
        xNameCont.insertByName( _labelName, labelModel );
        xNameCont.insertByName( _textFieldName, oTFModel );
        xNameCont.insertByName( _checkboxName, checkboxModel );
        xNameCont.insertByName( _buttonName, buttonModel );
        xNameCont.insertByName( _cancelButtonName, cancelButtonModel );
      
        // create the dialog control and set the model
        dialog = xMultiComponentFactory.createInstanceWithContext(
            "com.sun.star.awt.UnoControlDialog", _xComponentContext );
        XControl xControl = ( XControl )UnoRuntime.queryInterface(
            XControl.class, dialog );
        XControlModel xControlModel = ( XControlModel )UnoRuntime.queryInterface(
            XControlModel.class, dialogModel );      
        xControl.setModel( xControlModel );

        // add an action listener to the button control
        XButton xButton = (XButton) UnoRuntime.queryInterface(XButton.class, getControl(_buttonName));
        xButton.addActionListener(new ScriptActionListener( this ));

        // create a peer
        Object toolkit = xMultiComponentFactory.createInstanceWithContext(
            "com.sun.star.awt.ExtToolkit", _xComponentContext );      
        XToolkit xToolkit = ( XToolkit )UnoRuntime.queryInterface(
            XToolkit.class, toolkit );
        XWindow xWindow = ( XWindow )UnoRuntime.queryInterface(
            XWindow.class, xControl );
        xWindow.setVisible( false );
        xControl.createPeer( xToolkit, null );
      
        // execute the dialog
        xDialog = ( XDialog )UnoRuntime.queryInterface(
            XDialog.class, dialog );
        xDialog.execute();
      
        // dispose the dialog
        XComponent xComponent = ( XComponent )UnoRuntime.queryInterface(
            XComponent.class, dialog );
        xComponent.dispose();

        return script;
    }

    public void performOkAction(){
        script = new Script();
        XTextComponent xTextComponent = (XTextComponent) UnoRuntime.queryInterface(XTextComponent.class, getControl( _textFieldName ));
        script.setValue(xTextComponent.getText());
        XCheckBox xCheckboxComponent = (XCheckBox) UnoRuntime.queryInterface(XCheckBox.class, getControl( _checkboxName ));
        script.setVisiable(xCheckboxComponent.getState()==1);
        this.xDialog.endExecute();
    }

    private Object getControl(String name){
        XControlContainer xControlCont = ( XControlContainer )UnoRuntime.queryInterface(
            XControlContainer.class, dialog );
        return xControlCont.getControl( name );
    }
    
}
