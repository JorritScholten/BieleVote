import PropTypes from "prop-types";
import { useEffect } from "react";

export default function Header({ pageTitle }) {
  // sets page title in browser
  useEffect(() => {
    document.title = pageTitle;
  }, []);

  return (
    <div>
      <h1>{pageTitle}</h1>
    </div>
  );
}

Header.propTypes = {
  pageTitle: PropTypes.string.isRequired,
};
