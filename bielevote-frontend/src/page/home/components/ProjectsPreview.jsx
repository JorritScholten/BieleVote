import { useEffect, useState } from "react";
import { Button } from "semantic-ui-react";
import { Link } from "react-router-dom";

import { emptyForms, projectStatus } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import ListProjects from "../../projectOverview/components/ListProjects";

export default function ProjectsPreview() {
  const [projectsList, setProjectsList] = useState(emptyForms.projectOverview);

  useEffect(() => {
    async function fetchProjectPreview() {
      try {
        const response = await backendApi.getAllProjects(
          0,
          2,
          [projectStatus.active],
          null
        );
        setProjectsList(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    fetchProjectPreview();
  }, []);

  return projectsList.projects.length === 0 ? (
    <div>loading...</div>
  ) : (
    <div>
      <ListProjects projectsList={projectsList} />
      <Link to={"/projects"}>
        <Button fluid primary>
          View All Projects
        </Button>
      </Link>
    </div>
  );
}
