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
        let form = emptyForms.checkIfUserHasVoted;
        form.projectId = projectId;
        console.log(form);
        const response = await backendApi.getHasVoted(form, getUser());
        console.log(response.data);
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
          <Button color="green" disabled={hasAlreadyVoted}>
            For
          </Button>
          <Button color="grey" disabled={hasAlreadyVoted}>
            Neutral
          </Button>
          <Button color="red" disabled={hasAlreadyVoted}>
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
  projectId: PropTypes.number.isRequired,
};
