import { createContext, useContext, useState, useEffect } from "react";
import PropTypes from "prop-types";
import { accountType } from "./NavMappings";

const AuthContext = createContext();

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(sessionStorage.getItem("user"));
    setUser(storedUser);
  }, []);

  const getUser = () => {
    return JSON.parse(sessionStorage.getItem("user"));
  };

  const getUsername = () => {
    let storedUser = sessionStorage.getItem("user");
    if (!storedUser) {
      return false;
    }
    storedUser = JSON.parse(storedUser);
    return storedUser.data.username;
  };

  const userIsAuthenticated = () => {
    let storedUser = sessionStorage.getItem("user");
    if (!storedUser) {
      return false;
    }
    storedUser = JSON.parse(storedUser);

    // if user has token expired, logout user
    if (Date.now() > storedUser.data.exp * 1000) {
      userLogout();
      return false;
    }
    return true;
  };

  const userLogin = (user) => {
    sessionStorage.setItem("user", JSON.stringify(user));
    setUser(user);
  };

  const userLogout = () => {
    sessionStorage.removeItem("user");
    setUser(null);
  };

  const getAccountType = () => {
    const storedUser = sessionStorage.getItem("user");
    if (!storedUser) {
      return accountType.visitor;
    }
    return JSON.parse(storedUser).data.accountType;
  };

  const contextValue = {
    user,
    getUser,
    getUsername,
    userIsAuthenticated,
    userLogin,
    userLogout,
    getAccountType,
  };

  return (
    <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>
  );
}

AuthProvider.propTypes = {
  children: PropTypes.any,
};

export default AuthContext;

export function useAuth() {
  return useContext(AuthContext);
}

export { AuthProvider };
