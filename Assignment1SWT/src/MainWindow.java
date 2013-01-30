import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.wb.swt.SWTResourceManager;

public class MainWindow
{
    protected Shell shell;
    private AbstractShape shape;

    /**
     * Launch the application.
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            MainWindow window = new MainWindow();
            window.open();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Open the window.
     */
    public void open()
    {
        Display display = Display.getDefault();
        createContents();
        shell.open();
        shell.layout();

        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
    }

    /**
     * Create contents of the window.
     */
    protected void createContents()
    {
        shell = new Shell();
        shell.setSize(400, 310);
        shell.setText("SWT Application");
        shell.setLayout(new FormLayout());

        final Canvas canvas = new Canvas(shell, SWT.NONE);
        Listener listener = new Listener()
        {
            public void handleEvent(Event event)
            {
                switch (event.type)
                {
                case SWT.MouseDown:
                    System.out.println("down:" + event);
                    shape.moveShape(event.x, event.y, shape);
                    canvas.redraw();

                    break;
                }
            }
        };

        canvas.addListener(SWT.MouseDown, listener);
        canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

        FormData fd_canvas = new FormData();
        fd_canvas.bottom = new FormAttachment(0, 227);
        fd_canvas.right = new FormAttachment(100, -10);
        fd_canvas.top = new FormAttachment(0, 10);
        fd_canvas.left = new FormAttachment(0, 10);
        canvas.setLayoutData(fd_canvas);

        Button btnCircle = new Button(shell, SWT.NONE);
        btnCircle.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    if ((shape != null) && shape.shapeDrawn(true))
                    {
                        shape.eraseShape(canvas, shape);
                    }

                    shape = new Circle();
                    canvas.addPaintListener(shape);
                    canvas.redraw();
                }
            });

        FormData fd_btnNewButton = new FormData();
        fd_btnNewButton.top = new FormAttachment(canvas, 6);
        fd_btnNewButton.left = new FormAttachment(canvas, 0, SWT.LEFT);
        btnCircle.setLayoutData(fd_btnNewButton);
        btnCircle.setText("Create Circle");

        Button btnSquare = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton_1 = new FormData();
        fd_btnNewButton_1.top = new FormAttachment(canvas, 6);
        fd_btnNewButton_1.left = new FormAttachment(btnCircle, 6);
        btnSquare.setLayoutData(fd_btnNewButton_1);
        btnSquare.setText("Create Square");

        btnSquare.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    if ((shape != null) && shape.shapeDrawn(true))
                    {
                        shape.eraseShape(canvas, shape);
                    }

                    shape = new Square();
                    canvas.addPaintListener(shape);
                    canvas.redraw();
                }
            });

        Button btnIncrease = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton_2 = new FormData();
        fd_btnNewButton_2.top = new FormAttachment(canvas, 6);
        fd_btnNewButton_2.left = new FormAttachment(btnSquare, 6);
        btnIncrease.setLayoutData(fd_btnNewButton_2);
        btnIncrease.setText("Increase Size");

        btnIncrease.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    shape.increaseSize(shape);
                    canvas.redraw();
                }
            });

        Button btnDecrease = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton_3 = new FormData();
        fd_btnNewButton_3.top = new FormAttachment(canvas, 6);
        fd_btnNewButton_3.left = new FormAttachment(btnIncrease, 6);
        btnDecrease.setLayoutData(fd_btnNewButton_3);
        btnDecrease.setText("Decrease Size");

        btnDecrease.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    shape.decreaseSize(shape);
                    canvas.redraw();
                }
            });

        Button btnErase = new Button(shell, SWT.NONE);
        FormData fd_btnNewButton_4 = new FormData();
        fd_btnNewButton_4.bottom = new FormAttachment(btnCircle, 0, SWT.BOTTOM);
        fd_btnNewButton_4.left = new FormAttachment(btnDecrease, 6);
        btnErase.setLayoutData(fd_btnNewButton_4);
        btnErase.setText("Erase");

        btnErase.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    shape.eraseShape(canvas, shape);
                    canvas.redraw();
                }
            });

        Button btnSave = new Button(shell, SWT.NONE);
        FormData fd_btnSave = new FormData();
        fd_btnSave.top = new FormAttachment(btnSquare, 3);
        fd_btnSave.right = new FormAttachment(btnSquare, 0, SWT.RIGHT);
        btnSave.setLayoutData(fd_btnSave);
        btnSave.setText("Save");

        btnSave.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    shape.save(shape);                  
                    shape.eraseShape(canvas, shape);
                    shape.printMessage(shape);
                    
                    canvas.redraw();
                }
            });

        Button btnDrawAll = new Button(shell, SWT.NONE);
        btnDrawAll.addSelectionListener(new SelectionAdapter()
            {
                @Override
                public void widgetSelected(SelectionEvent e)
                {
                    for (AbstractShape shape : AbstractShape.drawAll())
                    {
                        canvas.addPaintListener(shape);
                    }

                    canvas.redraw();
                }
            });

        FormData fdDrawAll = new FormData();
        fdDrawAll.bottom = new FormAttachment(btnSave, 0, SWT.BOTTOM);
        fdDrawAll.left = new FormAttachment(btnIncrease, 0, SWT.LEFT);
        btnDrawAll.setLayoutData(fdDrawAll);
        btnDrawAll.setText("Draw All");
    }
}
