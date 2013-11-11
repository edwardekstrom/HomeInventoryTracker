package reports;

public class TableRow {
	private String[] _data;
	private int _numberOfColumns;
	
	
	public TableRow(int numberOfColumns){
		setData(new String[numberOfColumns]);
		setNumberOfColumns(numberOfColumns);
	}
	
	public TableRow(String[] theData){
		_data = theData;
		setNumberOfColumns(theData.length);
	}


	/**
	 * @return the data
	 */
	public String[] getData() {
		return _data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(String[] data) {
		this._data = data;
	}

	/**
	 * @return the _numberOfColumns
	 */
	public int getnumberOfColumns() {
		return _numberOfColumns;
	}

	/**
	 * @param _numberOfColumns the _numberOfColumns to set
	 */
	public void setNumberOfColumns(int numberOfColumns) {
		this._numberOfColumns = numberOfColumns;
	}

}
