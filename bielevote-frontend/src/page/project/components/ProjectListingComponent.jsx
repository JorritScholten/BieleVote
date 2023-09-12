import { useState, useEffect } from "react";
import axios from "axios";
import "semantic-ui-css/semantic.min.css";
import { Icon, Table } from "semantic-ui-react";

function ProjectListingComponent() {
  const [projectsList, setProjectsList] = useState([]);
  const [refreshData, setRefreshData] = useState(false);

  useEffect(() => {
    const getProjectsList = async () => {
      const allProjects = await axios.get(
        "http://localhost:8080/api/v1/project"
      );
      setProjectsList(allProjects.data);
      console.log(allProjects.data);
    };
    getProjectsList();
  }, [refreshData]);

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
              {projectsList.map((project) => (
                <div key={project.id}>{project.id}</div>
              ))}{" "}
            </Table.Cell>
            <Table.Cell>
              {" "}
              {projectsList.map((project) => (
                <div key={project.id}>{project.title}</div>
              ))}{" "}
            </Table.Cell>
          </Table.Row>
        </Table.Body>
      </Table>

      <button
        className="rounded-lg border-2 border-blue-300"
        type="button"
        onClick={() => setRefreshData(!refreshData)}
      >
        Refresh <Icon name="refresh" size="small" />
      </button>
    </>
  );
}

export default ProjectListingComponent;
