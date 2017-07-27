package qindelgroup.JJOOwebService;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JjoOwebServiceApplication {

	static ConfigurableApplicationContext appCtx;
	
	public static void main(String[] args) {
		
		appCtx = SpringApplication.run(JjoOwebServiceApplication.class, args);
		Display display = new Display();
		Shell shell = new Shell();
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		shell.setText("JJOO REST service controller");
		shell.setMinimumSize(400, 0);
		Button button = new Button(shell, SWT.CENTER);
		shell.setLayout(layout);
		button.setText("STOP");
		Button button2 = new Button(shell, SWT.CENTER);
		button2.setText("RUN");
		button2.setEnabled(false);
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				appCtx.close();
				button2.setEnabled(true);
				button.setEnabled(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		button2.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				appCtx = SpringApplication.run(JjoOwebServiceApplication.class, args);
				button.setEnabled(true);
				button2.setEnabled(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		shell.addListener(SWT.Close, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				appCtx.close();				
			}
		});
		
		shell.pack();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
