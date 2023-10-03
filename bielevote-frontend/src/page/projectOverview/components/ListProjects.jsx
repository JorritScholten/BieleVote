import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { Icon, Item } from "semantic-ui-react";

import { formatDate } from "../../../components/Utils";

export default function ListProjects({ projectsList, hideStatus = false }) {
  return projectsList.projects == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group relaxed>
      {projectsList.projects.map((project) => (
        <Item key={project.id}>
          <Item.Content>
            <Item.Header>
              <Link to={"/projects/" + project.id}>{project.title}</Link>
            </Item.Header>
            <Item.Meta>
              <Icon name="calendar alternate" />{" "}
              {formatDate(project.datePublished)}
            </Item.Meta>
            <Item.Meta>
              <Icon name="user" /> {project.author.legalName}
            </Item.Meta>
            <Item.Description>
              {hideStatus ? shortenSummary(project.summary) : project.summary}
            </Item.Description>
            <Item.Extra className={hideStatus ? "!hidden" : ""}>
              Status: {project.status}
            </Item.Extra>
          </Item.Content>
        </Item>
      ))}
    </Item.Group>
  );
}

function shortenSummary(summary) {
  const cutoffLength = 140;
  const cutoffMargin = 10;
  if (summary.length > cutoffLength) {
    const spaceIndex = summary.indexOf(" ", cutoffLength - cutoffMargin);
    let shortenedSummary = summary.substring(
      0,
      spaceIndex < cutoffLength + cutoffMargin && spaceIndex > 0
        ? spaceIndex
        : cutoffLength
    );
    if (shortenedSummary.match("[.!?]{1}$") !== null) {
      return shortenedSummary;
    } else if (shortenedSummary.match("[,]{1}$") !== null) {
      shortenedSummary = shortenedSummary.substring(
        0,
        shortenedSummary.length - 1
      );
    }
    return shortenedSummary.concat("...");
  } else {
    return summary;
  }
}

ListProjects.propTypes = {
  projectsList: PropTypes.object.isRequired,
  hideStatus: PropTypes.bool,
};
