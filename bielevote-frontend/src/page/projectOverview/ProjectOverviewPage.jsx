import Header from "../../components/Header";
import ListProjects from "./components/ListProjects";
import { useState, useEffect } from "react";
import { backendApi } from "../../misc/ApiMappings";

function ProjectOverviewPage() {
const [projectsList, setProjectsList] = useState([]);
const [version, setVersion] = useState(0);
const incVersion = () => setVersion(version + 1);

useEffect(() => {
  const getProjectsList = async () => {
    const allProjects = await backendApi.getAllProjects();
    setProjectsList(allProjects.data);
  };
  getProjectsList();
}, [version]);

  return (
    <div>
      <Header pageTitle="projects" />
      <ListProjects
        projectsList={projectsList}
        limit={30}
        incrementDataVersion={incVersion}
      />
    </div>
  );
}

export default ProjectOverviewPage;
