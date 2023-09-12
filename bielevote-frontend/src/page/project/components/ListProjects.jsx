import "semantic-ui-css/semantic.min.css";
import { Icon, Table } from "semantic-ui-react";
import { useState, useEffect } from "react";
import PropTypes from "prop-types";

function ListProjects({ projectsList, limit = 50 }) {
  const [version, setVersion] = useState(0);

  return (
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
              {projectsList.slice(0, limit).map((project) => (
                <div key={project.id}>{project.id}</div>
              ))}{" "}
            </Table.Cell>
            <Table.Cell>
              {" "}
              {projectsList.slice(0, limit).map((project) => (
                <div key={project.id}>{project.title}</div>
              ))}{" "}
            </Table.Cell>
            <Table.Cell></Table.Cell>
          </Table.Row>
        </Table.Body>
      </Table>

      <button
        className="rounded-lg border-2 border-blue-300"
        type="button"
        onClick={() => setVersion(version + 1)}
      >
        Refresh <Icon name="refresh" size="small" />
      </button>
    </>
  );
}

ListProjects.propTypes = {
  projectsList: PropTypes.array.isRequired,
  limit: PropTypes.number,
  // setVersion: PropTypes.func.isRequired,
};

export default ListProjects;
