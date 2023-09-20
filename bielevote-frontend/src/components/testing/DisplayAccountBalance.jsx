import { useAuth } from "../../misc/AuthContext";
import { useState } from "react";
import { backendApi } from "../../misc/ApiMappings";

export default function DisplayAccountBalance() {
  const { userIsAuthenticated, getUser } = useAuth();
  const [balance, setBalance] = useState(NaN);
  async function getBalance() {
    try {
      const res = await backendApi.getAccountBalance(getUser());
      setBalance(res.data);
    } catch (error) {
      setBalance(NaN);
    }
  }

  return userIsAuthenticated() ? (
    <div className="bg-slate-200 w-fit h-fit flex flex-col gap-2 p-2">
      <button className="bg-green-300 p-1" onClick={getBalance}>
        get balance
      </button>
      <div className="p-1">balance: {balance}</div>
    </div>
  ) : (
    <div />
  );
}
