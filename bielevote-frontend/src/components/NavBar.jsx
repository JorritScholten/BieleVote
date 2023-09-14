import PropTypes from "prop-types";
import { navList } from "../misc/NavMappings";
import { useAuth } from "../misc/AuthContext";
import { Link } from "react-router-dom";

export default function NavBar() {
  const { getAccountType } = useAuth();

  return (
    <div className="flex flex-col gap-2 ">
      {navList
        .filter((nav) => !nav.hideFromMenu)
        .filter((nav) =>
          nav.allowedAccountTypes.some((type) => type === getAccountType())
        )
        .map((nav) => (
          <Link to={nav.path} key={nav.path}>
            {nav.name}
          </Link>
        ))}
    </div>
  );
}

NavBar.propTypes = {};
