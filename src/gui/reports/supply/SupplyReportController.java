package gui.reports.supply;

import builder.HTMLBuilder;
import builder.PDFBuilder;
import builder.ReportBuilder;
import reports.NMonthSupplyReport;
import visitor.NMonthSupplyVisitor;
import gui.common.*;

/**
 * Controller class for the N-month supply report view.
 */
	public class SupplyReportController extends Controller implements
		ISupplyReportController {
/*---	STUDENT-INCLUDE-BEGIN

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the N-month supply report view
	 */	
	public SupplyReportController(IView view) {
		super(view);
		
		construct();
		getView().enableOK(false);

	}

	//
	// Controller overrides
	//
	
	/**
	 * Returns a reference to the view for this controller.
	 * 
	 * {@pre None}
	 * 
	 * {@post Returns a reference to the view for this controller.}
	 */
	@Override
	protected ISupplyReportView getView() {
		return (ISupplyReportView)super.getView();
	}

	/**
	 * Sets the enable/disable state of all components in the controller's view.
	 * A component should be enabled only if the user is currently
	 * allowed to interact with that component.
	 * 
	 * {@pre None}
	 * 
	 * {@post The enable/disable state of all components in the controller's view
	 * have been set appropriately.}
	 */
	@Override
	protected void enableComponents() {
	}

	/**
	 * Loads data into the controller's view.
	 * 
	 *  {@pre None}
	 *  
	 *  {@post The controller has loaded data into its view}
	 */
	@Override
	protected void loadValues() {
	}

	//
	// IExpiredReportController overrides
	//

	/**
	 * This method is called when any of the fields in the
	 * N-month supply report view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
		try{
			int n = Integer.parseInt(getView().getMonths());
			if (n > 0){
				getView().enableOK(true);
			}else{
				getView().enableOK(false);
			}

		}catch(NumberFormatException e){
			getView().enableOK(false);
		}
	}
	
	/**
	 * This method is called when the user clicks the "OK"
	 * button in the N-month supply report view.
	 */
	@Override
	public void display() {
		int n = 0;
		try{
			n = Integer.parseInt(getView().getMonths());
			if (n <= 0){
				n = -1;
			}else{
				NMonthSupplyReport report = new NMonthSupplyReport(n);
				ReportBuilder builder;
				if(getView().getFormat() == FileFormat.PDF){
					builder = new PDFBuilder();
				}else{
					builder = new HTMLBuilder();
				}
				report.generateReport(builder);
			
			}
		}catch(NumberFormatException e){
			n = -1;
		}
		
	}

}

