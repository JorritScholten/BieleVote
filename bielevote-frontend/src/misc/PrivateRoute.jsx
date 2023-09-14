import PropTypes from "prop-types";
import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContext";

export default function PrivateRoute({ page, allowedAccountTypes }) {
  const { getAccountType } = useAuth();
  const accountType = getAccountType();
  return allowedAccountTypes.some((type) => type === accountType) ? (
    page
  ) : (
    <Navigate to="/" />
  );
}

PrivateRoute.propTypes = {
  page: PropTypes.any.isRequired,
  allowedAccountTypes: PropTypes.arrayOf(PropTypes.string.isRequired)
    .isRequired,
};
