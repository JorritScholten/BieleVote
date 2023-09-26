import { useEffect, useState } from "react";
import { emptyForms, projectStatus } from "../../../misc/ApiForms";
import { backendApi } from "../../../misc/ApiMappings";
import ListProjects from "../../projectOverview/components/ListProjects";
import { Button } from "semantic-ui-react";
import { Link } from "react-router-dom";
export default function ProjectsPreview() {
  const [projectsList, setProjectsList] = useState(emptyForms.projectOverview);

  useEffect(() => {
    fetchProjectPreview();
  }, []);
  const fetchProjectPreview = async () => {
    let statusFilter = [];
    statusFilter.push(projectStatus.active);
    const response = await backendApi.getAllProjects(0, 2, statusFilter);
    setProjectsList(response.data);
  };

  return projectsList.projects == [] ? (
    <div>loading...</div>
  ) : (
    <div className=" h-1/3 bg-slate-300">
      <ListProjects projectsList={projectsList} />
      <Link to={"/projects"}>
        <Button primary>View All Projects</Button>
      </Link>
    </div>
  );
}
