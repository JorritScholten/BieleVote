import PropTypes from "prop-types";
import { useEffect } from "react";
import LoginForm from "../page/login/components/LoginForm";
import { Link } from "react-router-dom";

export default function Header({ pageTitle }) {
  // sets page title in browser
  useEffect(() => {
    document.title = pageTitle;
  }, [pageTitle]);

  return (
    <div className="p-2 gap-2 flex justify-between items-center bg-slate-200">
      <Link to={"/"} className="w-1/5 text-center text-4xl">
        BieleVote
      </Link>
      <h1 className="w-1/5 text-center text-4xl">{pageTitle}</h1>
      <div className="w-1/4 self-center">
        <LoginForm />
      </div>
    </div>
  );
}

Header.propTypes = {
  pageTitle: PropTypes.string.isRequired,
};
