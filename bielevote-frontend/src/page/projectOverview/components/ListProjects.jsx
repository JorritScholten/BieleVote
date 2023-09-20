import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import { Icon, Item } from "semantic-ui-react";

import { formatDate } from "../../../components/Utils";

export default function ListProjects({ projectsList }) {
  return projectsList.projects == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group className="w-3/5" relaxed>
      {projectsList.projects.map((project) => (
        <Item key={project.id}>
          <Item.Content>
            <Item.Header as="a">
              <Link to={"/projects/" + project.id}>{project.title}</Link>
            </Item.Header>
            <Item.Meta>
              <Icon name="calendar alternate" />{" "}
              {formatDate(project.datePublished)}
            </Item.Meta>
            <Item.Meta>
              <Icon name="user" /> {project.author.legalName}
            </Item.Meta>
            <Item.Description>{project.summary}</Item.Description>
            <Item.Extra>Status: {project.status}</Item.Extra>
          </Item.Content>
        </Item>
      ))}
    </Item.Group>
  );
}

ListProjects.propTypes = {
  projectsList: PropTypes.object.isRequired,
};
