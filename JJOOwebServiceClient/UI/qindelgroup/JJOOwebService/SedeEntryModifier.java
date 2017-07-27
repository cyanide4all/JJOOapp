package qindelgroup.JJOOwebService;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SedeEntryModifier implements SelectionListener {

	private Display display;
	private Shell parentShell;
	private Shell shell;
	private int id;
	private int tipo;
	private ProgramUI master;
	
	public SedeEntryModifier(int id, int tipo, Display display,  Shell parent, ProgramUI master) {
		this.id = id;
		this.tipo = tipo;
		parentShell = parent;
		this.display = display;
		this.master = master;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		shell = new Shell(parentShell);
		shell.setText("Crear nueva entrada");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		shell.setLayout(gridLayout);
		
		renderForm();
		
		shell.pack();
		shell.open ();
		while (!parentShell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
	}

	private void renderForm() {
		Map<String, Integer> sedeData = RequestController.getSedeByAnyoTipoJJOO(id,tipo);
		Label labelAnyo = new Label(shell,SWT.NONE);
		labelAnyo.setText("Año");
		Text textAnyo = new Text(shell, SWT.READ_ONLY); 
		textAnyo.setText(Integer.toString(id));
		Label labelTipo = new Label(shell,SWT.NONE);
		labelTipo.setText("Tipo");
		Label labelTipoValue = new Label (shell, SWT.NONE);
		labelTipoValue.setText(RequestController.getDescripcionesJJOO()[sedeData.get("ID_TIPO_JJOO") -1]);
		Label labelCiudad = new Label(shell,SWT.NONE);
		labelCiudad.setText("Ciudad");
		Combo comboCiudad = new Combo(shell, SWT.NONE);
		comboCiudad.setItems(RequestController.getCiudades());
		comboCiudad.select(sedeData.get("SEDE")-1);
		Button botonAceptar = new Button(shell, SWT.NONE);
		botonAceptar.setText("Modificar");
				
		botonAceptar.addSelectionListener(new SelectionListener() {
		
			@Override
			public void widgetSelected(SelectionEvent arg0) {			
				int id_sede = comboCiudad.getSelectionIndex() +1; //necesario
				RequestController.updateSede(id, tipo, id_sede);
				master.redrawUI();
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
	}

}
