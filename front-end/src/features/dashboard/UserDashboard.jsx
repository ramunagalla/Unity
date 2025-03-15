import { useEffect, useState } from "react";
import Navbar from "../../components/Navbar"; // Include Navbar
import "../../css/UserDashboard.css"; // Dashboard styles

const UserDashboard = () => {
  // Placeholder JSON Data (To Be Replaced with API Data)
  const [user, setUser] = useState({
    name: "John Doe",
    totalBalance: 7500.0,
    accounts: [
      { type: "Checking", number: "123-456-7890", balance: 5000.0, status: "Active" },
      { type: "Savings", number: "987-654-3210", balance: 2500.0, status: "Active" }
    ],
    transactions: [
      { date: "03/14", type: "Deposit", amount: 500, status: "Completed" },
      { date: "03/12", type: "Transfer", amount: 100, status: "Pending" },
      { date: "03/10", type: "Withdrawal", amount: 200, status: "Completed" }
    ]
  });

  return (
    <div className="dashboard-container">
      <Navbar /> {/* Include the Navbar */}
      <div className="dashboard-content">
        <h1 className="dashboard-heading">Welcome, {user.name} 👋</h1>
        
        {/* Account Summary */}
        <div className="account-summary">
          <h2>Total Balance: <span>${user.totalBalance.toFixed(2)}</span></h2>
        </div>

        {/* Accounts Section */}
        <div className="accounts-section">
          <h2>🏦 Your Accounts</h2>
          <table className="account-table">
            <thead>
              <tr>
                <th>Account Type</th>
                <th>Account Number</th>
                <th>Balance</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {user.accounts.map((acc, index) => (
                <tr key={index}>
                  <td>{acc.type}</td>
                  <td>{acc.number}</td>
                  <td>${acc.balance.toFixed(2)}</td>
                  <td>{acc.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Transactions Section */}
        <div className="transactions-section">
          <h2>📜 Recent Transactions</h2>
          <table className="transaction-table">
            <thead>
              <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {user.transactions.map((txn, index) => (
                <tr key={index}>
                  <td>{txn.date}</td>
                  <td>{txn.type}</td>
                  <td>${txn.amount.toFixed(2)}</td>
                  <td>{txn.status}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Quick Actions */}
        <div className="quick-actions">
          <button className="dashboard-btn">🔄 Withdraw Money</button>
          <button className="dashboard-btn">💰 Deposit Money</button>
          <button className="dashboard-btn">🔁 Transfer Money</button>
        </div>
      </div>
    </div>
  );
};

export default UserDashboard;
