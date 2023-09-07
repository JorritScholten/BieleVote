import PropTypes from "prop-types";
import { useEffect } from "react";

export default function Header({ pageTitle }) {
  // sets page title in browser
  useEffect(() => {
    document.title = pageTitle;
  }, []);

  return (
    <>
      <div className="p-10 flex justify-between items-center bg-slate-200">
        <div>BieleVote</div>
        <div>
          <h1>{pageTitle}</h1>
        </div>
        <div className="max-w-sm">
          <form>
            <div className="flex flex-row">
            <input type="text" placeholder="username"/>
            <input type="text" placeholder="password" />
            </div>
            <div className="flex justify-end gap-2">
            <input className="rounded-lg border-2 border-black" type="submit" value="Log in"></input>
            <input className="rounded-lg border-2 border-black" type="submit" value="Register"></input>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

Header.propTypes = {
  pageTitle: PropTypes.string.isRequired,
};
