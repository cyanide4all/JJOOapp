import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ProgramUI {
	
	private Display display;
	private Shell shell;
	private ArrayList<HashMap<String,String>> data;
	private ArrayList<HashMap<String,String>> dataSedes;
	private TabFolder tabFolder;
	private TabItem home;
	private TabItem sedes;
	
	public ProgramUI(ArrayList<HashMap<String, String>> data,  ArrayList<HashMap<String,String>> datasedes) {
		this.display = new Display();
		this.shell = new Shell();
		shell.setText("JJOO app");
		this.data = data;
		this.dataSedes = datasedes;
	}
	
	public void DeployProgramUI() {
		GridLayout gridLayout = new GridLayout();
		shell.setLayout(gridLayout);
		tabFolder = new TabFolder(shell, SWT.BORDER);
		
		renderData();
		
		shell.pack();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}

	
	public void redrawUI() {
		int tabIndex = tabFolder.getSelectionIndex();
		Control[] toRedraw = tabFolder.getChildren();
		home.dispose();
		sedes.dispose();
		for(Control i : toRedraw) {
			i.dispose();
		}
		getNewData();
		renderData();
		tabFolder.setSelection(tabIndex);
	}
	
	private void getNewData() {
		//TODO Esto a un controlador, la UI no es su sitio
		MasterDAO daoTest = new MasterDAO();
		data = daoTest.getCiudadesCompleto();
		SedeDAO daoSedes = new SedeDAO();
		dataSedes = daoSedes.getSedes();		
	}

	private void renderData() {
		home = new TabItem(tabFolder, SWT.NULL);
	    home.setText("Home");
	    sedes = new TabItem(tabFolder, SWT.NULL);
	    sedes.setText("Sedes");
		renderHome();
		renderSedes();
	}

	//Render home tab
	private void renderHome() {
		Table table = new Table (tabFolder, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		home.setControl(table);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData gData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gData.heightHint = 300;
		table.setLayoutData(gData);
		String[] titles = {"ID_PAIS", "NOMBRE_PAIS", "ID_CIUDAD", "NOMBRE_CIUDAD", "VALOR", "DESCRIPCION_TIPO", "NUMERO_VECES_SEDE"};
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [i]);
		 }
		for(int i = 0; i < data.size(); i++) {
			TableItem nuevaLinea = new TableItem(table, SWT.NONE);
			nuevaLinea.setText(0,data.get(i).get("ID_PAIS"));
			nuevaLinea.setText(1,data.get(i).get("NOMBRE_PAIS"));
			nuevaLinea.setText(2,data.get(i).get("ID_CIUDAD"));
			nuevaLinea.setText(3,data.get(i).get("NOMBRE_CIUDAD"));
			nuevaLinea.setText(4,data.get(i).get("VALOR"));
			if(data.get(i).get("DESCRIPCION_TIPO")==null) {
				nuevaLinea.setText(5,"N/A");
			}else {
				nuevaLinea.setText(5,data.get(i).get("DESCRIPCION_TIPO"));
			}
			nuevaLinea.setText(6,data.get(i).get("NUMERO_VECES_SEDE"));
		}
		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
			}
	}
	
	//Render sedes tab
	private void renderSedes() {
		Composite tab2 = new Composite(tabFolder, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		tab2.setLayout(gridLayout);
		sedes.setControl(tab2);
		
		Button botonNuevo = new Button(tab2, SWT.PUSH);
		botonNuevo.setText("Nueva entrada");
		botonNuevo.computeSize(SWT.DEFAULT, 200);
		botonNuevo.addSelectionListener(new SedeEntryCreator(display, shell, this));
		
		Table table = new Table (tab2, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData gData = new GridData(SWT.FILL, SWT.FILL, true, true);
		table.setLayoutData(gData);
		String[] titles = {"NOMBRE_CIUDAD", "AÑO", "DESCRIPCION_TIPO","Modificar","Eliminar"};
		
		
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [i]);
		}
		//Llenamos las lineas con datos y botones
		for(int i = 0; i < dataSedes.size(); i++) {
			TableItem nuevaLinea = new TableItem(table, SWT.NONE);
			int anyoAux = Integer.parseInt(dataSedes.get(i).get("ANYO"));
			int tipoAux = Integer.parseInt(dataSedes.get(i).get("ID_TIPO_JJOO"));
			nuevaLinea.setText(0,dataSedes.get(i).get("NOMBRE_CIUDAD"));
			nuevaLinea.setText(1,dataSedes.get(i).get("ANYO"));
			nuevaLinea.setText(2,dataSedes.get(i).get("DESCRIPCION_TIPO"));
			
			TableEditor modificar = new TableEditor(table);
			Button botonModificar = new Button(table, SWT.PUSH);
			botonModificar.setText("Edit");
			botonModificar.computeSize(SWT.DEFAULT, table.getItemHeight());
			botonModificar.addSelectionListener(new SedeEntryModifier(anyoAux, tipoAux, display, shell, this));
			modificar.grabHorizontal = true;
			modificar.setEditor(botonModificar, nuevaLinea, 3);
			
			
			TableEditor eliminar = new TableEditor(table);
			Button botonEliminar = new Button(table, SWT.PUSH);
			botonEliminar.setText("X");
			botonEliminar.computeSize(SWT.DEFAULT, table.getItemHeight());
			botonEliminar.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					//TODO mover esto a un controlador para hacer cosas de DAOs
					//TODO actualizar tabla
					SedeDAO sedeDAO =  new SedeDAO();
					sedeDAO.deleteSede(anyoAux, tipoAux);
					redrawUI();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			eliminar.grabHorizontal = true;
			eliminar.setEditor(botonEliminar, nuevaLinea, 4);
		}
		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}
	}
}
