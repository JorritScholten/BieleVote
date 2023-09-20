import PropTypes from "prop-types";
import { useEffect } from "react";
import LoginForm from "./LoginForm";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../misc/AuthContext";
import { Button, Icon, Popup } from "semantic-ui-react";
import NavBar from "./NavBar";

export default function Header({ pageTitle }) {
  // sets page title in browser
  useEffect(() => {
    document.title = pageTitle;
  }, [pageTitle]);
  const { userIsAuthenticated, getUsername, userLogout } = useAuth();
  const navigate = useNavigate();

  return (
    <div className="p-2 gap-2 flex justify-between items-center bg-slate-300">
      <div className="w-1/5 text-center flex flex-row ">
        <Popup
          content={<NavBar />}
          on="click"
          popper={{ id: "popper-container", style: { zIndex: 2000 } }}
          trigger={
            <Button>
              <Icon name="content" />
            </Button>
          }
        />
        <Link to={"/"} className="flex-auto text-4xl font-bold">
          BieleVote
        </Link>
      </div>
      <h1 className="w-1/5 text-center text-3xl">{pageTitle}</h1>
      <div className="w-1/4 self-center">
        {userIsAuthenticated() ? (
          <Button.Group fluid size="small">
            <Button
              active
              className="w-3/4"
              content={getUsername()}
              onClick={() => navigate("/settings")}
            />
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
