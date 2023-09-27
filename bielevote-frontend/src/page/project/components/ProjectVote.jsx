import PropTypes from "prop-types";
import { Button } from "semantic-ui-react";
import { HttpStatusCode } from "axios";

import { useAuth } from "../../../misc/AuthContext";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useEffect, useState } from "react";
import { voteTypes } from "../../../misc/ApiForms";
import { useContext } from "react";
import BalanceContext from "../../../misc/BalanceContext";

export default function ProjectVote({ projectId, updateVersion }) {
  const { userIsAuthenticated, getUser } = useAuth();
  const [hasAlreadyVoted, setHasAlreadyVoted] = useState(true);

  useEffect(() => {
    async function getVotingStatus() {
      try {
        if (userIsAuthenticated()) {
          const response = await backendApi.getHasVoted(projectId, getUser());
          setHasAlreadyVoted(response.data);
        }
      } catch (error) {
        handleLogError(error);
      }
    }
    getVotingStatus();
  }, [projectId, getUser, userIsAuthenticated]);

  async function castVote(voteType) {
    setHasAlreadyVoted(true);
    try {
      const response = await backendApi.postVote(
        voteType,
        projectId,
        getUser()
      );
      if (response.status !== HttpStatusCode.Created) {
        setHasAlreadyVoted(false);
      } else {
        updateVersion((version) => (version = version + 1));
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
        <div hidden />
      )}
    </div>
  );
}

ProjectVote.propTypes = {
  projectId: PropTypes.string.isRequired,
  updateVersion: PropTypes.func,
};
