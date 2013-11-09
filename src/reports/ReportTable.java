package reports;

import hit_exceptions.InvalidRowException;
import hit_exceptions.NullContainerException;

import java.util.ArrayList;
//import java.util.List;

public class ReportTable {
	private String[] _headerRow;
	private ArrayList<TableRow> _rows;
	private int _numberOfColumns;
	
	public ReportTable(int numberOfColumns){
		_numberOfColumns = numberOfColumns;
	}
	
	public ReportTable(String[] headerRow){
		setHeaderRow(headerRow);
		_numberOfColumns = headerRow.length;
	}

	/**
	 * @return the _headerRow
	 */
	public String[] getHeaderRow() {
		return _headerRow;
	}

	/**
	 * @param _headerRow the _headerRow to set
	 */
	public void setHeaderRow(String[] _headerRow) {
		this._headerRow = _headerRow;
	}

	public ArrayList<TableRow> getRows(){
		return _rows;
	}
	
	public void addRow(TableRow tableRow) throws InvalidRowException{
		if(tableRow.getnumberOfColumns() == _numberOfColumns){
			_rows.add(tableRow);
		}else{
			throw new InvalidRowException("Incorrect number of columns!!!!");
		}
	}

}
