import PropTypes from "prop-types";
import { useState, useEffect, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button, Icon, Popup } from "semantic-ui-react";
import { BiBug } from "react-icons/bi";

import { backendApi } from "../misc/ApiMappings";
import NavBar from "./NavBar";
import LoginForm from "./LoginForm";
import { useAuth } from "../misc/AuthContext";
import BalanceContext from "../misc/BalanceContext";

export default function Header({ pageTitle }) {
  const { userIsAuthenticated, getUsername, userLogout } = useAuth();
  const navigate = useNavigate();

  const balance = useContext(BalanceContext);

  // sets page title in browser
  useEffect(() => {
    document.title = pageTitle;
  }, [pageTitle]);

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
      <h1 className="w-1/5 grow text-center text-3xl">{pageTitle}</h1>
      <div className="w-1/4 self-center">
        {userIsAuthenticated() ? (
          <Button.Group fluid size="small">
            <Button
              className="w-3/5"
              content={getUsername()}
              onClick={() => navigate("/settings")}
            />
            <Button active compact>
              {balance}{" "}
              <Icon>
                <BiBug />
              </Icon>
            </Button>
            <Button
              negative
              compact
              onClick={() => userLogout()}
              content="Logout"
            />
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
