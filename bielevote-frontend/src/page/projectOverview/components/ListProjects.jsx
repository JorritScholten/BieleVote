import { Table } from "semantic-ui-react";
import PropTypes from "prop-types";

export default function ListProjects({ projectsList }) {
  console.log(projectsList);
  return projectsList.projects == [] ? (
    <div>loading...</div>
  ) : (
    <>
      <Table celled>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell>Identity number</Table.HeaderCell>
            <Table.HeaderCell>Title</Table.HeaderCell>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          <Table.Row>
            <Table.Cell>
              {" "}
              {projectsList.projects.map((project) => (
                <div key={project.id}>
                  <div>{project.id}</div>
                  <div>{project.title}</div>
                  <div>{project.author.legalName}</div>
                  <div>{project.datePublished}</div>
                  <div>{project.status}</div>
                </div>
              ))}{" "}
            </Table.Cell>
          </Table.Row>
        </Table.Body>
      </Table>
    </>
  );
}

ListProjects.propTypes = {
  projectsList: PropTypes.object.isRequired,
};
