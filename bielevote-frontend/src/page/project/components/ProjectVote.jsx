import PropTypes from "prop-types";
import { Button } from "semantic-ui-react";

import { useAuth } from "../../../misc/AuthContext";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useEffect, useState } from "react";
import { emptyForms } from "../../../misc/ApiForms";

export default function ProjectVote({ projectId }) {
  const { userIsAuthenticated, getUser } = useAuth();
  const [hasAlreadyVoted, setHasAlreadyVoted] = useState(true);
  useEffect(() => {
    async function getVotingStatus() {
      try {
        const response = await backendApi.getHasVoted(projectId, getUser());
        setHasAlreadyVoted(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    getVotingStatus();
  }, [projectId, getUser]);

  return (
    <div>
      {userIsAuthenticated() ? (
        <Button.Group widths={3}>
          <Button positive disabled={hasAlreadyVoted}>
            For
          </Button>
          <Button disabled={hasAlreadyVoted}>Neutral</Button>
          <Button negative disabled={hasAlreadyVoted}>
            Against
          </Button>
        </Button.Group>
      ) : (
        <div />
      )}
    </div>
  );
}

ProjectVote.propTypes = {
  projectId: PropTypes.string.isRequired,
};
