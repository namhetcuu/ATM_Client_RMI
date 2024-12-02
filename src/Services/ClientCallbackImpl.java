package Services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.SwingUtilities;

import client.FrmClient;

public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback, Serializable{

	private static final long serialVersionUID = 1L;
	
	private FrmClient client;

    public ClientCallbackImpl(FrmClient client) throws RemoteException {
        this.client = client;
    }
	
	@Override
	public void notifyBalanceChange(String accountNumber, BigDecimal newBalance) throws RemoteException {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(() -> {
	        client.displayMessage("Account " + accountNumber + "\n new balance: " + newBalance + "\n");
	    });
	}

	@Override
	public String getClientAccount(String accountNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return accountNumber;
	}

//    private FrmClient client;
//
//    public ClientCallbackImpl(FrmClient client) throws RemoteException {
//        this.client = client;
//    }
//
//    @Override
//    public void notifyBalanceChange(String accountNumber, double newBalance) throws RemoteException {
//        client.displayMessage("Account " + accountNumber + ", new balance: " + newBalance+"\n");
//    }

}
