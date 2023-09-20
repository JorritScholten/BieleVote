import PropTypes from "prop-types";
import { Button } from "semantic-ui-react";

import { useAuth } from "../../../misc/AuthContext";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useEffect, useState } from "react";
import { voteTypes } from "../../../misc/ApiForms";
import { HttpStatusCode } from "axios";

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

  async function castVote(voteType) {
    setHasAlreadyVoted(true);
    try {
      const response = await backendApi.postVote(
        voteType,
        projectId,
        getUser()
      );
      console.log(response);
      if (response.status !== HttpStatusCode.Created) {
        setHasAlreadyVoted(false);
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <div>
      {userIsAuthenticated() ? (
        <Button.Group widths={3}>
          <Button
            positive
            disabled={hasAlreadyVoted}
            onClick={() => castVote(voteTypes.for)}
          >
            For
          </Button>
          <Button
            disabled={hasAlreadyVoted}
            onClick={() => castVote(voteTypes.neutral)}
          >
            Neutral
          </Button>
          <Button
            negative
            disabled={hasAlreadyVoted}
            onClick={() => castVote(voteTypes.against)}
          >
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
