package fetchOptionsChain;

import com.ib.client.CommissionReport;
import com.ib.client.Contract;
import com.ib.client.ContractDetails;
import com.ib.client.EWrapper;
import com.ib.client.Execution;
import com.ib.client.Order;
import com.ib.client.OrderState;
import com.ib.client.TickType;
import com.ib.client.UnderComp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class RequestOptionChainWrapper implements EWrapper{
    private final Connection sqlConnection;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public int counter_iter;

    public RequestOptionChainWrapper(Connection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public double outputSQLDouble(ResultSet resultSet, String output_field) throws SQLException {
        Double output_value = -1.0;
        while (resultSet.next()) {
            output_value = resultSet.getDouble(output_field);
        }
        return output_value;
    }

    public int outputSQLInt(ResultSet resultSet, String output_field) throws SQLException {
        int output_value = -1;
        while (resultSet.next()) {
            output_value = resultSet.getInt(output_field);
        }
        return output_value;
    }


    public void contractDetails(int reqId, ContractDetails contractDetails) {
		try {
			Contract contract = contractDetails.m_summary;

		    // write to MasterChainList
            preparedStatement = sqlConnection.prepareStatement("INSERT INTO "
                + "IBAlgoSystem.MasterChainList VALUES (default, 'T', '"
                + contract.m_symbol + "', '" + contract.m_secType + "', '"
                + contract.m_exchange + "', '" + contract.m_currency + "', '"
                + contract.m_expiry + "', "
                + Double.toString(contract.m_strike) + ", '"
                + contract.m_right + "', '" + contract.m_multiplier
                + "', 'F', 'OTM');");
            preparedStatement.executeUpdate();
		
        } catch (Exception e) {
			e.printStackTrace();
	    }
    }

    public void contractDetailsEnd(int reqId) {
		try {
			// update counter
            statement = sqlConnection.createStatement();
            resultSet = statement.executeQuery(
                "SELECT counter FROM misc.counter;");
            counter_iter = outputSQLInt(resultSet, "counter");

            counter_iter++;

            preparedStatement = sqlConnection.prepareStatement(
                "UPDATE misc.counter SET counter = "
                + Integer.toString(counter_iter) + ";");
            preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
	    }
    }

    public void tickPrice(int tickerId, int field, double price, 
        int canAutoExecute) {
    }
    
    public void execDetails(int reqId, Contract contract, Execution execution) {
    }
    
    public void bondContractDetails(int reqId, ContractDetails contractDetails) {
    }

    public void fundamentalData(int reqId, String data) {
    }

    public void bondContractDetails(ContractDetails contractDetails) {
    }

    public void currentTime(long time) {
    }

    public void displayGroupList(int requestId, String contraftInfo) {
    }

    public void displayGroupUpdated(int requestId, String contractInfo) {
    }

    public void verifyCompleted(boolean completed, String contractInfo) {
    }
    
    public void verifyMessageAPI(String message) {
    }
    
    public void execDetailsEnd(int reqId) {
    }

    public void historicalData(int reqId, String date, double open, double high, 
        double low, double close, int volume, int count, double WAP, 
        boolean hasGaps) {
    }

    public void managedAccounts(String accountsList) {
    }

    public void commissionReport(CommissionReport cr) {
    }

    public void position(String account, Contract contract, int pos, 
        double avgCost) {
    }

    public void positionEnd() {
    }

    public void accountSummary(int reqId, String account, String tag, 
        String value, String currency) {
    }

    public void accountSummaryEnd(int reqId) {
    }

    public void accountDownloadEnd(String accountName) {
    }

    public void openOrder(int orderId, Contract contract, Order order, 
        OrderState orderState) {
    }

    public void openOrderEnd() {
    }

    public void orderStatus(int orderId, String status, int filled, 
        int remaining, double avgFillPrice, int permId, int parentId, 
        double lastFillPrice, int clientId, String whyHeld) {
    }

    public void receiveFA (int faDataType, String xml) {
    }

    public void scannerData(int reqId, int rank, ContractDetails contractDetails, 
        String distance, String benchmark, String projection, String legsStr) {
    }

    public void scannerDataEnd(int reqId) {
    }

    public void scannerParameters(String xml) {
    }

    public void tickEFP(int symbolId, int tickType, double basisPoints, 
        String formattedBasisPoints, double impliedFuture, int holdDays, 
        String futureExpiry, double dividendImpact, double dividendsToExpiry) {
    }

    public void tickGeneric(int symbolId, int tickType, double value) {
    }

    public void tickOptionComputation( int tickerId, int field, 
        double impliedVol, double delta, double optPrice, double pvDividend, 
        double gamma, double vega, double theta, double undPrice) {
    }

    public void deltaNeutralValidation(int reqId, UnderComp underComp) {
    }

    public void updateAccountTime(String timeStamp) {
    }

    public void updateAccountValue(String key, String value, String currency, 
        String accountName) {
    }

    public void updateMktDepth(int symbolId, int position, int operation, 
        int side, double price, int size) {
    }

    public void updateMktDepthL2(int symbolId, int position, String marketMaker, 
        int operation, int side, double price, int size) {
    }

    public void updateNewsBulletin(int msgId, int msgType, String message, 
        String origExchange) {
    }

    public void updatePortfolio(Contract contract, int position, 
        double marketPrice, double marketValue, double averageCost, 
        double unrealizedPNL, double realizedPNL, String accountName) {
    }

    public void marketDataType(int reqId, int marketDataType) {
    }

    public void tickSnapshotEnd(int tickerId) {
    }

    public void connectionClosed() {
    }

    public void realtimeBar(int reqId, long time, double open, double high, 
        double low, double close, long volume, double wap, int count) {
    }

    public void tickSize(int orderId, int field, int size) {
    }

    public void tickString(int orderId, int tickType, String value) {
    }

    public void error(Exception e) {
        e.printStackTrace ();
    }

    public void error(String str) {
        System.err.println (str);
    }

    public void error(int id, int errorCode, String errorMsg) {
        System.err.println ("error: " + id + "," + errorCode + "," + errorMsg);
    }
    
    public void nextValidId (int orderId) {
    }
}