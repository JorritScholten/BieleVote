import Header from "../../components/Header";
import ListProjects from "./components/ListProjects";
import { useState } from "react";

function ProjectOverviewPage() {
const [projectsList, setProjectsList] = useState([]);
const [version, setVersion] = useState(0);
const incVersion = () => setVersion(version + 1);


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
