import PropTypes from "prop-types";
import { useEffect } from "react";
import LoginForm from "./LoginForm";
import { Link } from "react-router-dom";
import { useAuth } from "../misc/AuthContext";
import { Button } from "semantic-ui-react";

export default function Header({ pageTitle }) {
  // sets page title in browser
  useEffect(() => {
    document.title = pageTitle;
  }, [pageTitle]);
  const { userIsAuthenticated, getUsername, userLogout } = useAuth();

  return (
    <div className="p-2 gap-2 flex justify-between items-center bg-slate-300">
      <Link to={"/"} className="w-1/5 text-center text-4xl font-bold">
        BieleVote
      </Link>
      <h1 className="w-1/5 text-center text-3xl">{pageTitle}</h1>
      <div className="w-1/4 self-center">
        {userIsAuthenticated() ? (
          <Button.Group fluid size="small">
            <Button active className="w-3/4" content={getUsername()} />
            <Button negative onClick={() => userLogout()} content="Logout" />
          </Button.Group>
        ) : (
          <LoginForm />
        )}
      </div>
    </div>
  );
}

Header.propTypes = {
  pageTitle: PropTypes.string.isRequired,
};
