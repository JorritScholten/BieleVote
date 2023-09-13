import PropTypes from "prop-types";
import { navList } from "../misc/NavMappings";
import { useAuth } from "../misc/AuthContext";
import { Link } from "react-router-dom";

export default function NavBar() {
  const { userIsAuthenticated, getAccountType } = useAuth();

  return (
    <div className="flex flex-col gap-2">
      {navList
        .filter((nav) => !nav.hideFromMenu)
        .map((nav) => (
          <Link to={nav.path} key={nav.id}>
            {nav.name}
          </Link>
        ))}
    </div>
  );
}

NavBar.propTypes = {};
