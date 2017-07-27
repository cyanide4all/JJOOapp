import java.util.Observable;

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

public class SedeEntryCreator extends Observable implements SelectionListener {

	private Display display;
	private Shell parentShell;
	private Shell shell;
	private ProgramUI master;

	public SedeEntryCreator(Display display, Shell parent, ProgramUI master) {
		parentShell = parent;
		this.display = display;
		this.master = master;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		//No es necesario
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
		Label labelAnyo = new Label(shell,SWT.NONE);
		labelAnyo.setText("Año");
		Text textAnyo = new Text(shell, SWT.FILL); 
		Label labelTipo = new Label(shell,SWT.NONE);
		labelTipo.setText("Tipo");
		Combo comboTipo = new Combo(shell, SWT.NONE);
		TipoJJOODAO tipoJJOODAO = new TipoJJOODAO();
		comboTipo.setItems(	tipoJJOODAO.getDescripciones());
		Label labelCiudad = new Label(shell,SWT.NONE);
		labelCiudad.setText("Ciudad");
		Combo comboCiudad = new Combo(shell, SWT.NONE);
		CiudadDAO ciudadDAO = new CiudadDAO();
		comboCiudad.setItems(ciudadDAO.getNombres());
		Button botonAceptar = new Button(shell, SWT.NONE);
		botonAceptar.setText("Crear");
		botonAceptar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//TODO mover esto a un controlador que haga uso de los DAOs
				//TODO Actualizar tabla
				int anyo = Integer.parseInt(textAnyo.getText());
				int id_tipo = comboTipo.getSelectionIndex() +1; //+1 porque estan ordenados por ID con offset -1
				int id_sede = comboCiudad.getSelectionIndex() +1; //idem
				SedeDAO sedeDAO = new SedeDAO();
				sedeDAO.insertNuevaSede(anyo, id_tipo, id_sede);
				master.redrawUI();
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
	}

}
