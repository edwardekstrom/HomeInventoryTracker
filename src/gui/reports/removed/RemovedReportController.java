package gui.reports.removed;

import gui.common.*;


import builder.*;
import reports.*;
import model.*;
import singletons.Configuration;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Controller class for the removed items report view.
 */
public class RemovedReportController extends Controller implements
		IRemovedReportController {

	/**
	 * Constructor.
	 * 
	 * @param view Reference to the removed items report view
	 */
	public RemovedReportController(IView view) {
		super(view);

		construct();
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
	protected IRemovedReportView getView() {
		return (IRemovedReportView) super.getView();
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
		HomeInventory hi = Configuration.getHIT();
		model.Date d = hi.getLastRemovedItemsDate();
		boolean enableLast = d != null;
		getView().enableSinceLast(enableLast);
		getView().setSinceDate(!enableLast);
		getView().setSinceLast(enableLast);
		if(enableLast){
			getView().setSinceLastValue(d.getUtilDate());
		}

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
	 * removed items report view is changed by the user.
	 */
	@Override
	public void valuesChanged() {
	}

	/**
	 * This method is called when the user clicks the "OK"
	 * button in the removed items report view.
	 */
	@Override
	public void display() {

		model.Date date = null;
		if (getView().getSinceDate()){
			java.util.Date d = getView().getSinceDateValue();
			date = new model.Date(d);
		}
		else if(getView().getSinceLast()){
			HomeInventory hi = Configuration.getHIT();
			date =  hi.getLastRemovedItemsDate();
		}
		RemovedItemsReport report = new RemovedItemsReport(date);

		FileFormat format = getView().getFormat();
		if(format == FileFormat.HTML){
			report.generateReport(new HTMLBuilder());
		}else{
			report.generateReport(new PDFBuilder());
		}
		HomeInventory hi = Configuration.getHIT();
		hi.saveLastRemovedItemsDate(new model.Date(new java.util.Date()));
	}

}

