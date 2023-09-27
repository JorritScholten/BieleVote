import { createContext, useContext, useEffect, useState } from "react";

import { backendApi } from "./ApiMappings";
import PropTypes from "prop-types";
import { useAuth } from "./AuthContext";

const BalanceContext = createContext();

function BalanceProvider({ children }) {
  const { getUser } = useAuth();
  const [balance, setBalance] = useState(NaN);
  const [version, setVersion] = useState(0);

  useEffect(() => {
    async function getBalance() {
      try {
        const res = await backendApi.getAccountBalance(getUser());
        setBalance(res.data);
      } catch (error) {
        setBalance(NaN);
      }
    }
    getBalance();
  }, [getUser, version]);

  return (
    <BalanceContext.Provider value={balance}>
      {children}
    </BalanceContext.Provider>
  );
}

export default BalanceContext;

export { BalanceProvider };

BalanceProvider.propTypes = {
  children: PropTypes.any,
};
