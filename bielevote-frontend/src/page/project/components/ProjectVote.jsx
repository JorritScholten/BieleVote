import { Button } from "semantic-ui-react";

import { useAuth } from "../../../misc/AuthContext";

export default function ProjectVote() {
  const { userIsAuthenticated } = useAuth();

  return (
    <div>
      {userIsAuthenticated() ? (
        <>
          <Button className="ui green button ">For</Button>
          <Button className="ui teal button">Neutral</Button>
          <Button className="ui red button">Against</Button>
        </>
      ) : (
        <div />
      )}
    </div>
  );
}
